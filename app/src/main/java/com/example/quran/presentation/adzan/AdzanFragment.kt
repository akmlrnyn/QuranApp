package com.example.quran.presentation.adzan

import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.quran.core.data.Resource
import com.example.quran.databinding.FragmentAdzanBinding
import com.example.quran.presentation.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import java.util.Locale


class AdzanFragment : Fragment() {
    private var _binding: FragmentAdzanBinding? = null
    private val binding get() = _binding as FragmentAdzanBinding
    private val adzanViewModel: AdzanViewModel by viewModels { ViewModelFactory(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentAdzanBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adzanViewModel.getDailyAdzanTime().observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {}
                is Resource.Success -> {
                    binding.tvLocation.text = it.data?.listAdress?.get(1)
                    when (val listCity = it.data?.listCity) {
                        is Resource.Loading -> {}
                        is Resource.Success -> {
                            val idCity = listCity.data?.get(0)?.idCity
                            val city = listCity.data?.get(0)?.location
                            Toast.makeText(context, "id city of $city: $idCity", Toast.LENGTH_SHORT).show()
                        }
                        is Resource.Error -> {}

                        else -> {
                            Toast.makeText(context, "Something wrong", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                is Resource.Error -> {}
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}