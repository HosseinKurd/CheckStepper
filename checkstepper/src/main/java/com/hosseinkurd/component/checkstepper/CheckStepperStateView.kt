package com.hosseinkurd.component.checkstepper

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.os.Build
import android.util.AttributeSet
import android.view.Gravity
import androidx.appcompat.widget.AppCompatTextView
import com.hosseinkurd.component.checkstepper.enums.EnumCompleted
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

    private var lineWidth = 2f
    private var badgeCircleRadius = 2f
    private var showAboveLine = true
    private var showBelowLine = true
    private var aboveLineCompleted: EnumCompleted = EnumCompleted.UNCOMPLETED
    private var belowLineCompleted: EnumCompleted = EnumCompleted.UNCOMPLETED
    private var circleCompleted: EnumCompleted = EnumCompleted.UNCOMPLETED
    private var showCompletedText = true

    init {
        textAlignment = TEXT_ALIGNMENT_CENTER
        gravity = Gravity.CENTER
        setTextColor(Color.WHITE)
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
        invalidate()
    }

    fun setBadgeCircleRadius(badgeCircleRadius: Float) {
        this.badgeCircleRadius = badgeCircleRadius
        invalidate()
    }

    fun setShowAboveLine(showAboveLine: Boolean) {
        this.showAboveLine = showAboveLine
        invalidate()
    }

    fun setShowBelowLine(showBelowLine: Boolean) {
        this.showBelowLine = showBelowLine
        invalidate()
    }

    fun setAboveLineCompleted(aboveLineSelected: EnumCompleted) {
        this.aboveLineCompleted = aboveLineSelected
        invalidate()
    }

    fun setBelowLineCompleted(belowLineSelected: EnumCompleted) {
        this.belowLineCompleted = belowLineSelected
        invalidate()
    }

    fun setCircleCompleted(circleSelected: EnumCompleted) {
        this.circleCompleted = circleSelected
        invalidate()
    }

    fun setShowCompletedText(showCompletedText: Boolean) {
        this.showCompletedText = showCompletedText
        invalidate()
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
            color = when (circleCompleted) {
                EnumCompleted.COMPLETED -> {
                    getColour(R.color.background_completed)
                }
                EnumCompleted.INCOMPLETED -> {
                    getColour(R.color.background_incompleted)
                }
                else -> {
                    getColour(R.color.background_uncompleted)
                }
            }
            style = Paint.Style.FILL
            isAntiAlias = true
        }
        paintLineUp.apply {
            color = when (aboveLineCompleted) {
                EnumCompleted.COMPLETED -> {
                    getColour(R.color.background_completed)
                }
                EnumCompleted.INCOMPLETED -> {
                    getColour(R.color.background_incompleted)
                }
                else -> {
                    getColour(R.color.background_uncompleted)
                }
            }
            style = Paint.Style.FILL
            isAntiAlias = true
        }
        paintLineDown.apply {
            color = when (belowLineCompleted) {
                EnumCompleted.COMPLETED -> {
                    getColour(R.color.background_completed)
                }
                EnumCompleted.INCOMPLETED -> {
                    getColour(R.color.background_incompleted)
                }
                else -> {
                    getColour(R.color.background_uncompleted)
                }
            }
            style = Paint.Style.FILL
            isAntiAlias = true
        }
        pathLineUp.apply {
            moveTo(((width / 2) - (lineWidth / 2)), 0F)
            lineTo(((width / 2) + (lineWidth / 2)), 0F)
            lineTo(((width / 2) + (lineWidth / 2)), (height / 2).toFloat())
            lineTo(((width / 2) - (lineWidth / 2)), (height / 2).toFloat())
        }
        pathLineDown.apply {
            moveTo(((width / 2) - (lineWidth / 2)), (height - (height / 2)).toFloat())
            lineTo(((width / 2) + (lineWidth / 2)), (height - (height / 2)).toFloat())
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
        if (circleCompleted == EnumCompleted.INCOMPLETED)
            canvas.drawCircle(
                (cx + (radius * 0.7)).toFloat(),
                (cy - (radius * 0.7)).toFloat(),
                badgeCircleRadius,
                paintCircleBadge
            )
        else if (circleCompleted == EnumCompleted.COMPLETED && showCompletedText)
            setText(R.string.state_title_completed)

    }

    private fun getColour(resId: Int): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            context.resources.getColor(resId, null)
        } else {
            context.resources.getColor(resId)
        }
    }
}