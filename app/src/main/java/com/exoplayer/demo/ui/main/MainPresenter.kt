

package com.exoplayer.demo.ui.main

import android.app.Activity
import android.content.Intent
import com.exoplayer.demo.data.network.VideosService
import com.exoplayer.demo.data.network.model.ApiResponse
import com.exoplayer.demo.ui.video.VideoViewActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.lang.ref.WeakReference

class MainPresenter(view: MainContract.View) : MainContract.Presenter {

  private val view = WeakReference<MainContract.View>(view)
  private val disposables = CompositeDisposable()

  override fun fetchSampleVideos() {
    disposables.add(
        VideosService.fetchVideos()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { apiResponse -> onVideosFetchedSuccessfully(apiResponse) },
                { throwable -> onVideosFetchError(throwable) }
            ))
  }

  override fun showVideoScreen(videoUrl: String) {
    val intent = Intent((view.get() as Activity), VideoViewActivity::class.java)
    intent.putExtra(VideoViewActivity.VIDEO_URL_EXTRA, videoUrl)
    (view.get() as Activity).startActivity(intent)
  }

  override fun deactivate() {
    disposables.clear()
  }

  private fun onVideosFetchedSuccessfully(videoData: ApiResponse?) {
    view.get()?.renderVideos(videoData?.resources!!)
  }

  private fun onVideosFetchError(throwable: Throwable) {
    view.get()?.showErrorMessage()
  }
}