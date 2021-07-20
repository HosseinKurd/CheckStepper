package com.hosseinkurd.component.checkstepper

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.os.Build
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import kotlin.math.sqrt

class CheckStepperStateView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView
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

    override fun onDraw(canvas: Canvas?) {
        drawShape(canvas)
        super.onDraw(canvas)
    }

    private fun drawShape(canvas: Canvas?) {
        if (canvas == null) {
            return
        }
        val paint = Paint()
        val path = Path()
        paint.apply {
            color = getColour(R.color.navy_blue)
            style = Paint.Style.FILL
            isAntiAlias = true
        }
        path.apply {

        }
        // canvas.drawPath(path, paint)
        val cx = (width / 3).toFloat() * 1.5f
        val cy = (height / 3).toFloat() * 1.5f
        val radius = (sqrt((width * width).toDouble() + (height * height).toDouble()) / 9).toFloat()
        canvas.drawCircle(cx, cy, radius, paint)
    }

    private fun getColour(resId: Int): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            context.resources.getColor(resId, null)
        } else {
            context.resources.getColor(resId)
        }
    }
}