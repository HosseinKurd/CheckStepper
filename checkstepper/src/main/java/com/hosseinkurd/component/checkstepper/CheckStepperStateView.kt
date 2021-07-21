package com.hosseinkurd.component.checkstepper

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.os.Build
import android.util.AttributeSet
import android.view.Gravity
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
        textAlignment = TEXT_ALIGNMENT_CENTER
        gravity = Gravity.CENTER
    }

    private var lineWidth = 6

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
        val paintCircleBadge = Paint()
        val paintCircleCenter = Paint()
        val paintLineUp = Paint()
        val paintLineDown = Paint()
        val pathLineUp = Path()
        val pathLineDown = Path()
        paintCircleBadge.apply {
            color = getColour(R.color.badge_background)
            style = Paint.Style.FILL
            isAntiAlias = true
        }
        paintCircleCenter.apply {
            color = getColour(R.color.selected_background)
            style = Paint.Style.FILL
            isAntiAlias = true
        }
        paintLineUp.apply {
            color = getColour(R.color.unselected_background)
            style = Paint.Style.FILL
            isAntiAlias = true
        }
        paintLineDown.apply {
            color = getColour(R.color.unselected_background)
            style = Paint.Style.FILL
            isAntiAlias = true
        }
        pathLineUp.apply {
            moveTo(((width / 2) - (lineWidth / 2)).toFloat(), 0F)
            lineTo(((width / 2) + (lineWidth / 2)).toFloat(), 0F)
            lineTo(((width / 2) + (lineWidth / 2)).toFloat(), (height / 5).toFloat())
            lineTo(((width / 2) - (lineWidth / 2)).toFloat(), (height / 5).toFloat())
        }
        pathLineDown.apply {
            moveTo(((width / 2) - (lineWidth / 2)).toFloat(), (height / 5).toFloat())
            lineTo(((width / 2) + (lineWidth / 2)).toFloat(), (height / 5).toFloat())
            lineTo(((width / 2) + (lineWidth / 2)).toFloat(), height.toFloat())
            lineTo(((width / 2) - (lineWidth / 2)).toFloat(), height.toFloat())
        }
        val cx = (width / 3).toFloat() * 1.5f
        val cy = (height / 3).toFloat() * 1.5f
        val radius = (sqrt((width * width).toDouble() + (height * height).toDouble()) / 6).toFloat()
        canvas.drawPath(pathLineUp, paintLineUp)
        canvas.drawPath(pathLineDown, paintLineDown)
        canvas.drawCircle(cx, cy, radius, paintCircleCenter)
        canvas.drawCircle(
            (cx + (radius * 0.7)).toFloat(),
            (cy - (radius * 0.7)).toFloat(),
            radius / 8,
            paintCircleBadge
        )
    }

    private fun getColour(resId: Int): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            context.resources.getColor(resId, null)
        } else {
            context.resources.getColor(resId)
        }
    }
}