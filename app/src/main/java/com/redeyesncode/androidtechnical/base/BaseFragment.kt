package com.redeyesncode.androidtechnical.base

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.redeyesncode.androidtechnical.R

open class BaseFragment: Fragment() {

    private var loadingDialog: AlertDialog? = null

    var fragmentContext: Context?=null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentContext = context

    }
    fun showToast(message: String) {
        // Show a toast message
        Toast.makeText(fragmentContext, message, Toast.LENGTH_SHORT).show()
    }


    fun showSnackbar(message: String,rootView: View) {
        // Show a Snackbar message
        Snackbar.make(rootView, message, Snackbar.LENGTH_SHORT).show()
    }

    fun showCustomDialog(title: String, message: String) {
        // Show a custom dialog with an OK button
        val dialogBuilder = AlertDialog.Builder(fragmentContext!!)
        dialogBuilder.setTitle(title)
        dialogBuilder.setMessage(message)
        dialogBuilder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }
        val dialog = dialogBuilder.create()
        dialog.show()
    }
    fun showLoadingDialog() {
        val builder = AlertDialog.Builder(fragmentContext!!)
        builder.setCancelable(false)
        builder.setView(R.layout.layout_loading_dialog)
        loadingDialog = builder.create()
        loadingDialog?.show()
    }

    fun hideLoadingDialog() {
        loadingDialog?.dismiss()
    }
}