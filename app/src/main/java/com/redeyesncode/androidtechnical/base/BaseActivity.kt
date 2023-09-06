package com.redeyesncode.androidtechnical.base

import android.content.Context
import android.content.DialogInterface
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.redeyesncode.androidtechnical.R
import java.lang.Exception

open class BaseActivity: AppCompatActivity() {
    private var loadingDialog: AlertDialog? = null
    private var noInternetDialog: AlertDialog? = null
    private lateinit var networkCallback: ConnectivityManager.NetworkCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupNetworkCallBack()
    }

    private fun setupNetworkCallBack() {

        // Initialize network callback
        runOnUiThread {
            networkCallback = object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    // Internet connection is available, dismiss the dialog if it's showing
//                    dismissNoInternetDialog()
                    showToast("Internet Available!")

                }

                override fun onLost(network: Network) {
                    super.onLost(network)
                    // Internet connection is lost, show the dialog
//                    showNoInternetDialog()
                    showSnackbar("Internet Not Available !")
                }
            }
            val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkRequest = NetworkRequest.Builder().build()
            connectivityManager.registerNetworkCallback(networkRequest, networkCallback)
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }
    override fun onPause() {
        super.onPause()

        runOnUiThread {
            // Unregister the network callback to prevent memory leaks
            val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            connectivityManager.unregisterNetworkCallback(networkCallback)
        }


    }
    override fun onResume() {
        super.onResume()

        runOnUiThread {
            if (!isInternetAvailable(this)) {
                // Internet is not available, show the dialog
//                showNoInternetDialog()
                showSnackbar("Internet Not Available !")
            } else {
                // Internet is available, dismiss the dialog if it's showing
//                dismissNoInternetDialog()
                showSnackbar("Internet Available !")
            }
        }
    }
    private fun showNoInternetDialog() {
        if (noInternetDialog == null) {
            val builder = AlertDialog.Builder(this)
            val dialogView = layoutInflater.inflate(R.layout.dialog_no_internet, null)
            builder.setView(dialogView)
            builder.setCancelable(false)

            // Handle the "Retry" button click action
            builder.setPositiveButton("Retry", null)

            noInternetDialog = builder.create()
            noInternetDialog?.setOnShowListener { dialog ->
                val retryButton = noInternetDialog?.getButton(DialogInterface.BUTTON_POSITIVE)
                retryButton?.setOnClickListener {
                    if (isInternetAvailable(this)) {
                        // Internet is available, dismiss the dialog
                        dialog.dismiss()
                    } else {
                        // Internet is still not available, show an error message if needed
                        // You can show a message to inform the user that the internet is still not available.
                        // Optionally, you can also attempt reconnection here.
                        showToast("Internet still not available.")
                    }
                }
            }
        }
        noInternetDialog?.show()
    }


    private fun dismissNoInternetDialog() {
        noInternetDialog?.dismiss()
        noInternetDialog = null
    }


    fun showToast(message: String) {
        // Show a toast message
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun showSnackbar(message: String) {
        // Show a Snackbar message
        val rootView = findViewById<View>(android.R.id.content)
        Snackbar.make(rootView, message, Snackbar.LENGTH_SHORT).show()
    }

    fun showCustomDialog(title: String, message: String) {
        // Show a custom dialog with an OK button
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle(title)
        dialogBuilder.setMessage(message)
        dialogBuilder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }
        val dialog = dialogBuilder.create()
        dialog.show()
    }

    fun showLoadingDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(false)
        builder.setView(R.layout.layout_loading_dialog)
        loadingDialog = builder.create()
        loadingDialog?.show()
    }

    fun hideLoadingDialog() {
        loadingDialog?.dismiss()
    }

    fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network)

        return networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
    }
}