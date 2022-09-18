package com.batuhandemirbas.studentapp

import android.os.Bundle
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


        val db = DatabaseHelper(context)

        binding.buttonChangePassword.setOnClickListener {
            try {
                val number = binding.numberEditTextFromForgot.text.toString().toInt()
                val name = binding.nameEditTextFromForgot.text.toString()
                val newPassword = binding.newPasswordEditTextFromForgot.text.toString().toInt()

                val student: Student = db.readDataStudent(number, context)

                if (name == student.name) {
                    db.updateData(student.number, student.name, newPassword, context)

                    Snackbar.make(
                        binding.buttonChangePassword,
                        "Şifre başarıyla değiştirildi.",
                        Snackbar.LENGTH_SHORT
                    ).show()

                    val action =
                        ForgotPasswordFragmentDirections.actionForgotPasswordFragmentToLoginFragment()
                    Navigation.findNavController(it).navigate(action)
                } else {
                    Snackbar.make(
                        binding.buttonChangePassword,
                        "Numara ve isim eşleşmiyor.",
                        Snackbar.LENGTH_SHORT
                    ).show()
                    binding.newPasswordEditTextFromForgot.onEditorAction(EditorInfo.IME_ACTION_DONE)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Snackbar.make(
                    binding.buttonChangePassword,
                    "Kayıtlı kullanıcı bulunamadı.",
                    Snackbar.LENGTH_SHORT
                ).show()
                binding.newPasswordEditTextFromForgot.onEditorAction(EditorInfo.IME_ACTION_DONE)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}