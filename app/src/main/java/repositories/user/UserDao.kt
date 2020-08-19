package repositories.user

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {
    @Insert
    fun insert(user: UserResponse)

    @Delete
    fun delete(user: UserResponse)

    @Query("SELECT * FROM UserResponse limit 1")
    fun getFirst(): LiveData<UserResponse?>

}