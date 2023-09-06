package com.redeyesncode.androidtechnical.ui.activity.fragments

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.disklrucache.DiskLruCache
import com.redeyesncode.androidtechnical.ui.activity.viewmodels.MainViewModel
import com.redeyesncode.androidtechnical.ui.activity.adapters.PostAdapter
import com.redeyesncode.androidtechnical.base.AndroidApp
import com.redeyesncode.androidtechnical.base.BaseFragment
import com.redeyesncode.androidtechnical.base.Event
import com.redeyesncode.androidtechnical.base.getNetworkResponse
import com.redeyesncode.androidtechnical.base.putNetworkResponse
import com.redeyesncode.androidtechnical.caching.AndroidDiskCacheManager
import com.redeyesncode.androidtechnical.caching.CacheKey
import com.redeyesncode.androidtechnical.caching.CacheKeyGenerator
import com.redeyesncode.androidtechnical.data.PostsResponse
import com.redeyesncode.androidtechnical.databinding.FragmentPostBinding
import com.redeyesncode.androidtechnical.ui.activity.CommentActivity
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FragmentPost : BaseFragment(),PostAdapter.onAdapterClick {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding:FragmentPostBinding

    @Inject
    lateinit var mainViewModel: MainViewModel

    private lateinit var diskCacheInstance: DiskLruCache


    override fun onPostClick(position: Int, data: PostsResponse) {
        // Move to the post details activity
        val commentIntent = Intent(requireContext(),CommentActivity::class.java)
        commentIntent.putExtra("POST_ID",data.id.toString())
        commentIntent.putExtra("USER_ID",data.userId.toString())
        commentIntent.putExtra("TITLE",data.title.toString())
        commentIntent.putExtra("BODY",data.body.toString())
        startActivity(commentIntent)



    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostBinding.inflate(LayoutInflater.from(requireContext()))
        (activity?.application as AndroidApp).getDaggerComponent().injectPostFragment(this@FragmentPost)
        attachObservers()
        setupCaching()


        if(isInternetAvailable(requireContext())){
            initialApiCall()
        }else{
            // get the caching data
            showSnackbar("No Internet Available !",binding.root)
            try {
                val data = diskCacheInstance.getNetworkResponse<ArrayList<PostsResponse>>(CacheKeyGenerator.generateCacheKey(CacheKey.USER_POST_CACHE))
                binding.recvUsePosts.apply {
                    adapter = PostAdapter(requireContext(),data!!,this@FragmentPost)
                    layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
                }
            }catch (e:Exception){
                e.printStackTrace()
                showToast("No Cached Data !")
            }


        }



        return binding.root
    }

    private fun initialApiCall() {
        mainViewModel.getAllPosts()


    }
    fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network)

        return networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
    }

    private fun setupCaching() {
        val androidCache = AndroidDiskCacheManager
        androidCache.initialize(requireContext())
        diskCacheInstance = androidCache.getDiskLruCache()


    }

    private fun attachObservers() {
        mainViewModel.userFeedResponse.observe(viewLifecycleOwner,Event.EventObserver(
            onLoading = {
                        showLoadingDialog()

            },
            onError = {
                      hideLoadingDialog()
                showSnackbar(it,binding.root)
            },
            onSuccess = {
                hideLoadingDialog()
                // populate the recycler view
                diskCacheInstance.putNetworkResponse(CacheKeyGenerator.generateCacheKey(CacheKey.USER_POST_CACHE),it)

                binding.recvUsePosts.apply {
                    adapter = PostAdapter(requireContext(),it,this@FragmentPost)
                    layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
                }



            }

        ))

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentPost().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}