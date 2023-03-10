package com.clintpauldev.daggerhiltinstrumentationtestingexample.di

import android.content.Context
import androidx.room.Room
import com.valor_paytech.task.repository.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent ::class)
object HiltTestApplication {

    @Provides
    @Named("test-db")
    fun provideInMemoryDb(@ApplicationContext context: Context) =
        Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        ).allowMainThreadQueries()
            .build()

}

