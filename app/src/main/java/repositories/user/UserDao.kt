package repositories.user

import androidx.room.*

@Dao
interface UserDao {
    @Insert
    fun insert(user: UserResponse)

    @Delete
    fun delete(user: UserResponse)

}