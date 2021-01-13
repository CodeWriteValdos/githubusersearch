package com.rivaldi.githubuserapp.di.module

import androidx.lifecycle.ViewModel
import com.rivaldi.githubuserapp.di.scope.ViewModelKey
import com.rivaldi.githubuserapp.ui.detail.DetailViewModel
import com.rivaldi.githubuserapp.ui.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun bindDetailViewModel(viewModel: DetailViewModel): ViewModel

}
