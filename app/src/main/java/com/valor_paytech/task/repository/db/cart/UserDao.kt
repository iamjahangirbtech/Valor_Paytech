package com.valor_paytech.task.repository.db.cart

import androidx.room.*
import com.valor_paytech.task.repository.db.User
import com.valor_paytech.task.repository.model.PostsDbModel
import com.valor_paytech.task.repository.model.PostsModel

@Dao
abstract class UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(item: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertPost(item: PostsDbModel)

    @Query("SELECT * from post_db_table where id= :id")
    abstract fun getPostData(id: Int): PostsDbModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(item: List<User>)

    @Query("DELETE FROM user_table WHERE id = :id")
    abstract fun deleteByUserId(id: String)

    @Update
    abstract fun update(item: User)

    @Delete
    abstract fun delete(item: User)

    // all the data of database.
    @Query("SELECT * FROM user_table")
    abstract fun getAllUserItems(): List<User>
}