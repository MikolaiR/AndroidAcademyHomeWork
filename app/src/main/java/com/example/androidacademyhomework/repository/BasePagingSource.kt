package com.example.androidacademyhomework.repository

import androidx.paging.PagingSource
import retrofit2.HttpException
import java.io.IOException

@Suppress("UNCHECKED_CAST")
abstract class BasePagingSource<Key : Number, Value : Any> : PagingSource<Key, Value>() {
    var position: Key? = null
    var totalPages: Key? = null
    var startingPage: Key = 1 as Key

    override suspend fun load(params: LoadParams<Key>): LoadResult<Key, Value> {
        position = params.key ?: startingPage
        return try {
            LoadResult.Page(
                data = getPagingData(),
                prevKey = if (position == startingPage) null else position?.toInt()
                    ?.minus(1) as Key,
                nextKey = if (position == totalPages) {
                    null
                } else position?.toInt()?.plus(1) as Key
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    abstract suspend fun getPagingData(): List<Value>
}

