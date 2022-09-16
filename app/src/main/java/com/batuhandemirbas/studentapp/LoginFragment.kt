package com.batuhandemirbas.studentapp

import android.content.ContentValues
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.batuhandemirbas.studentapp.data.DatabaseHelper
import com.batuhandemirbas.studentapp.data.StudentSource
import com.batuhandemirbas.studentapp.data.TableContract
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

        //db
        val db = DatabaseHelper(context)

        // Actions to be applied when the login button is pressed
        binding.buttonLogin.setOnClickListener {
            val number = binding.numberEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            if (number != "031811626" || password != "12345") {
                Toast.makeText(
                    activity,
                    "Numara ve şifre eşleşmiyor. Lütfen tekrar deneyiniz.",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val action = LoginFragmentDirections.actionLoginFragmentToDashboardFragment(number)
                Navigation.findNavController(it).navigate(action)
            }
        }

        // Actions to be applied when the register TextView is pressed

        binding.textViewRegister.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
