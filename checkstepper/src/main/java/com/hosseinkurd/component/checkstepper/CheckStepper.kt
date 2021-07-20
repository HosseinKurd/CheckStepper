package com.hosseinkurd.component.checkstepper

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
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

    private var gap = 0f

    init {
        val rootView =
            (getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
                .inflate(R.layout.check_stepper_item, this, true)
        if (attributeSet != null) {
            val attrsArray = getContext().obtainStyledAttributes(attributeSet, R.styleable.CheckStepperItem, defStyleAttr, 0)
            gap = attrsArray.getDimension(
                R.styleable.CheckStepperItem_checkStepperItemGap,
                100F
            )
            attrsArray.recycle()
        }
    }
}