package com.batuhandemirbas.studentapp

import android.content.ContentValues
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.navigation.Navigation
import com.batuhandemirbas.studentapp.data.DatabaseHelper
import com.batuhandemirbas.studentapp.data.StudentSource
import com.batuhandemirbas.studentapp.data.TableContract
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
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // db
        val db = DatabaseHelper(context)

        // Gets the data repository in write mode
        val dbWrite = db.writableDatabase


        // Actions to be applied when the register button is pressed
        val toggleButton = binding.passwordToggle
        binding.registerButton.setOnClickListener {

            if (toggleButton.isChecked) {
                val number = binding.numberTextField.text.toString().toInt()
                val name = binding.nameTextField.text.toString()
                val password = binding.passwordTextField.text.toString().toInt()

                // Create a new map of values, where column names are the keys
                val values = ContentValues().apply {
                    put(TableContract.TableEntry.COLUMN_NUMBER, number)
                    put(TableContract.TableEntry.COLUMN_NAME, name)
                    put(TableContract.TableEntry.COLUMN_PASSWORD, password)
                }

                // Insert the new row, returning the primary key value of the new row
                val newRowId = dbWrite?.insert(TableContract.TableEntry.TABLE_NAME, null, values)

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