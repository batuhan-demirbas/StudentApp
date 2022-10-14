package com.batuhandemirbas.studentapp.ui.login

import android.app.Activity
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context.NOTIFICATION_SERVICE
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.batuhandemirbas.studentapp.R
import com.batuhandemirbas.studentapp.databinding.FragmentLoginBinding
import com.batuhandemirbas.studentapp.util.extensions.showSnackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import io.grpc.Context


class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private lateinit var auth: FirebaseAuth

    /** This property is only valid between onCreateView and onDestroyView. */
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

        // Actions applied to buttons
        with(binding) {

            // Login button
            buttonLogin.setOnClickListener { view ->
                val email = binding.numberEditText.text.toString()
                val password = binding.passwordEditText.text.toString()

                val notificationBuilder = NotificationCompat.Builder(requireActivity(), "1")
                    .setSmallIcon(androidx.loader.R.drawable.notification_bg)
                    .setContentTitle("Test Bildirimi")
                    .setContentText("Bu bir test bildirimidir. Dikkate almayınız.")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)

                createNotificationChannel()

                with(NotificationManagerCompat.from(requireActivity())) {
                    notify(1, notificationBuilder.build())
                }



                // Signing in with Firebase Email
                signIn(email, password, view)
            }

            // Register button
            textViewRegister.setOnClickListener {

                // Navigate to registration fragment
                val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
                Navigation.findNavController(it).navigate(action)
            }

            // Forgot button
            textViewForgotPassword.setOnClickListener {

                // Navigate to forgot password fragment
                val action = LoginFragmentDirections.actionLoginFragmentToForgotPasswordFragment()
                Navigation.findNavController(it).navigate(action)
            }
        }
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "loginNotification"
            val descriptionText = "Notification description"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("1", name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                context?.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun signIn(email: String, password: String, view: View) {

        // Firebase login control processes
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()){ task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("FirebaseAuth", "signInWithEmail:success")

                    // Go to Dashborad fragment on successful login
                    val action = LoginFragmentDirections.actionLoginFragmentToDashboardFragment()
                    Navigation.findNavController(view).navigate(action)

                } else {
                    // If sign in fails, display a message to the user
                    Log.w("FirebaseAuth", "signInWithEmail:failure", task.exception)

                    view.showSnackbar("Geçersiz kullanıcı bilgisi girdiniz.")
                    binding.passwordEditText.onEditorAction(EditorInfo.IME_ACTION_DONE)
                }
            }
    }

    override fun onStart() {
        super.onStart()

        // Check if user is logged in before
        val currentUser = auth.currentUser
        if(currentUser != null){

            // Dashboard fragment redirect if logged in before
            reload()
        }
    }

    private fun reload() {

        // Navigate to dashboard fragment
        val action = LoginFragmentDirections.actionLoginFragmentToDashboardFragment()
        Navigation.findNavController(binding.numberEditText).navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
