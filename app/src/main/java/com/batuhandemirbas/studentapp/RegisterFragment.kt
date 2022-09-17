package com.batuhandemirbas.studentapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.navigation.Navigation
import com.batuhandemirbas.studentapp.data.DatabaseHelper
import com.batuhandemirbas.studentapp.databinding.FragmentRegisterBinding
import com.google.android.material.snackbar.Snackbar

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Connect database
        val db = DatabaseHelper(context)

        // Actions to be applied when the register button is pressed
        val toggleButton = binding.passwordToggle
        binding.registerButton.setOnClickListener {

            if (toggleButton.isChecked) {
                val number = binding.numberTextField.text.toString().toInt()
                val name = binding.nameTextField.text.toString()
                val password = binding.passwordTextField.text.toString().toInt()

                // Write data
                db.writeData(number, name, password, context)


                // Snackbar message
                Snackbar.make(it, "Başarıyla kayıt oldunuz.", Snackbar.LENGTH_SHORT).show()
                binding.passwordTextField.onEditorAction(EditorInfo.IME_ACTION_DONE)

                // Action to LoginFragment
                val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
                Navigation.findNavController(it).navigate(action)

            } else {
                // Not cheked snackbar message
                Snackbar.make(it, "Lütfen sözleşmeyi onaylayınız.", Snackbar.LENGTH_SHORT).show()
                binding.passwordTextField.onEditorAction(EditorInfo.IME_ACTION_DONE)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}