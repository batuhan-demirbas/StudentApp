package com.batuhandemirbas.studentapp

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.batuhandemirbas.studentapp.databinding.FragmentAverageBinding

class AverageFragment : Fragment() {

    /** This property is only valid between onCreateView and onDestroyView */
    private var _binding: FragmentAverageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentAverageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var vize: Int
        var final: Int
        var average: Double

        binding.buttonCalculate.setOnClickListener {
            vize = binding.vizeEditText.text.toString().toInt()
            final = binding.finalEditText.text.toString().toInt()
            average = vize * 0.4 + final * 0.6

            binding.textViewResult.apply {
                visibility = View.VISIBLE

                if (average >= 50 && final >= 50) {
                    text = "Sonuç: $average, geçtiniz :)"
                    setTextColor(Color.GREEN)
                } else {
                    text = "Sonuç: $average, kaldınız :("
                    setTextColor(Color.RED)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}