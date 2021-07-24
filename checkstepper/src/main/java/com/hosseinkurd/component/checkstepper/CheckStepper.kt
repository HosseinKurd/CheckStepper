package com.hosseinkurd.component.checkstepper

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
import androidx.annotation.StyleRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.hosseinkurd.component.checkstepper.enums.EnumCompleted

class CheckStepper @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout
    (
    context,
    attributeSet,
    defStyleAttr
) {

    var textViewTitle: TextView
    var checkStepperStateView: CheckStepperStateView
    private var lineWidth = 2f
    private var badgeCircleRadius = 2f
    private var showBelowLine = false
    private var showAboveLine = false
    private var aboveLineCompleted: EnumCompleted = EnumCompleted.UNCOMPLETED
    private var belowLineCompleted: EnumCompleted = EnumCompleted.UNCOMPLETED
    private var circleCompleted: EnumCompleted = EnumCompleted.UNCOMPLETED
    private var showCompletedText = true
    private var title: String? = null
    private var stateTitle: String? = null

    init {
        val rootView =
            (getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
                .inflate(R.layout.check_stepper, this, true)
        textViewTitle = rootView.findViewById(R.id.textView_title)
        checkStepperStateView = rootView.findViewById(R.id.checkStepperStateView)
        if (attributeSet != null) {
            val typedArray = getContext().obtainStyledAttributes(
                attributeSet,
                R.styleable.CheckStepper,
                defStyleAttr,
                0
            )
            title = typedArray.getString(R.styleable.CheckStepper_title)
            stateTitle = typedArray.getString(R.styleable.CheckStepper_stateTitle)
            title?.let {
                textViewTitle.text = it
            }
            stateTitle?.let {
                checkStepperStateView.text = it
            }
            lineWidth = typedArray.getDimension(R.styleable.CheckStepper_lineWidth, 100F)
            badgeCircleRadius =
                typedArray.getDimension(R.styleable.CheckStepper_badgeCircleRadius, 100F)
            showAboveLine = typedArray.getBoolean(R.styleable.CheckStepper_showAboveLine, false)
            showBelowLine = typedArray.getBoolean(R.styleable.CheckStepper_showBelowLine, false)
            aboveLineCompleted =
                EnumCompleted.values()[typedArray.getInt(
                    R.styleable.CheckStepper_aboveLineCompleted,
                    EnumCompleted.UNCOMPLETED.enumValue
                )]
            belowLineCompleted =
                EnumCompleted.values()[typedArray.getInt(
                    R.styleable.CheckStepper_belowLineCompleted,
                    EnumCompleted.UNCOMPLETED.enumValue
                )]
            circleCompleted =
                EnumCompleted.values()[typedArray.getInt(
                    R.styleable.CheckStepper_circleCompleted,
                    EnumCompleted.UNCOMPLETED.enumValue
                )]
            showCompletedText =
                typedArray.getBoolean(R.styleable.CheckStepper_showCompletedText, false)
            checkStepperStateView.apply {
                setLineWidth(lineWidth)
                setBadgeCircleRadius(badgeCircleRadius)
                setShowAboveLine(showAboveLine)
                setShowBelowLine(showBelowLine)
                setAboveLineCompleted(aboveLineCompleted)
                setBelowLineCompleted(belowLineCompleted)
                setCircleCompleted(circleCompleted)
                setShowCompletedText(showCompletedText)
                invalidate()
            }
            typedArray.recycle()
        }
    }

    fun setTitleTextAppearance(@StyleRes resId: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            textViewTitle.setTextAppearance(resId)
        } else {
            textViewTitle.setTextAppearance(context, resId)
        }
    }

    fun setCheckStepperStateViewTextAppearance(@StyleRes resId: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkStepperStateView.setTextAppearance(resId)
        } else {
            checkStepperStateView.setTextAppearance(context, resId)
        }
    }

    fun setTitle(title: String) {
        this.title = title
    }

    fun setStateTitle(stateTitle: String) {
        this.stateTitle = stateTitle
    }

    fun setLineWidth(lineWidth: Float) {
        this.lineWidth = lineWidth
        checkStepperStateView.setLineWidth(lineWidth)
    }

    fun setBadgeCircleRadius(badgeCircleRadius: Float) {
        this.badgeCircleRadius = badgeCircleRadius
        checkStepperStateView.setBadgeCircleRadius(badgeCircleRadius)
    }

    fun setShowAboveLine(showAboveLine: Boolean) {
        this.showAboveLine = showAboveLine
        checkStepperStateView.setShowAboveLine(showAboveLine)
    }

    fun setShowBelowLine(showBelowLine: Boolean) {
        this.showBelowLine = showBelowLine
        checkStepperStateView.setShowBelowLine(showBelowLine)
    }

    fun setAboveLineSelected(aboveLineCompleted: EnumCompleted) {
        this.aboveLineCompleted = aboveLineCompleted
        checkStepperStateView.setAboveLineCompleted(aboveLineCompleted)
    }

    fun setBelowLineSelected(belowLineCompleted: EnumCompleted) {
        this.belowLineCompleted = belowLineCompleted
        checkStepperStateView.setBelowLineCompleted(belowLineCompleted)
    }

    fun setCircleSelected(circleCompleted: EnumCompleted) {
        this.circleCompleted = circleCompleted
        checkStepperStateView.setCircleCompleted(circleCompleted)
    }

}