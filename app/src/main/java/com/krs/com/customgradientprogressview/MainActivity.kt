package com.krs.com.customgradientprogressview

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*


@RequiresApi(Build.VERSION_CODES.KITKAT)
class MainActivity : AppCompatActivity() {

    var currentProgressValue:Int= 40

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onResume() {
        super.onResume()
        setProgressLevels()
    }


    private fun setProgressLevels() {
        custom_progress_view.setProgressDrawable(R.drawable.custom_progress_gradient)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            custom_progress_view.setSplitTrack(false)
        }
        custom_progress_view.setProgressMax(100)
        custom_progress_view.setProgressStart(0)
        custom_progress_view.hideThumb(true)
        custom_progress_view.setProgressCurrent(currentProgressValue)
    }
}
