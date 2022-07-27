package com.tahirikosan.testpractices.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tahirikosan.testpractices.data.local.ShoppingItem
import com.tahirikosan.testpractices.data.remote.responses.ImageResponse
import com.tahirikosan.testpractices.other.Resource

class FakeShoppingRepository: ShoppingRepository {
    // Acts as database table
    private  val shoppingItems = mutableListOf<ShoppingItem>()

    private val observableShoppingItems = MutableLiveData<List<ShoppingItem>>(shoppingItems)
    private val observableTotalPrice = MutableLiveData<Float>()

    private var shouldReturnNetworkError = false;
    fun setShouldReturnNetworkError(value: Boolean){
        shouldReturnNetworkError = value;
    }

    fun refreshLiveData(){
        observableShoppingItems.postValue(shoppingItems)
        observableTotalPrice.postValue(getTotalPrice())
    }

    fun getTotalPrice(): Float{
        return shoppingItems.sumByDouble { it.price.toDouble() }.toFloat()
    }

    override fun insertShoppingItem(shoppingItem: ShoppingItem): Long {
        shoppingItems.add(shoppingItem)
        refreshLiveData()
        return 1
    }

    override fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        shoppingItems.remove(shoppingItem)
        refreshLiveData()
    }

    override fun observeAllShoppingItems(): LiveData<List<ShoppingItem>> {
        return observableShoppingItems
    }

    override fun observeTotalPrice(): LiveData<Float> {
        return observableTotalPrice
    }

    override suspend fun searchForImage(imageQuery: String): Resource<ImageResponse> {
        return if(shouldReturnNetworkError){
            Resource.error("Network error", null)
        }else{
            Resource.success(ImageResponse(listOf(), 1,1))
        }
    }
}