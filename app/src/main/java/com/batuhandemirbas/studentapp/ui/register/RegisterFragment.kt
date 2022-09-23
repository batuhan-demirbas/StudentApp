package com.batuhandemirbas.studentapp.ui.register

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.batuhandemirbas.studentapp.databinding.FragmentRegisterBinding
import com.batuhandemirbas.studentapp.util.extensions.showSnackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private var _binding: FragmentRegisterBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize Firebase Auth
        auth = Firebase.auth

    }

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

        // Actions to be applied when the register button is pressed
        val toggleButton = binding.passwordToggle
        binding.registerButton.setOnClickListener {

            if (toggleButton.isChecked) {
                val email = binding.emailTextField.text.toString()
                val password = binding.passwordTextField.text.toString()

                signUpFirebase(email, password, it)

            } else {
                // Not cheked snackbar message
                view.showSnackbar("Lütfen sözleşmeyi onaylayınız.")
                binding.passwordTextField.onEditorAction(EditorInfo.IME_ACTION_DONE)
            }
        }
    }

    private fun signUpFirebase(email: String, password: String, view: View) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser

                    user!!.sendEmailVerification()
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                Log.d("Firebase", "Email verification sent.")
                            }
                        }

                    // Snackbar message
                    view.showSnackbar("Başarıyla kayıt oldunuz.")
                    binding.passwordTextField.onEditorAction(EditorInfo.IME_ACTION_DONE)

                    // Action to LoginFragment
                    val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
                    Navigation.findNavController(view).navigate(action)

                } else {
                    // Snackbar message
                    view.showSnackbar("Başarıyla kayıt oldunuz.")
                    binding.passwordTextField.onEditorAction(EditorInfo.IME_ACTION_DONE)
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}