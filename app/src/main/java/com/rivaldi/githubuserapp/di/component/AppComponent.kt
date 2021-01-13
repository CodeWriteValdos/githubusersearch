package com.rivaldi.githubuserapp.di.component

import android.app.Application
import com.rivaldi.App
import com.rivaldi.githubuserapp.di.module.AppModule
import com.rivaldi.githubuserapp.di.module.DataModule
import com.rivaldi.githubuserapp.di.module.ViewModelModule
import com.rivaldi.githubuserapp.di.module.builder.ActivityModuleBuilder
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        DataModule::class,
        ActivityModuleBuilder::class,
        ViewModelModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun app(app: Application): Builder

        fun build(): AppComponent
    }
}