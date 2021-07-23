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

    private var lineWidth = 6f
    private var showAboveLine = false
    private var showBelowLine = false
    private var isAboveLineSelected = false
    private var isBelowLineSelected = false

    init {
        textAlignment = TEXT_ALIGNMENT_CENTER
        gravity = Gravity.CENTER
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        // super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        super.onMeasure(heightMeasureSpec, heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas?) {
        drawShape(canvas)
        super.onDraw(canvas)
    }

    fun setLineWidth(lineWidth: Float) {
        this.lineWidth = lineWidth
    }

    fun setShowAboveLine(showAboveLine: Boolean) {
        this.showAboveLine = showAboveLine
    }

    fun setShowBelowLine(showBelowLine: Boolean) {
        this.showBelowLine = showBelowLine
    }

    fun setAboveLineSelected(isAboveLineSelected: Boolean) {
        this.isAboveLineSelected = isAboveLineSelected
    }

    fun setBelowLineSelected(isBelowLineSelected: Boolean) {
        this.isBelowLineSelected = isBelowLineSelected
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
            color = if (isAboveLineSelected) {
                getColour(R.color.selected_background)
            } else {
                getColour(R.color.unselected_background)
            }
            style = Paint.Style.FILL
            isAntiAlias = true
        }
        paintLineDown.apply {
            color = if (isBelowLineSelected) {
                getColour(R.color.selected_background)
            } else {
                getColour(R.color.unselected_background)
            }
            style = Paint.Style.FILL
            isAntiAlias = true
        }
        pathLineUp.apply {
            moveTo(((width / 2) - (lineWidth / 2)), 0F)
            lineTo(((width / 2) + (lineWidth / 2)), 0F)
            lineTo(((width / 2) + (lineWidth / 2)), (height / 5).toFloat())
            lineTo(((width / 2) - (lineWidth / 2)), (height / 5).toFloat())
        }
        pathLineDown.apply {
            moveTo(((width / 2) - (lineWidth / 2)), (height / 5).toFloat())
            lineTo(((width / 2) + (lineWidth / 2)), (height / 5).toFloat())
            lineTo(((width / 2) + (lineWidth / 2)), height.toFloat())
            lineTo(((width / 2) - (lineWidth / 2)), height.toFloat())
        }
        val cx = (width / 3).toFloat() * 1.5f
        val cy = (height / 3).toFloat() * 1.5f
        val radius = (sqrt((width * width).toDouble() + (height * height).toDouble()) / 6).toFloat()
        if (showAboveLine)
            canvas.drawPath(pathLineUp, paintLineUp)
        if (showBelowLine)
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