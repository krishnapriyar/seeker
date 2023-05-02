package com.seeker.seeker.ui.login

import androidx.lifecycle.ViewModelProvider
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.seeker.seeker.R
import com.seeker.seeker.databinding.FragmentHomeBinding
import com.seeker.seeker.databinding.FragmentLoginBinding
import com.seeker.seeker.ui.login.model.LoginState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        val usernameEditText = binding.edittextUsername
        val passwordEditText = binding.edittextPassword
        val loginButton = binding.login
        val loadingProgressBar = binding.loading
        initObservers()
        initViewListeners(usernameEditText, passwordEditText, loginButton, loadingProgressBar)
        loginViewModel.checkExistingLogin()
    }

    private fun initObservers() {

        loginViewModel.loginStateObserver.observe(viewLifecycleOwner){
            when(it){
                LoginState.LOGGED_IN -> {
                    findNavController().navigate(R.id.action_loginFragment_to_navigation_home)
                }
                LoginState.LOGGED_OUT -> {
                    //Do nothing show log in screen
                }
                LoginState.ERROR -> {
                    Toast.makeText(requireContext(), R.string.login_failed, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun initViewListeners(
        usernameEditText: TextInputEditText,
        passwordEditText: TextInputEditText,
        loginButton: Button,
        loadingProgressBar: ProgressBar
    ) {


        val onTextChanged = object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (usernameEditText.text?.isBlank() == false
                    && passwordEditText.text?.isBlank() == false
                ){
                    loginButton.isEnabled = true
                }
            }

        }

        usernameEditText.addTextChangedListener(onTextChanged)
        passwordEditText.addTextChangedListener(onTextChanged)

        passwordEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                loginViewModel.login(
                    usernameEditText.text.toString(),
                    passwordEditText.text.toString()
                )
            }
            false
        }

        loginButton.setOnClickListener {
            loadingProgressBar.visibility = View.VISIBLE
            loginViewModel.login(
                usernameEditText.text.toString(),
                passwordEditText.text.toString()
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}