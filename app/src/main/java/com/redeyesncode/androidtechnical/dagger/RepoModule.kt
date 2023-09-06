package com.redeyesncode.androidtechnical.dagger

import com.redeyesncode.androidtechnical.repository.DefaultDashboardRepo
import dagger.Module
import dagger.Provides


@Module
class RepoModule {

    @Provides
    fun provideDashboardRepo(): DefaultDashboardRepo {

        return DefaultDashboardRepo()
    }

}