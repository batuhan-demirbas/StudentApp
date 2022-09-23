package com.batuhandemirbas.studentapp.ui.forgot

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.batuhandemirbas.studentapp.databinding.FragmentForgotPasswordBinding
import com.batuhandemirbas.studentapp.util.extensions.showSnackbar
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ForgotPasswordFragment : Fragment() {

    /** This property is only valid between onCreateView and onDestroyView */
    private val binding get() = _binding!!
    private var _binding: FragmentForgotPasswordBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentForgotPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //
        binding.buttonChangePassword.setOnClickListener {
            val emailAddress = binding.emailEditTextFromForgot.text.toString()

            // Firebase user password reset email sending processes
            Firebase.auth.sendPasswordResetEmail(emailAddress)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // If send email succes, display a message to the user
                        Log.d("Firebase", "Email sent.")

                        view.showSnackbar("Şifre değiştirme maili başarıyla gönderildi.")

                        // Navigate to login fragment
                        val action =
                            ForgotPasswordFragmentDirections.actionForgotPasswordFragmentToLoginFragment()
                        Navigation.findNavController(it).navigate(action)

                    } else {
                        // If send mail fails, display a message to the user
                        view.showSnackbar("Kayıtlı kullanıcı bulunamadı.")
                        binding.emailEditTextFromForgot.onEditorAction(EditorInfo.IME_ACTION_DONE)
                    }
                }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}