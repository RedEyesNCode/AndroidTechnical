package com.redeyesncode.androidtechnical.ui.activity.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.redeyesncode.androidtechnical.base.Event
import com.redeyesncode.androidtechnical.base.Resource
import com.redeyesncode.androidtechnical.data.PostCommentResponse
import com.redeyesncode.androidtechnical.data.PostsResponse
import com.redeyesncode.androidtechnical.repository.DefaultDashboardRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val dashboardRepo: DefaultDashboardRepo):ViewModel() {

    private val _userFeedResponse = MutableLiveData<Event<Resource<ArrayList<PostsResponse>>>>()
    val userFeedResponse : LiveData<Event<Resource<ArrayList<PostsResponse>>>> = _userFeedResponse


    private val _postCommentResponse = MutableLiveData<Event<Resource<ArrayList<PostCommentResponse>>>>()
    val postCommentResponse : LiveData<Event<Resource<ArrayList<PostCommentResponse>>>> = _postCommentResponse

    fun getAllPosts(){
        _userFeedResponse.postValue(Event(Resource.Loading()))
        viewModelScope.launch(Dispatchers.Main){
            val result = dashboardRepo.getAllPosts()
            _userFeedResponse.postValue(Event(result))
        }

    }

    fun getCommentPost(post_id:String){
        _postCommentResponse.postValue(Event(Resource.Loading()))
        viewModelScope.launch(Dispatchers.Main){
            val result = dashboardRepo.getPostComment(post_id)
            _postCommentResponse.postValue(Event(result))
        }
    }

}