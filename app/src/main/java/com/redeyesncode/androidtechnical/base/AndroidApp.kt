package com.redeyesncode.androidtechnical.base

import android.app.Application
import androidx.room.Room
import com.redeyesncode.androidtechnical.dagger.DaggerComp
import com.redeyesncode.androidtechnical.dagger.DaggerDaggerComp
import com.redeyesncode.androidtechnical.network.RetrofitInstance
import com.redeyesncode.androidtechnical.room.AppDatabase

class AndroidApp: Application() {
    companion object {
        lateinit var database: AppDatabase
    }
    lateinit var daggerComp: DaggerComp
    override fun onCreate() {
        super.onCreate()
        // need to create all the modules in the base application class.

        database = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "my_database")
            .build()
        daggerComp = DaggerDaggerComp.builder().retrofitInstance(RetrofitInstance()).build()


    }

    fun getDaggerComponent(): DaggerComp {
        return daggerComp

    }
}