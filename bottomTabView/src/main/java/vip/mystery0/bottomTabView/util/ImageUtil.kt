package vip.mystery0.bottomTabView.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.drawable.Drawable
import android.graphics.PixelFormat
import android.graphics.drawable.BitmapDrawable

object ImageUtil {
	fun zoomBitmap(bitmap: Bitmap, width: Float, height: Float): Bitmap {
		val oldWidth = bitmap.width
		val oldHeight = bitmap.height
		val scaleWidth = width / oldWidth.toFloat()
		val scaleHeight = height / oldHeight.toFloat()
		val matrix = Matrix()
		matrix.postScale(scaleWidth, scaleHeight)
		return Bitmap.createBitmap(bitmap, 0, 0, oldWidth, oldHeight, matrix, true)
	}

	fun zoomDrawable(context: Context, drawable: Drawable, width: Float, height: Float): Drawable {
		val bitmap = drawableToBitmap(drawable)
		return BitmapDrawable(context.resources, zoomBitmap(bitmap, width, height))
	}

	fun drawableToBitmap(drawable: Drawable): Bitmap {
		val width = drawable.intrinsicWidth//取drawable的长宽
		val height = drawable.intrinsicHeight
		val config = if (drawable.opacity != PixelFormat.OPAQUE)
			Bitmap.Config.ARGB_8888 else Bitmap.Config.RGB_565//取drawable的颜色格式
		val bitmap = Bitmap.createBitmap(width, height, config)//建立对应bitmap
		val canvas = Canvas(bitmap)//建立对应bitmap的画布
		drawable.setBounds(0, 0, width, height)
		drawable.draw(canvas)//把drawable内容画到画布中
		return bitmap
	}
}