package com.batuhandemirbas.studentapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.navigation.Navigation
import com.batuhandemirbas.studentapp.data.DatabaseHelper
import com.batuhandemirbas.studentapp.databinding.FragmentForgotPasswordBinding
import com.batuhandemirbas.studentapp.model.Student
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ForgotPasswordFragment : Fragment() {

    private var _binding: FragmentForgotPasswordBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentForgotPasswordBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonChangePassword.setOnClickListener {

            val emailAddress = binding.emailEditTextFromForgot.text.toString()

            Firebase.auth.sendPasswordResetEmail(emailAddress)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("Firebase", "Email sent.")

                        Snackbar.make(
                            binding.buttonChangePassword,
                            "Şifre değiştirme maili başarıyla gönderildi.",
                            Snackbar.LENGTH_SHORT
                        ).show()

                        val action =
                            ForgotPasswordFragmentDirections.actionForgotPasswordFragmentToLoginFragment()
                        Navigation.findNavController(it).navigate(action)

                    } else {
                        Snackbar.make(
                            binding.buttonChangePassword,
                            "Kayıtlı kullanıcı bulunamadı.",
                            Snackbar.LENGTH_SHORT
                        ).show()
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