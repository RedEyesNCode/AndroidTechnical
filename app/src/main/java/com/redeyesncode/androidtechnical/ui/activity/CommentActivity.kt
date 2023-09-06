package com.redeyesncode.androidtechnical.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.disklrucache.DiskLruCache
import com.redeyesncode.androidtechnical.R
import com.redeyesncode.androidtechnical.base.AndroidApp
import com.redeyesncode.androidtechnical.base.BaseActivity
import com.redeyesncode.androidtechnical.base.Event
import com.redeyesncode.androidtechnical.base.putNetworkResponse
import com.redeyesncode.androidtechnical.caching.CacheKey
import com.redeyesncode.androidtechnical.caching.CacheKeyGenerator
import com.redeyesncode.androidtechnical.databinding.ActivityCommentBinding
import com.redeyesncode.androidtechnical.room.PostTable
import com.redeyesncode.androidtechnical.ui.activity.adapters.CommentAdapter
import com.redeyesncode.androidtechnical.ui.activity.adapters.PostAdapter
import com.redeyesncode.androidtechnical.ui.activity.viewmodels.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class CommentActivity : BaseActivity() {

    private lateinit var binding:ActivityCommentBinding
    @Inject
    lateinit var mainViewModel: MainViewModel

    private lateinit var diskCacheInstance: DiskLruCache
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityCommentBinding.inflate(layoutInflater)
        (application as AndroidApp).getDaggerComponent().injectCommentActivity(this@CommentActivity)

        attachObservers()
        initClicks()
        if(isInternetAvailable(this@CommentActivity)) {
            initialApiCall()
        }else{
            showToast("No Internet Available !")
        }

        setContentView(binding.root)
    }

    private fun initClicks() {

        binding.ivBack.setOnClickListener {
            onBackPressed()
        }
        binding.fabFavorite.setOnClickListener {
            // save the post obj.
            val userID = intent.getStringExtra("USER_ID")
            val postId = intent.getStringExtra("POST_ID")
            val body = intent.getStringExtra("BODY")
            val title = intent.getStringExtra("TITLE")

            val post = PostTable(userId = userID!!.toInt(), apiId = postId!!.toInt(),body=  body!!, title = title!!)
            GlobalScope.launch(Dispatchers.IO) {
                AndroidApp.database.commentDao().insertPost(post)
            }

        }


    }

    private fun initialApiCall() {
        mainViewModel.getCommentPost(intent.getStringExtra("POST_ID")!!)
    }

    private fun attachObservers() {

        mainViewModel.postCommentResponse.observe(this, Event.EventObserver(
            onLoading = {
                showLoadingDialog()

            },
            onError = {
                hideLoadingDialog()
                showSnackbar(it)
            },
            onSuccess = {
                hideLoadingDialog()
                // populate the recycler view

                binding.recvPostComment.apply {
                    adapter = CommentAdapter(this@CommentActivity,it)
                    layoutManager = LinearLayoutManager(this@CommentActivity,LinearLayoutManager.VERTICAL,false)
                }

            }

        ))


    }
}