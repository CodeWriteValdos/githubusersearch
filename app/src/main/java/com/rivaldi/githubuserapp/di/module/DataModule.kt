package com.rivaldi.githubuserapp.di.module

import android.content.Context
import androidx.room.Room
import com.rivaldi.githubuserapp.data.local.AppDatabase
import com.rivaldi.githubuserapp.data.local.DB_NAME
import com.rivaldi.githubuserapp.data.local.UserDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room
            .databaseBuilder(context.applicationContext, AppDatabase::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideDao(database: AppDatabase): UserDao {
        return database.userDao()
    }
}
