package com.example.momentcommerce.di

import android.content.Context
import androidx.room.Room
import com.example.momentcommerce.data.source.file.ProductJsonSourceImpl
import com.example.momentcommerce.data.source.file.ProductsJsonSource
import com.example.momentcommerce.data.source.room.CommerceDao
import com.example.momentcommerce.data.source.room.CommerceDatabase
import com.example.momentcommerce.data.source.room.CommerceRoomSource
import com.example.momentcommerce.data.source.room.CommerceRoomSourceImpl
import com.example.momentcommerce.util.Constants.DATABASE_NAME
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideProductJsonSource(
        @ApplicationContext context: Context
    ): ProductsJsonSource {
        return ProductJsonSourceImpl(context)
    }

    @Provides
    @Singleton
    fun providesNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
    }



    @Provides
    @Singleton
    fun provideCommerceDatabase(
        @ApplicationContext context: Context
    ): CommerceDatabase = Room
        .databaseBuilder(
            context,
            CommerceDatabase::class.java,
            DATABASE_NAME
        )
        .build()

    @Provides
    @Singleton
    fun provideCommerceDao(satellitesDatabase: CommerceDatabase): CommerceDao =
        satellitesDatabase.commerceDao()

    @Provides
    @Singleton
    fun provideRoomDataSource(
        commerceDao: CommerceDao
    ): CommerceRoomSource =
        CommerceRoomSourceImpl(commerceDao)
}