package com.top.shiftestask.utils

import android.graphics.Bitmap
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Shader
import com.squareup.picasso.Transformation
import androidx.core.graphics.createBitmap

class CircleTransformation : Transformation {
    companion object {
        private const val KEY = "circleImageTransformation"
    }

    override fun transform(source: Bitmap): Bitmap {
        val minEdge = source.width.coerceAtMost(source.height)
        val dx = (source.width - minEdge) / 2
        val dy = (source.height - minEdge) / 2

        val shader = BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        val matrix = Matrix()
        matrix.setTranslate((-dx).toFloat(), (-dy).toFloat())
        shader.setLocalMatrix(matrix)

        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.shader = shader

        val output = createBitmap(minEdge, minEdge, source.config!!)
        val canvas = Canvas(output)
        canvas.drawOval(RectF(0f, 0f, minEdge.toFloat(), minEdge.toFloat()), paint)

        source.recycle()

        return output
    }

    override fun key(): String = KEY
}