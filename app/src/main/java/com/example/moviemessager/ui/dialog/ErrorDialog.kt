package com.example.moviemessager.ui.dialog

import android.content.DialogInterface
import android.view.View
import com.example.moviemessager.R
import com.example.moviemessager.databinding.ErrorDialogBinding
import com.example.moviemessager.ui.base.BaseDialog
import com.example.moviemessager.ui.base.viewBinding


class ErrorDialog(): BaseDialog<ErrorDialogBinding>() {

    override val dialogStyle: Int
        get() = R.style.AppTheme_FullScreenDialog

    override val binding: ErrorDialogBinding by viewBinding()

    var erTitle: String? = null
    var message: String? = null
    var showButton: Boolean = false
    var action: () -> Unit = {}

    var onDismissAction: () -> Unit = {}
    var onCancelAction: () -> Unit = {}

    override fun onView() {
        super.onView()
        with(binding) {

            errorTitle.text = erTitle ?: getString(R.string.Error_title)
            errorMessage.text = message ?: getString(R.string.Errorr_message)
            if (showButton){
                tryButton.visibility= View.VISIBLE
                binding.warningIcon.visibility=View.VISIBLE
            }else{
                tryButton.visibility= View.INVISIBLE
                binding.warningIcon.visibility=View.INVISIBLE
            }



        }
    }

    override fun onViewClick() {
        super.onViewClick()
        with(binding) {
            tryButton.setOnClickListener {
                action()
                dismiss()
                //TODO fix removing the dialog from backstack
            }
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        onDismissAction()
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        onCancelAction()
    }



}