package com.example.moviemessager.ui.base

import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.viewbinding.ViewBinding
import com.example.moviemessager.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

abstract class FragmentBaseNCMVVM<ViewModel : BaseViewModel, ViewBind : ViewBinding> :
    FragmentBaseMVVM<ViewModel, ViewBind>() {

    private lateinit var navController: NavController
  //  lateinit var auth: FirebaseAuth
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = Navigation.findNavController(view)

        super.onViewCreated(view, savedInstanceState)

    }

//protected fun isAuth(){
//    auth= Firebase.auth
//    if (auth.currentUser==null){
//        navigateFragment(R.id.loginFragment)
//    }
//}


    protected fun popBackStack() {
        navController.popBackStack()
    }

    protected fun popBackStack(destinationId: Int) {
        navController.popBackStack(destinationId,true)
    }

    protected fun navigateFragment(destinationId: Int, arg: Bundle? = null) {
        navController.navigate(destinationId, arg)
    }

    protected fun navigateFragment(destinations: NavDirections) {
        navController.navigate(destinations)
    }

}