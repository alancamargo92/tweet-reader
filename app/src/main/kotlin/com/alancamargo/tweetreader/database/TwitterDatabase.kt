package com.alancamargo.tweetreader.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.alancamargo.tweetreader.model.Tweet

@Database(entities = [Tweet::class], version = 2, exportSchema = false)
abstract class TwitterDatabase : RoomDatabase() {

    abstract fun tweetDao(): TweetDao

    companion object {
        private var instance: TwitterDatabase? = null

        @JvmStatic
        @Synchronized
        fun getInstance(context: Context): TwitterDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(context, TwitterDatabase::class.java, "twitter_database")
                    .fallbackToDestructiveMigration()
                    .build()
            }

            return instance!!
        }
    }

}