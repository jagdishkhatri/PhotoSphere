package com.site2apps.photosphere.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.site2apps.photosphere.R
import com.site2apps.photosphere.databinding.FragmentLoginBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        observeViewModel()
        setupListeners()
        return binding.root
    }

    private fun setupListeners() {
        binding.loginButton.setOnClickListener {
            viewModel.validateAndLogin()
        }

        binding.registerTextView.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    private fun observeViewModel() {
        viewModel.emailError.observe(viewLifecycleOwner, Observer { error ->
            binding.emailInputLayout.error = error
        })

        viewModel.passwordError.observe(viewLifecycleOwner, Observer { error ->
            binding.passwordInputLayout.error = error
        })

        viewModel.isLoginValid.observe(viewLifecycleOwner, Observer { isValid ->
            if (isValid == true) {
                Toast.makeText(requireContext(), "Login Successful", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            }
        })
    }
}
