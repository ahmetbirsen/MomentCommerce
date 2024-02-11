package com.example.momentcommerce.di

import android.content.Context
import com.example.momentcommerce.data.source.ProductJsonSourceImpl
import com.example.momentcommerce.data.source.ProductsJsonSource
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
}