package com.cysion.ktbox.image

import android.graphics.*
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.cysion.ktbox.Box
import com.cysion.other.density
import java.security.MessageDigest

class RoundTransform(val r: Int) : BitmapTransformation() {

    private var radius = 0f
    override fun updateDiskCacheKey(messageDigest: MessageDigest) {
    }

    override fun transform(pool: BitmapPool, toTransform: Bitmap, outWidth: Int, outHeight: Int): Bitmap {
        radius = Box.context.density()*r
        return roundCrop(pool, toTransform)

    }

    private fun roundCrop(pool: BitmapPool, source: Bitmap): Bitmap {
        if (radius == 0f) {
            radius = source.getWidth() / (Box.context.density() * 12)
        }
        var result: Bitmap? = pool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888)
        if (result == null) {
            result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888)
        }

        val canvas = Canvas(result!!)
        val paint = Paint()
        paint.shader = BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        paint.isAntiAlias = true
        val rectF = RectF(0f, 0f, source.getWidth().toFloat(), source.getHeight().toFloat())
        canvas.drawRoundRect(rectF, radius, radius, paint)
        return result
    }
}