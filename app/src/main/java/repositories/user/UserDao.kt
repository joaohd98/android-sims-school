package repositories.user

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Insert
    fun insert(user: UserResponse)

    @Delete
    fun delete(user: UserResponse)

    @Query("SELECT * FROM user limit 1")
    fun getFirst(): LiveData<UserResponse?>

    @Query("UPDATE user set profile_picture = :uri")
    fun updateProfilePicture(uri: String)

    @Query("DELETE FROM user")
    fun removeUser()

}