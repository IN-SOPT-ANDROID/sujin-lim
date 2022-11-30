package org.sopt.sample.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.sample.data.remote.repository.RemoteRepository
import org.sopt.sample.data.remote.repository.RemoteRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindsRemoteRepository(
        remoteRepositoryImpl: RemoteRepositoryImpl
    ): RemoteRepository
}