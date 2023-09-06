package com.redeyesncode.androidtechnical.ui.activity.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.redeyesncode.androidtechnical.R
import com.redeyesncode.androidtechnical.base.AndroidApp
import com.redeyesncode.androidtechnical.base.BaseFragment
import com.redeyesncode.androidtechnical.databinding.FragmentFavoritesBinding
import com.redeyesncode.androidtechnical.ui.activity.adapters.FavPostAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentFavorites.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentFavorites : BaseFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding:FragmentFavoritesBinding

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
        // Inflate the layout for this fragment

        binding = FragmentFavoritesBinding.inflate(layoutInflater)

        fetchFavPosts()

        return binding.root
    }

    private fun fetchFavPosts() {
        GlobalScope.launch(Dispatchers.IO) {
            val comments = AndroidApp.database.commentDao().getAllComments()

            // Update the UI on the main thread


            if(comments.isEmpty()){
                activity?.runOnUiThread {
                    showToast("No Favorites posts")

                }

            }else{
                activity?.runOnUiThread {
                    binding.recvFavPosts.apply {
                        adapter = FavPostAdapter(requireContext(),comments)
                        layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
                    }
                }


            }
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentFavorites.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentFavorites().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}