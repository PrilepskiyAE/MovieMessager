package com.example.moviemessager.ui.base

import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.viewbinding.ViewBinding
import com.example.moviemessager.R
import com.example.moviemessager.ui.dialog.ErrorDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

abstract class FragmentBaseNCMVVM<ViewModel : BaseViewModel, ViewBind : ViewBinding> :
    FragmentBaseMVVM<ViewModel, ViewBind>() {
    private var errorDialog: ErrorDialog? = null
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
    fun showErrorDialog(
        title: String?,
        message: String?,
        showButton: Boolean,
        action: () -> Unit,
        cansel:() -> Unit={}
    ) {

        if (errorDialog == null) {

            errorDialog = ErrorDialog()
            errorDialog!!.erTitle = title ?: getString(R.string.Error_title)
            errorDialog!!.message = message ?: getString(R.string.Errorr_message)
            errorDialog!!.showButton=showButton

            errorDialog!!.action = {
                action()

            }

            errorDialog?.onDismissAction = {
                errorDialog = null

            }

            errorDialog?.onCancelAction = {
                errorDialog = null

//                if (showButton){
//                    showErrorDialog(title, message, showButton, action,cansel)
//
//                }
                cansel()

            }

            try {
                if (!errorDialog!!.isVisible && !errorDialog!!.isAdded)
                    errorDialog!!.show(childFragmentManager, "ERROR_DIALOG_TAG")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }
}