package com.example.momentcommerce.di

import com.example.momentcommerce.data.repo.ProductRepositoryImpl
import com.example.momentcommerce.data.repo.ProductsRepository
import com.example.momentcommerce.data.source.file.ProductsJsonSource
import com.example.momentcommerce.data.source.room.CommerceRoomSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideProductRepository(
        productJsonSource: ProductsJsonSource,
        productRoomSource : CommerceRoomSource
        ): ProductsRepository {
        return ProductRepositoryImpl(productJsonSource, productRoomSource)
    }

}