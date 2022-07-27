package com.tahirikosan.testpractices.repositories

import androidx.lifecycle.LiveData
import androidx.room.Query
import com.tahirikosan.testpractices.data.local.ShoppingItem
import com.tahirikosan.testpractices.data.remote.responses.ImageResponse
import com.tahirikosan.testpractices.other.Resource

interface ShoppingRepository {

    fun insertShoppingItem(shoppingItem: ShoppingItem): Long
    fun deleteShoppingItem(shoppingItem: ShoppingItem)
    fun observeAllShoppingItems(): LiveData<List<ShoppingItem>>
    fun observeTotalPrice(): LiveData<Float>
    suspend fun searchForImage(imageQuery: String): Resource<ImageResponse>
}