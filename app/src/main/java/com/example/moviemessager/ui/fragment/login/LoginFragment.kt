package com.example.moviemessager.ui.fragment.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast

import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import com.example.moviemessager.R
import com.example.moviemessager.databinding.FragmentHomeBinding
import com.example.moviemessager.databinding.FragmentLoginBinding
import com.example.moviemessager.domain.model.UserModel
import com.example.moviemessager.domain.model.UserModelFirebase
import com.example.moviemessager.ui.base.FragmentBaseNCMVVM
import com.example.moviemessager.ui.base.viewBinding
import com.example.moviemessager.ui.fragment.home.HomeViewModel
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment() : FragmentBaseNCMVVM<LoginViewModel, FragmentLoginBinding>() {
    override val viewModel: LoginViewModel by viewModels()
    override val binding: FragmentLoginBinding by viewBinding()
    lateinit var launcher: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            try {
                val account = task.getResult(ApiException::class.java)
                if (account != null) {
                    // firebaseAuthWithGoogle(account.idToken!!)
                    viewModel.firebaseAuthWithGoogle(account.idToken!!, {
                        binding.HintError.text = "firebaseAuthWithEmail: ok"
                        popBackStack()
                    }, {
                        showErrorDialog("Error Action", "Auch error", false, {  }, {
                            Toast.makeText(requireContext(), "Auch error", Toast.LENGTH_SHORT)
                                .show()
                        })
                    })
                }
            } catch (e: ApiException) {
                binding.HintError.text = e.message
                showErrorDialog("Error Action", "Auch error", false, {  }, {
                    Toast.makeText(requireContext(), "Auch error", Toast.LENGTH_SHORT)
                        .show()
                })
            }
        }
    }

    override fun onView() {

    }

    override fun onViewClick() {
        binding.btLoginGoogle.setOnClickListener {
            viewModel.signInWithGoogle(launcher, getClient())
        }
        binding.btLogin.setOnClickListener {
            val isEmptyLogin = !binding.etLogin.text.isNullOrEmpty()
            val isEmptyPassword = !binding.etPass.text.isNullOrEmpty()
            if (isEmptyLogin && isEmptyPassword) {
            viewModel.loginBasicAuth(
                binding.etLogin.text.toString(),
                binding.etPass.text.toString(),
                {
                    binding.HintError.text = "firebaseAuthWithEmail: ok"
                    popBackStack()
                },
                {
                    binding.HintError.text = "firebaseAuthWithGoogle: nok"
                    showErrorDialog("Error Action", "firebaseAuthWithGoogle: nok", false, {  }, {
                        Toast.makeText(requireContext(), "Auch error", Toast.LENGTH_SHORT)
                            .show()
                    })
                })
            }else{
                binding.HintError.text="Empty password or login"
                showErrorDialog("Error Action", "Empty password or login", false, {  }, {
                    Toast.makeText(requireContext(), "Auch error", Toast.LENGTH_SHORT)
                        .show()
                })
            }
        }
        binding.btReg.setOnClickListener {

            val isEmptyLogin = !binding.etLogin.text.isNullOrEmpty()
            val isEmptyPassword = !binding.etPass.text.isNullOrEmpty()
            if (isEmptyLogin && isEmptyPassword) {
                viewModel.registerBasicAuth(
                    binding.etLogin.text.toString(),
                    binding.etPass.text.toString(),
                    {
                        binding.HintError.text = "firebaseAuthWithEmail: ok"
                        popBackStack()
                    },
                    {
                        binding.HintError.text = "firebaseAuthWithGoogle: nok"
                        showErrorDialog("Error Action", "firebaseAuthWithGoogle: nok", false, {  }, {
                            Toast.makeText(requireContext(), "firebaseAuthWithGoogle: nok", Toast.LENGTH_SHORT)
                                .show()
                        })
                    })
            }else{
                binding.HintError.text="Empty password or login"
                showErrorDialog("Error Action", "Empty password or login", false, {  }, {
                    Toast.makeText(requireContext(), "Empty password or login", Toast.LENGTH_SHORT)
                        .show()
                })
            }
        }
    }

    private fun getClient(): GoogleSignInClient {
        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        return GoogleSignIn.getClient(requireActivity(), gso)
    }

}