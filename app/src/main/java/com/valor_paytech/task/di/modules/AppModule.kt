package com.valor_paytech.task.di.modules

import android.content.Context
import androidx.room.Room
import com.valor_paytech.task.BuildConfig
import com.valor_paytech.task.repository.api.ApiServices
import com.valor_paytech.task.repository.db.AppDatabase
import com.valor_paytech.task.repository.db.cart.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    /**
     * Provides ApiServices client for Retrofit
     */
    @Singleton
    @Provides
    fun provideNewsService(): ApiServices {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServices::class.java)
    }


    /**
     * Provides app AppDatabase
     */
    @Singleton
    @Provides
    fun provideDb(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "valor0-db")
            .fallbackToDestructiveMigration().build()


    /**
     * Provides CartDao an object to access Cart table from Database
     */
    @Singleton
    @Provides
    fun provideCartDao(db: AppDatabase): UserDao = db.cartDao()


}
