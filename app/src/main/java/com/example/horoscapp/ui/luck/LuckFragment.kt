package com.example.horoscapp.ui.luck

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.horoscapp.R
import com.example.horoscapp.databinding.FragmentLuckBinding
import com.example.horoscapp.domain.model.HoroscopeModel
import com.example.horoscapp.ui.core.OnSwipeTouchListener
import com.example.horoscapp.ui.luck.LuckFragmentDirections
import com.example.horoscapp.ui.model.LuckyModel
import com.example.horoscapp.ui.providers.RandomCardProvider
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.random.Random

@AndroidEntryPoint
class LuckFragment : Fragment() {

    private var _binding: FragmentLuckBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var randomCardProvider: RandomCardProvider

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        initListeners()
        preparePrediction()
    }

    private fun preparePrediction() {
        val currentLuck = randomCardProvider.getLucky()
        currentLuck?.let {luck ->
            val currentPrediction = getString(luck.text)
            binding.tvLucky.text = currentPrediction
            binding.ivLuckyCard.setImageResource(luck.image)
            binding.tvShare.setOnClickListener{shareResult(currentPrediction)}
        }
    }

    private fun shareResult(prediction:String) {
        val sendIntent:Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT,prediction)
            type= "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent,null)
        startActivity(shareIntent)
    }

    @SupressLint("ClickableViewAccessibility")
    private fun initListeners() {
        //binding.ivRoulette.setOnClickListener { spinRoulette() }
        binding.ivRoulette.setOnTouchListener(object: OnSwipeTouchListener(requireContext()){
            override fun onSwipeRight() {
                spinRoulette()

            }
            override fun onSwipeLeft(){
                spinRoulette()
            }
        })
    }


annotation class SupressLint(val value: String)

private fun spinRoulette() {
        val random = Random.Default
        val degrees = random.nextInt(1140) + 360 // 4 vueltas
        val animator = ObjectAnimator.ofFloat(binding.ivRoulette, View.ROTATION, 0f, degrees.toFloat())
        animator.duration = 2000
        animator.interpolator = DecelerateInterpolator()
        animator.doOnEnd { slideCard() }
        animator.start()
    }

    private fun slideCard() {
        val slideUpAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_up)
        slideUpAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                binding.reverse.isVisible = true
            }

            override fun onAnimationEnd(animation: Animation?) {
                growCard()
            }

            override fun onAnimationRepeat(animation: Animation?) {
                // No se necesita acción aquí
            }
        })

        binding.reverse.startAnimation(slideUpAnimation)
    }

    private fun growCard() {
        val growAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.grow)
        growAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) { }

            override fun onAnimationEnd(animation: Animation?) {
                //navigateToDetail() // Navegar a la actividad de detalles del horóscopo
                binding.reverse.isVisible = false
                showPremonitionView()
            }

            override fun onAnimationRepeat(animation: Animation?) { }
        })
        binding.reverse.startAnimation(growAnimation)
    }

    private fun showPremonitionView() {
        val disappearAnimation = AlphaAnimation(1.0f,0.0f)
        disappearAnimation.duration = 200

        val appearAnimation = AlphaAnimation(0.0f,1.0f)
        appearAnimation.duration = 1000

        disappearAnimation.setAnimationListener(object:Animation.AnimationListener{
            override fun onAnimationStart(p0: Animation?) {

            }

            override fun onAnimationEnd(p0: Animation?) {
                binding.preview.isVisible = false
                binding.prediction.isVisible = true
            }
            override fun onAnimationRepeat(p0: Animation?) {}
        })

        binding.preview.startAnimation(appearAnimation)
        binding.prediction.startAnimation(disappearAnimation)
    }

    private fun navigateToDetail() {
        val type = HoroscopeModel.Aries // O cualquier lógica para determinar el signo del horóscopo
        findNavController().navigate(
            LuckFragmentDirections.actionLuckFragmentToHoroscopeDetailActivity(type)
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLuckBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
