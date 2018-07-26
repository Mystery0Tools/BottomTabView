package vip.mystery0.bottomTabView

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.widget.ImageView
import android.widget.LinearLayout


class BottomTabView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : LinearLayout(context, attrs, defStyleAttr) {
	private val inflater: LayoutInflater = LayoutInflater.from(context)
	val menuInflater = MenuInflater(context)

	private var menu: Menu? = null
	//	var selectedColor: Int
	private var itemTextSize = 24f

	constructor(context: Context) : this(context, null)
	constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

	init {
		val typedArray = context.obtainStyledAttributes(attrs, R.styleable.BottomTabView)
		if (typedArray.hasValue(R.styleable.BottomTabView_menu)) {
			val menuRes = typedArray.getInteger(R.styleable.BottomTabView_menu, 0)
			menu = BottomTabViewMenu()
			menuInflater.inflate(menuRes, menu)
		}
		typedArray.recycle()


		addView(createImageView(drawBitmap(0, "1")))
		addView(createImageView(drawBitmap(1, "222")))
		addView(createImageView(drawBitmap(2, "第三个")))
	}

	private fun drawBitmap(position: Int, text: String): Bitmap {
		val menuItem = menu!!.getItem(position)
		val drawable = menuItem.icon
		val width = drawable.intrinsicWidth
		val height = drawable.intrinsicHeight
		val config = if (drawable.opacity != PixelFormat.OPAQUE)
			Bitmap.Config.ARGB_8888
		else
			Bitmap.Config.RGB_565
		val bitmap = Bitmap.createBitmap(width, height, config)
		val drawBitmap = Bitmap.createBitmap(height + itemTextSize.toInt(), width, Bitmap.Config.ARGB_8888)
		val canvas = Canvas(drawBitmap)
		val bitmapPaint = Paint()
		bitmapPaint.color = Color.BLACK
		canvas.drawBitmap(bitmap, 0f, 0f, bitmapPaint)
		val textPaint = Paint()
		textPaint.textSize = itemTextSize
		textPaint.style = Paint.Style.FILL
		textPaint.textAlign = Paint.Align.CENTER
		val fontMetrics = textPaint.fontMetrics
		val top = fontMetrics.top
		val bottom = fontMetrics.bottom
		val baseLineX = width / 2f
		val baseLineY = height + itemTextSize / 2 - top / 2 - bottom / 2
		canvas.drawText(text, baseLineX, baseLineY, textPaint)
		return drawBitmap
	}

	private fun createImageView(bitmap: Bitmap): ImageView {
		val imageView = ImageView(context)
		imageView.setImageBitmap(bitmap)
		val layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
		imageView.layoutParams = layoutParams
		return imageView
	}
}