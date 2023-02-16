package com.valor_paytech.task.repository.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.valor_paytech.task.repository.db.cart.UserDao
import com.valor_paytech.task.repository.model.PostsDbModel


/**
 * App Database
 * Define all entities and access doa's here/ Each entity is a table.
 */
@Database(entities =[User::class, PostsDbModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun cartDao(): UserDao
}