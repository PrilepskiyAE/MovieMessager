package com.example.moviemessager.ui.fragment.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import com.example.moviemessager.R
import com.example.moviemessager.databinding.FragmentHomeBinding
import com.example.moviemessager.databinding.FragmentLoginBinding
import com.example.moviemessager.ui.base.FragmentBaseNCMVVM
import com.example.moviemessager.ui.base.viewBinding
import com.example.moviemessager.ui.fragment.home.HomeViewModel
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
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
        auth= Firebase.auth

        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            val task=GoogleSignIn.getSignedInAccountFromIntent(it.data)
            try {
                val account=task.getResult(ApiException::class.java)
                if (account!=null){
                    firebaseAuthWithGoogle(account.idToken!!)
                }
            } catch (e:ApiException){
                binding.HintError.text=e.message
                Log.d("TAG", "onView: ${e.localizedMessage}")
            }
        }
    }
    override fun onView() {

    }

    override fun onViewClick() {
        binding.btLoginGoogle.setOnClickListener {
            signInWithGoogle()
        }
        binding.btLogin.setOnClickListener {
            login()
        }
        binding.btReg.setOnClickListener {
            register()
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

    private fun signInWithGoogle() {
        val signInClient = getClient()
        launcher.launch(signInClient.signInIntent)
    }
    private fun firebaseAuthWithGoogle(idToken:String){
        val credation=GoogleAuthProvider.getCredential(idToken,null)
        auth.signInWithCredential(credation).addOnCompleteListener {
            if (it.isSuccessful){
                Log.d("TAG", "firebaseAuthWithGoogle: ok")
                binding.HintError.text="firebaseAuthWithGoogle: ok"
                popBackStack()
            }else {
                binding.HintError.text="firebaseAuthWithGoogle: nok"
            }
        }
    }


    fun register(){
        auth=FirebaseAuth.getInstance()
        val email=binding.etLogin.text.toString()
        val password=binding.etPass.text.toString()
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener{ task ->
            if(task.isSuccessful){
                binding.HintError.text="firebaseAuthWithGoogle: ok"
                popBackStack()
            }
        }
            .addOnFailureListener{
            binding.HintError.text="firebaseAuthWithGoogle: nok"
        }
    }
    fun login(){
        auth=FirebaseAuth.getInstance()
        val email=binding.etLogin.text.toString()
        val password=binding.etPass.text.toString()
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    binding.HintError.text="firebaseAuthWithEmail: ok"
                    popBackStack()
                }
            }
            .addOnFailureListener{ binding.HintError.text="firebaseAuthWithEmail: nok"}
    }
}