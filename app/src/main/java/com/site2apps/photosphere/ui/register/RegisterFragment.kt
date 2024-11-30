package com.site2apps.photosphere.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.site2apps.photosphere.R
import com.site2apps.photosphere.databinding.FragmentRegisterBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private val viewModel: RegisterViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        observeViewModel()
        setupListeners()

        return binding.root
    }

    private fun setupListeners() {
        binding.registerButton.setOnClickListener {
            viewModel.validateAndRegister()
        }

        binding.loginTextView.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
    }

    private fun observeViewModel() {
        viewModel.emailError.observe(viewLifecycleOwner) { error ->
            binding.emailInputLayout.error = error
        }

        viewModel.passwordError.observe(viewLifecycleOwner) { error ->
            binding.passwordInputLayout.error = error
        }

        viewModel.ageError.observe(viewLifecycleOwner) { error ->
            binding.ageInputLayout.error = error
        }

        viewModel.isRegisterValid.observe(viewLifecycleOwner) { isValid ->
            if (isValid == true) {
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
            }
        }
    }
}
