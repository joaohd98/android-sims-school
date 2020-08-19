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

//companion object {
//    @Volatile private var instance: NoteDatabase? = null
//    private val LOCK = Any()
//
//    operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
//        instance ?: buildDatabase(context).also { instance = it}
//    }
//
//    private fun buildDatabase(context: Context) =
//        Room.databaseBuilder(context, NoteDatabase::class.java, "note_database")
//            .fallbackToDestructiveMigration()
//            .addCallback(object : RoomDatabase.Callback() {
//                override fun onCreate(db: SupportSQLiteDatabase) {
//                    super.onCreate(db)
//                    PopulateDbAsyncTask(instance?.noteDao()!!).execute()
//                }
//            })
//            .build()
//
//    class PopulateDbAsyncTask constructor(private val noteDao: NoteDao) : AsyncTask<Void, Void?, Void?>() {
//        override fun doInBackground(vararg params: Void): Void? {
//            noteDao.insert(Note("Title 1", "Description 1", 1))
//            noteDao.insert(Note("Title 2", "Description 2", 2))
//            noteDao.insert(Note("Title 3", "Description 3", 3))
//            noteDao.insert(Note("Title 4", "Description 4", 4))
//
//            return null
//        }
//    }
//}