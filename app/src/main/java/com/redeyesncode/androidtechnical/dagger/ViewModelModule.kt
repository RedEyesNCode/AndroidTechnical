package com.redeyesncode.androidtechnical.dagger

import com.redeyesncode.androidtechnical.ui.activity.viewmodels.MainViewModel
import com.redeyesncode.androidtechnical.repository.DefaultDashboardRepo
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {
    @Provides
    fun provideMainViewModel(repository: DefaultDashboardRepo): MainViewModel {
        return MainViewModel(dashboardRepo = repository)
    }
}