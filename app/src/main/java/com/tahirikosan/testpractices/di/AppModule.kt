package com.tahirikosan.testpractices.di

import android.content.Context
import androidx.room.PrimaryKey
import androidx.room.Room
import com.tahirikosan.testpractices.data.local.ShoppingDao
import com.tahirikosan.testpractices.other.Constants.BASE_URL
import com.tahirikosan.testpractices.other.Constants.DATABASE_NAME
import com.tahirikosan.testpractices.data.local.ShoppingDatabase
import com.tahirikosan.testpractices.data.remote.PixabayAPI
import com.tahirikosan.testpractices.repositories.ShoppingRepository
import com.tahirikosan.testpractices.repositories.ShoppingRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideShoppingDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        ShoppingDatabase::class.java, DATABASE_NAME
    ).build()


    @Singleton
    @Provides
    fun provideShoppingRepository(
        shoppingDao: ShoppingDao,
        pixabayAPI: PixabayAPI
    ): ShoppingRepository = ShoppingRepositoryImpl(shoppingDao = shoppingDao, pixabayAPI = pixabayAPI) as ShoppingRepository

    @Singleton
    @Provides
    fun provideShoppingDao(
        database: ShoppingDatabase
    ) = database.shoppingDao()

    @Singleton
    @Provides
    fun providePixabayAPI(): PixabayAPI =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(PixabayAPI::class.java)
}