

package com.exoplayer.demo.data.network.api

import com.exoplayer.demo.data.network.model.ApiResponse
import io.reactivex.Single
import retrofit2.http.GET

interface CloudinaryApi {

  @GET("demo/video/list/samples.json")
  fun fetchVideos(): Single<ApiResponse>
}