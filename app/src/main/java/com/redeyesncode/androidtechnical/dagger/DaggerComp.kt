package com.redeyesncode.androidtechnical.dagger

import com.redeyesncode.androidtechnical.ui.activity.MainActivity
import com.redeyesncode.androidtechnical.ui.activity.fragments.FragmentFavorites
import com.redeyesncode.androidtechnical.ui.activity.fragments.FragmentPost
import com.redeyesncode.androidtechnical.network.RetrofitInstance
import com.redeyesncode.androidtechnical.ui.activity.CommentActivity
import dagger.Component
import org.w3c.dom.Comment

@Component(modules = [RetrofitInstance::class, ViewModelModule::class, RepoModule::class])
interface DaggerComp {

    fun injectDashboard(mainActivity: MainActivity)

    fun injectPostFragment(post: FragmentPost)

    fun injectFavFragment(favorites: FragmentFavorites)
    fun injectCommentActivity(commentActivity: CommentActivity)

}