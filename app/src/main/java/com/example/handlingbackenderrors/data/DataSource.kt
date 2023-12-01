package com.example.handlingbackenderrors.data

import com.example.handlingbackenderrors.domain.ErrorCode


class DataSource {
    private val list = ErrorCode.values()

    fun getData(): ErrorCode {
        return list.random()
    }
}