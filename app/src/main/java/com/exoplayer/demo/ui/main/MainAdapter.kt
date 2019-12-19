

package com.exoplayer.demo.ui.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.exoplayer.demo.R
import com.exoplayer.demo.data.network.model.ApiVideo
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.Subject

import kotlinx.android.synthetic.main.video_item_view.view.*
import java.util.concurrent.TimeUnit

class MainAdapter : RecyclerView.Adapter<MainAdapter.VideoViewHolder>() {

  companion object {
    const val CLICK_THROTTLE_WINDOW_MILLIS = 300L
  }

  private val onVideoClickSubject: Subject<ApiVideo> = BehaviorSubject.create()

  private var videos: List<ApiVideo> = ArrayList()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
    val itemView = LayoutInflater.from(parent.context)
        .inflate(R.layout.video_item_view, parent, false)
    return VideoViewHolder(itemView, onVideoClickSubject)
  }

  override fun onBindViewHolder(holder: VideoViewHolder, position: Int) = holder.setVideo(videos[position])

  override fun getItemCount() = videos.size

  fun onVideosUpdate(videos: List<ApiVideo>) {
    this.videos = videos
    notifyDataSetChanged()
  }

  fun onItemClick() = onVideoClickSubject.throttleFirst(CLICK_THROTTLE_WINDOW_MILLIS, TimeUnit.MILLISECONDS)

  class VideoViewHolder(val view: View,
                        private val clickSubject: Subject<ApiVideo>) : RecyclerView.ViewHolder(view) {

    private lateinit var video: ApiVideo

    fun setVideo(video: ApiVideo) {
      this.video = video
      itemView.tv_main_video_title.text = video.publicId
      itemView.main_video_item_container.setOnClickListener { onMovieClick() }
    }

    private fun onMovieClick() = clickSubject.onNext(video)
  }
}