package com.rivaldi.githubuserapp.di.module.builder

import com.rivaldi.githubuserapp.ui.detail.DetailActivity
import com.rivaldi.githubuserapp.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class ActivityModuleBuilder {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeDetailActivity(): DetailActivity


}
