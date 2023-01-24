package com.example.moviemessager.ui.base

import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.viewbinding.ViewBinding

abstract class FragmentBaseNCMVVM<ViewModel : BaseViewModel, ViewBind : ViewBinding> :
    FragmentBaseMVVM<ViewModel, ViewBind>() {

    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = Navigation.findNavController(view)

        super.onViewCreated(view, savedInstanceState)
    }

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