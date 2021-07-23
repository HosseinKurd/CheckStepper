package com.hosseinkurd.component.checkstepper

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

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

    private var textViewTitle: TextView
    private var checkStepperStateView: CheckStepperStateView
    private var lineWidth = 6f
    private var showBelowLine = false
    private var showAboveLine = false
    private var isAboveLineSelected = false
    private var isBelowLineSelected = false

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
            lineWidth = typedArray.getDimension(R.styleable.CheckStepper_lineWidth, 100F)
            showAboveLine = typedArray.getBoolean(R.styleable.CheckStepper_showAboveLine, false)
            isAboveLineSelected =
                typedArray.getBoolean(R.styleable.CheckStepper_isAboveLineSelected, false)
            showBelowLine = typedArray.getBoolean(R.styleable.CheckStepper_showBelowLine, false)
            isBelowLineSelected =
                typedArray.getBoolean(R.styleable.CheckStepper_isBelowLineSelected, false)
            checkStepperStateView.apply {
                setLineWidth(lineWidth)
                setLineWidth(lineWidth)
                setShowBelowLine(showBelowLine)
                setAboveLineSelected(isAboveLineSelected)
                setBelowLineSelected(isBelowLineSelected)
                invalidate()
            }
            typedArray.recycle()
        }
    }
}