package com.example.havucwallpapernewversion.features.categories.data.repository

import com.example.havucwallpapernewversion.features.categories.data.local.CategoryLocalDS
import com.example.havucwallpapernewversion.features.categories.data.remote.CategoryRemoteDS
import com.example.havucwallpapernewversion.features.categories.domain.mapper.toCategory
import com.example.havucwallpapernewversion.features.categories.domain.mapper.toCategoryEntity
import com.example.havucwallpapernewversion.features.categories.domain.model.Category
import com.example.havucwallpapernewversion.features.images.domain.model.Image
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val categoryRemoteDS: CategoryRemoteDS,
    private val categoryLocalDS: CategoryLocalDS
) : CategoryRepository {
    override fun getCategories(): Flow<Result<List<Category>>> = flow {
        categoryLocalDS.getCategories().collect {
            if (it.isEmpty()) {
                getRemoteCategories()
            }
            emit(Result.success(it.map {
                it.toCategory()
            }))
        }
    }

    private suspend fun getRemoteCategories() {
        withContext(Dispatchers.IO) {
            val response = categoryRemoteDS.getCategories()
            response.data.map { getCategory ->
                categoryLocalDS.insertCategory(getCategory.toCategoryEntity())
            }
        }

    }

    private fun getMockCategories(): ArrayList<Category> {
        val jsonLocation = readJsonFromAssets("categories.json")
        val jsonobject = JSONObject(jsonLocation)
        val getCategories = ArrayList<Any>()
        val jsonList = jsonobject.getJSONArray("image") as JSONArray
        for (i in 0 until jsonList.length()) {
            val jb = jsonList[i] as JSONObject
            val categoryQuery = jb.getString("categoryQuery")
            val categoryTitle = jb.getString("categoryTitle")
            val count = jb.getInt("count")
            val image = jb.getString("image")

            val images = Category(categoryQuery, categoryTitle, count, image)
            getCategories.add(images)

        }
        return getCategories as ArrayList<Category>
    }

    private fun readJsonFromAssets(fileName: String): String {
        val inputStream = javaClass.classLoader?.getResourceAsStream("assets/$fileName")
        val reader = BufferedReader(InputStreamReader(inputStream))
        val stringBuilder = StringBuilder()

        var line: String?
        try {
            while (reader.readLine().also { line = it } != null) {
                stringBuilder.append(line)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            inputStream?.close()
        }

        return stringBuilder.toString()
    }
}