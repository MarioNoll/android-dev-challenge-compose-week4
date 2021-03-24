package com.example.androiddevchallenge.ui.decoration

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathMeasure
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun DrawScope.drawWave(
    waveHeight: Dp,
    strokeColor: Color,
    topCurveProgress: Float,
    bottomCurveProgress: Float,
    curveIntersectionColor: Color,
    bottomSurfaceColor: Color
) {
    val curve1Start = size.height + (16.dp - waveHeight).toPx()
    val curve1EndY = curve1Start + 30.dp.toPx()

    val curve1 = Path().apply {
        moveTo(x = 0F, y = curve1Start)
        cubicTo(
            x1 = 0.277F * size.width,
            y1 = curve1Start + 100.dp.toPx(),
            x2 = 0.66F * size.width,
            y2 = curve1Start - 80.dp.toPx(),
            x3 = size.width,
            y3 = curve1EndY
        )
    }

    val curve1segment = Path()
    PathMeasure().apply {
        setPath(curve1, forceClosed = false)
        getSegment(
            0F,
            stopDistance = length * topCurveProgress,
            curve1segment
        )
    }

    val curve2Start = curve1EndY + 30.dp.toPx()
    val curve2 = Path().apply {
        moveTo(x = size.width, y = curve2Start)
        cubicTo(
            x1 = 0.4F * size.width,
            y1 = curve2Start - 140.dp.toPx(),
            x2 = 0.3F * size.width,
            y2 = curve2Start + 50.dp.toPx(),
            x3 = 0F,
            y3 = curve1Start + 30.dp.toPx()
        )
    }

    val curve2segment = Path()
    PathMeasure().apply {
        setPath(curve2, forceClosed = false)
        getSegment(
            0F,
            stopDistance = length * bottomCurveProgress,
            curve2segment
        )
    }

    val curveIntersection = Path().apply {
        addPath(curve1)
        relativeLineTo(dx = 0F, dy = 30.dp.toPx())
        addPath(curve2)
        relativeLineTo(dx = 0F, dy = -30.dp.toPx())
        close()
    }

    val belowBottomCurveSurface = Path().apply {
        addPath(curve2)
        lineTo(x = 0F, y = size.height)
        lineTo(x = size.width, y = size.height)
        close()
    }

    drawPath(curveIntersection, curveIntersectionColor)
    drawPath(belowBottomCurveSurface, bottomSurfaceColor)

    drawPath(curve1segment, strokeColor, style = Stroke(width = 1.dp.toPx()))
    drawPath(curve2segment, strokeColor, style = Stroke(width = 1.dp.toPx()))
}
