package com.batuhandemirbas.studentapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.batuhandemirbas.studentapp.databinding.FragmentLoginBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null

    private lateinit var auth: FirebaseAuth

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
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Buttons
        with(binding) {
            // Login
            buttonLogin.setOnClickListener { view ->
                val email = binding.numberEditText.text.toString()
                val password = binding.passwordEditText.text.toString()
                signIn(email, password, view)
            }

            // Register
            textViewRegister.setOnClickListener {
                val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
                Navigation.findNavController(it).navigate(action)
            }

            // Forgot
            textViewForgotPassword.setOnClickListener {
                val action = LoginFragmentDirections.actionLoginFragmentToForgotPasswordFragment()
                Navigation.findNavController(it).navigate(action)
            }


            /*
            try {
                // EditText'e girilen öğrenci numarasına göre öğrencinin verileri
                // database üzerinden çekme
                val student = db.readDataStudent(number, context)

                // Öğrenci bilgileri ile girilen şifre doğru ise yapılacak işlemler
                if (number == student.number && password == student.password) {
                    val action = LoginFragmentDirections.actionLoginFragmentToDashboardFragment(number)
                    Navigation.findNavController(buttonLoginView).navigate(action)
                } else {
                    // Snackbar message
                    Snackbar.make(
                        binding.buttonLogin,
                        "Numara ve şifre eşleşmiyor.",
                        Snackbar.LENGTH_SHORT
                    ).show()
                    binding.passwordEditText.onEditorAction(EditorInfo.IME_ACTION_DONE)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                // Snackbar message
                Snackbar.make(
                    binding.buttonLogin,
                    "Kayıtlı kullanıcı bulunamadı.",
                    Snackbar.LENGTH_SHORT
                ).show()
                binding.passwordEditText.onEditorAction(EditorInfo.IME_ACTION_DONE)
            } */
        }
    }

    private fun signIn(email: String, password: String, view: View) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()){ task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("FirebaseAuth", "signInWithEmail:success")
                    val user = auth.currentUser
                    val action = LoginFragmentDirections.actionLoginFragmentToDashboardFragment(31811626)
                    Navigation.findNavController(view).navigate(action)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("FirebaseAuth", "signInWithEmail:failure", task.exception)
                    Toast.makeText(context, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
