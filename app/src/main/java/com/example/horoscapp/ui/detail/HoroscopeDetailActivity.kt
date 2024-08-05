package com.example.horoscapp.ui.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.navArgs
import com.example.horoscapp.databinding.ActivityHoroscopeDatailBinding
import com.example.horoscapp.domain.model.HoroscopeModel.*
import com.example.horoscapp.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HoroscopeDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHoroscopeDatailBinding
    private val horoscopeDetailViewModel : HoroscopeDetailViewModel by viewModels()

    private val args:HoroscopeDetailActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHoroscopeDatailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
        horoscopeDetailViewModel.getHoroscope(args.type)
    }

    private fun initUI() {
        initListeners()
        initUIState()
    }

    private fun initListeners() {
        binding.ivBack.setOnClickListener{ onBackPressed() }
    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                horoscopeDetailViewModel.state.collect{
                    when(it){
                        is HoroscopeDetailState.Error -> errorState()
                        HoroscopeDetailState.Loading -> loadingState()
                        is HoroscopeDetailState.Success -> successState(it)
                    }
                }
            }
        }
    }

    private fun successState(state: HoroscopeDetailState.Success) {
        binding.pb.isVisible = false
        binding.tvTitle.text = state.sign
        binding.tvBody.text = state.prediction

        val image =when(state.horoscopeModel) {
            Aries -> R.drawable.aries
            Taurus -> R.drawable.taurus
            Gemini -> R.drawable.gemini
            Cancer -> R.drawable.cancer
            Leo -> R.drawable.leo
            Virgo -> R.drawable.virgo
            Libra -> R.drawable.libra
            Scorpio -> R.drawable.scorpio
            Capricorn -> R.drawable.capricorn
            Aquarius -> R.drawable.aquarius
            Pisces -> R.drawable.pisces
        }

        binding.ivDetail.setImageResource(image)

    }

    private fun errorState() {
        binding.pb.isVisible = false

    }

    private fun loadingState() {
        binding.pb.isVisible = true
    }
}