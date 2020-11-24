package com.vikram.customviews.ui.circle

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.View
import com.vikram.customviews.R
import kotlin.math.*

private const val ANGLE_BUMP = PI / 360 // Move dot by 0.5 degrees at a time
private const val START_ANGLE = PI * 1.5 // 12 O'Clock position

/**
 *   Created by vikram.gupta on 11/15/20.
 */
class CircleView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var circlePosition = PointF(0.0f, 0.0f) // center of the circle
    private var dotPosition = PointF(0.0f, 0.0f) // center of the dot
    private var circleRadius = 0.0f
    private var dotRadius = 0.0f
    private var dotDistance = 0.0f
    private var dotAngle = START_ANGLE

    var play = false

    private val paintStroke = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = resources.getColor(R.color.design_default_color_primary, context.theme)
        style = Paint.Style.STROKE
        strokeWidth = 20f
    }

    private val paintFill = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = resources.getColor(R.color.design_default_color_secondary, context.theme)
        style = Paint.Style.FILL
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        circleRadius = (min(width, height) / 2 * 0.8).toFloat()
        dotRadius = circleRadius / 12
        dotDistance = circleRadius - dotRadius * 2
        circlePosition.x = width / 2f
        circlePosition.y = height / 2f

        dotPosition.x = (dotDistance * cos(dotAngle) + circlePosition.x).toFloat()
        dotPosition.y = (dotDistance * sin(dotAngle) + circlePosition.y).toFloat()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        // Draw outer circle
        canvas?.drawCircle(circlePosition.x, circlePosition.y, circleRadius, paintStroke)

        // Draw dot
        canvas?.drawCircle(dotPosition.x, dotPosition.y, dotRadius, paintFill)

        if (play) {
            dotPosition.next()
            invalidate()
        }
    }

    private fun PointF.next() {
        dotAngle += ANGLE_BUMP

        if (dotAngle == 2 * PI) dotAngle = 0.0

        // Calculate next dot position
        this.x = (dotDistance * cos(dotAngle) + circlePosition.x).toFloat()
        this.y = (dotDistance * sin(dotAngle) + circlePosition.y).toFloat()
    }

    fun play() {
        invalidate()
    }
}