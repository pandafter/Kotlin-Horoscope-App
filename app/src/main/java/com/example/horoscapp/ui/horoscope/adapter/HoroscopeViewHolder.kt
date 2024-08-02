package com.example.horoscapp.ui.horoscope.adapter

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.view.View
import android.view.animation.LinearInterpolator
import android.view.animation.OvershootInterpolator
import androidx.recyclerview.widget.RecyclerView
import com.example.horoscapp.databinding.ItemHoroscopeBinding
import com.example.horoscapp.domain.model.HoroscopeInfo

class HoroscopeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemHoroscopeBinding.bind(view)

    fun render(horoscopeInfo: HoroscopeInfo, onItemSelected: (HoroscopeInfo) -> Unit) {
        val context = binding.tvHoroscope.context
        binding.ivHoroscope.setImageResource(horoscopeInfo.img)
        binding.tvHoroscope.text = context.getString(horoscopeInfo.name)

        binding.parent.setOnClickListener{
            startBubbleAnimation(binding.ivHoroscope, newLambda = {onItemSelected(horoscopeInfo)})
            //onItemSelected(horoscopeInfo)
        }

    }

    private fun startBubbleAnimation(view: View, newLambda: () -> Unit) {
        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 0.8f)
        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 0.8f)
        val alpha = PropertyValuesHolder.ofFloat(View.ALPHA, 0.8f)

        ObjectAnimator.ofPropertyValuesHolder(view, scaleX, scaleY, alpha).apply {
            duration = 250 // Adjust duration as needed
            repeatCount = 1
            repeatMode = ObjectAnimator.REVERSE
            interpolator = OvershootInterpolator()
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    newLambda()
                }
            })
            start()
        }
    }
}