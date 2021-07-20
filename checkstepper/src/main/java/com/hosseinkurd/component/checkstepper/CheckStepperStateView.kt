package com.hosseinkurd.component.checkstepper

import android.content.Context
import android.util.AttributeSet

class CheckStepperStateView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : androidx.appcompat.widget.AppCompatImageView
    (
    context,
    attributeSet,
    defStyleAttr
) {
    init {

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        // super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        super.onMeasure(heightMeasureSpec, heightMeasureSpec)
    }
}