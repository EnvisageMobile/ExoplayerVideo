
package com.exoplayer.demo.data.network

import com.exoplayer.demo.data.network.api.CloudinaryApi
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


object VideosService {

  private const val CLOUDINARY_BASE_URL = "https://res.cloudinary.com/"
 // private const val CLOUDINARY_BASE_URL = ""

  fun fetchVideos() = createCloudinaryVideoService().fetchVideos()

  private fun createRetrofitInstance() = Retrofit.Builder()
      .baseUrl(CLOUDINARY_BASE_URL)
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .addConverterFactory(GsonConverterFactory.create())
      .build()

  private fun createCloudinaryVideoService() = createRetrofitInstance().create(CloudinaryApi::class.java)
}