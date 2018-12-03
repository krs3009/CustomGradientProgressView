package com.krs.com.cust

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.PorterDuff
import android.os.Build
import android.os.Handler
import android.support.annotation.RequiresApi
import android.support.v4.content.res.ResourcesCompat
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import com.krs.com.customgradientprogressview.R
import kotlinx.android.synthetic.main.layout_custom_progress.view.*


class CustomGradientProgressView : RelativeLayout, OnSeekBarChangeListener {

    private lateinit var mContext: Context

    private var thumbTouchable: Boolean = false;

    constructor(context: Context) : this(context, null) {
        mContext = context
        initView()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {
        initView()
    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

        var param: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        param.addRule(RelativeLayout.BELOW, seekBar?.getId()!!)
        val thumbRect = seekBar.getThumb().bounds
        param.setMargins(
                thumbRect.centerX(), 10, 0, 0)
        tvProgressCurrent.layoutParams = param
        tvProgressCurrent.setText(""+progress.toString())
    }


    fun initView() {
        val inflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.layout_custom_progress, this)
        seekbarCustom.setOnSeekBarChangeListener(this)
    }

    fun setProgressDrawable(customDrawable: Int){
        seekbarCustom.setProgressDrawable(ResourcesCompat.getDrawable(getResources(),customDrawable,null))
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun setSplitTrack(split: Boolean) {
        seekbarCustom.splitTrack = split;
    }

    fun setProgressStart(start: Int) {
        tvProgressStart.setText("" + start);
    }

    fun setProgressMax(maxValue: Int) {
        tvProgressEnd.setText("" + maxValue)
        seekbarCustom.setMax(maxValue)
    }

    fun setProgressCurrent(current: Int) {
        tvProgressCurrent.setText("" + current)
        seekbarCustom.setProgress(current)
        Handler().postDelayed({
            val anim = ValueAnimator.ofInt(0, current)
            anim.duration = 1000
            anim.addUpdateListener { animation ->
                val animProgress = animation.animatedValue as Int
                seekbarCustom.setProgress(animProgress)
                seekbarCustom.refreshDrawableState()
            }
            anim.start()
        }, 100)

    }

    @SuppressLint("ResourceAsColor")
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    fun hideThumb(hide: Boolean) {
        if (hide) {
            seekbarCustom.thumb.mutate().alpha=0
            thumbTouchable = false;
        } else {
            seekbarCustom.thumb.setColorFilter(android.R.color.black, PorterDuff.Mode.MULTIPLY);
            thumbTouchable = true;
        }
    }

    fun getProgress(): Int? {
        return seekbarCustom.getProgress()
    }

}


