package com.example.horoscapp.ui.luck

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.horoscapp.R
import com.example.horoscapp.databinding.FragmentLuckBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.Random


@AndroidEntryPoint
class LuckFragment : Fragment() {

    private var _binding: FragmentLuckBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        initlistener()
    }

    private fun initlistener() {
        binding.ivRoulette.setOnClickListener {
            spinRoulette()
        }
    }

    private fun spinRoulette() {

        val random = Random()
        val degrees = random.nextInt(1440) + 360

        val animator = ObjectAnimator.ofFloat(binding.ivRoulette, View.ROTATION, 0f, degrees.toFloat())
        animator.duration = 2000
        animator.interpolator = DecelerateInterpolator()
        animator.doOnEnd {
            slideCard()
        }
        animator.start()
    }

    private fun slideCard() {
        val slideUpAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_up)

        slideUpAnimation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(p0: Animation?) {
                binding.reversedCard.isVisible = true
            }

            override fun onAnimationEnd(p0: Animation?) {
                growCard()
            }

            override fun onAnimationRepeat(p0: Animation?) {

            }

        })

        binding.reversedCard.startAnimation(slideUpAnimation)
    }

    private fun growCard() {
        val growAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.grow_card)

        growAnimation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(p0: Animation?) {}

            override fun onAnimationEnd(p0: Animation?) {
                binding.reversedCard.isVisible = false
                showPremonitionView()
            }

            override fun onAnimationRepeat(p0: Animation?) {}

        })

        binding.reversedCard.startAnimation(growAnimation)


    }

    private fun showPremonitionView() {
        val disappearAnimation = AlphaAnimation(1.0f, 0.0f)
        disappearAnimation.duration = 200

        disappearAnimation.setAnimationListener(object: Animation.AnimationListener{
            override fun onAnimationStart(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
                binding.preview.isVisible = false
                binding.prediction.isVisible = true
            }

            override fun onAnimationRepeat(animation: Animation?) {

            }

        })

        binding.preview.startAnimation(disappearAnimation)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLuckBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

}