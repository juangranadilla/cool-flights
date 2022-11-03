package com.juangranadilla.data.repository

import com.juangranadilla.data.remote.RemoteDataSource
import com.juangranadilla.domain.repository.Repository

class RepositoryImpl(
    private val remote: RemoteDataSource
) : Repository {

}