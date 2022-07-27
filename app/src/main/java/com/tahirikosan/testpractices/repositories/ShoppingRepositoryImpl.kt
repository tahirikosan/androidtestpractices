package com.tahirikosan.testpractices.repositories

import androidx.lifecycle.LiveData
import com.tahirikosan.testpractices.data.local.ShoppingDao
import com.tahirikosan.testpractices.data.local.ShoppingItem
import com.tahirikosan.testpractices.data.remote.PixabayAPI
import com.tahirikosan.testpractices.data.remote.responses.ImageResponse
import com.tahirikosan.testpractices.other.Resource
import java.lang.Exception
import javax.inject.Inject

class ShoppingRepositoryImpl @Inject constructor(
    private val shoppingDao: ShoppingDao,
    private val pixabayAPI: PixabayAPI
): ShoppingRepository {


    override fun insertShoppingItem(shoppingItem: ShoppingItem): Long {
        return shoppingDao.insertShoppingItem(shoppingItem)
    }

    override fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        return shoppingDao.deleteShoppingItem(shoppingItem)
    }

    override fun observeAllShoppingItems(): LiveData<List<ShoppingItem>> {
        return shoppingDao.observeAllShoppingItems()
    }

    override fun observeTotalPrice(): LiveData<Float> {
        return shoppingDao.observeTotalPrice()
    }

    override suspend fun searchForImage(imageQuery: String): Resource<ImageResponse> {
        return try {
            val response = pixabayAPI.searchForImages(imageQuery)
            if(response.isSuccessful){
                response.body()?.let {
                    return@let Resource.success(it)
                }?: Resource.error("An unknown error occured", null)
            }else{
                Resource.error("An unknown error occured", null)
            }
        }catch (e: Exception){
            Resource.error("Couldn't reach the server. Check your internet connection.", null)
        }
    }
}