package com.juangranadilla.data.remote

interface RemoteDataSource {

}

class RemoteDataSourceImpl(
    private val service: ApiService
) : RemoteDataSource {


}