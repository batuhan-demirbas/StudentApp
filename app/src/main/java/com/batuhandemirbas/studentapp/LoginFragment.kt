package com.batuhandemirbas.studentapp

import android.content.ContentValues
import android.os.Bundle
import android.provider.ContactsContract
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
import com.batuhandemirbas.studentapp.model.Student


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
        val dbRead = db.readableDatabase

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        val projection = arrayOf(
            TableContract.TableEntry.COLUMN_NUMBER,
            TableContract.TableEntry.COLUMN_NAME,
            TableContract.TableEntry.COLUMN_PASSWORD
        )


        // How you want the results sorted in the resulting Cursor
        val sortOrder = "${TableContract.TableEntry.COLUMN_NUMBER} DESC"

        val cursor = dbRead.query(
            TableContract.TableEntry.TABLE_NAME,   // The table to query
            projection,             // The array of columns to return (pass null to get all)
            null,
            null,
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            null          // The sort order
        )

        // DATA
        val students = mutableListOf<Student>()
        with(cursor) {
            while (moveToNext()) {
                val userNumber =
                    getInt(getColumnIndexOrThrow(TableContract.TableEntry.COLUMN_NUMBER))
                val userName =
                    getString(getColumnIndexOrThrow(TableContract.TableEntry.COLUMN_NAME))
                val userPassword =
                    getInt(getColumnIndexOrThrow(TableContract.TableEntry.COLUMN_PASSWORD))

                students.add(Student(userNumber, userName, userPassword))
            }
        }
        cursor.close()

        // Actions to be applied when the login button is pressed
        binding.buttonLogin.setOnClickListener { buttonLoginView ->
            val number = binding.numberEditText.text.toString().toInt()
            val password = binding.passwordEditText.text.toString().toInt()

            var flag = false
            students.forEach {
                if (number == it.number && password == it.password) {

                    val action = LoginFragmentDirections.actionLoginFragmentToDashboardFragment(number, it.name, password)
                    Navigation.findNavController(buttonLoginView).navigate(action)
                    flag = true
                }
            }

            if (!flag) {
                Toast.makeText(
                    activity,
                    "Numara ve şifre eşleşmiyor. Lütfen tekrar deneyiniz.", Toast.LENGTH_SHORT
                ).show()
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
