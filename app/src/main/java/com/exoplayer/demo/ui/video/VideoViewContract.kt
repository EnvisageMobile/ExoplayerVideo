
package com.exoplayer.demo.ui.video

import com.exoplayer.demo.device.player.MediaPlayer

interface VideoViewContract {

  interface Presenter {

    fun deactivate()

    fun getPlayer(): MediaPlayer

    fun play(url: String)

    fun releasePlayer()

    fun setMediaSessionState(isActive: Boolean)
  }

  interface View
}