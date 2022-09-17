package com.batuhandemirbas.studentapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.navigation.Navigation
import com.batuhandemirbas.studentapp.data.DatabaseHelper
import com.batuhandemirbas.studentapp.databinding.FragmentLoginBinding
import com.google.android.material.snackbar.Snackbar


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

        // Connect database
        val db = DatabaseHelper(context)

        // Actions to be applied when the login button is pressed
        binding.buttonLogin.setOnClickListener { buttonLoginView ->
            val number = binding.numberEditText.text.toString().toInt()
            val password = binding.passwordEditText.text.toString().toInt()

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
