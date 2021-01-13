package com.rivaldi.githubuserapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rivaldi.githubuserapp.data.model.User

const val DB_NAME = "db_github"

@Database(
    entities = [User::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

}