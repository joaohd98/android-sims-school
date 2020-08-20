package repositories

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import repositories.user.UserDao
import repositories.user.UserResponse

@Database(entities = [UserResponse::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        var instance: AppDatabase? = null

        fun getAppDataBase(context: Context): AppDatabase? {
            if (instance == null){
                synchronized(AppDatabase::class) {
                    instance = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "AppDatabase").build()
                }
            }

            return instance
        }
    }

}

