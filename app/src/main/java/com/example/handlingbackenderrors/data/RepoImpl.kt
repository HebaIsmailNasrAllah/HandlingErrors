package com.example.handlingbackenderrors.data

import com.example.handlingbackenderrors.domain.IRepo
import com.example.handlingbackenderrors.domain.MyCustomException
import com.example.handlingbackenderrors.domain.toCustomException

class RepoImpl(private val dataSource: DataSource): IRepo {

    override fun getBackEndResponse(): MyCustomException{
        val data = dataSource.getData()
        throw dataSource.getData().toCustomException(data.name)
    }
}