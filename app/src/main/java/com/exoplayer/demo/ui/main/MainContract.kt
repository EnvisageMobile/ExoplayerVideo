
package com.exoplayer.demo.ui.main

import com.exoplayer.demo.data.network.model.ApiVideo

interface MainContract {

  interface Presenter {

    fun fetchSampleVideos()

    fun deactivate()

    fun showVideoScreen(videoUrl: String)
  }

  interface View {

    fun renderVideos(videos: List<ApiVideo>)

    fun showErrorMessage()
  }
}