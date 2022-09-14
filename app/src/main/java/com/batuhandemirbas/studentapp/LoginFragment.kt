package com.batuhandemirbas.studentapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.batuhandemirbas.studentapp.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null

    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Actions to be applied when the login button is pressed
        binding.buttonLogin.setOnClickListener {
            val name = binding.editText.text.toString()
            if (name == "") {
                Toast.makeText(activity, "Lütfen isminizi giriniz.", Toast.LENGTH_SHORT).show()
            } else {
                val action = LoginFragmentDirections.actionLoginFragmentToDashboardFragment(name)
                Navigation.findNavController(it).navigate(action)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
