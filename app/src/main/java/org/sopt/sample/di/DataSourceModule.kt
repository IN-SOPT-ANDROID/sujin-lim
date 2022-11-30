package org.sopt.sample.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.sample.data.remote.source.RemoteDataSource
import org.sopt.sample.data.remote.source.RemoteDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun bindsRemoteDataSource(
        remoteDatasourceImpl: RemoteDataSourceImpl
    ): RemoteDataSource
}