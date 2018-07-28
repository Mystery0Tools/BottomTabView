package vip.mystery0.bottomTabView

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import vip.mystery0.bottomTabView.util.DensityTools

class BottomTabView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : LinearLayout(context, attrs, defStyleAttr) {
	private val inflater: LayoutInflater = LayoutInflater.from(context)
	var list: List<BottomTabItem>? = null
	var config = BottomTabViewConfig()
	var currentItem = 0
		private set
	var onItemSelectedListener: OnItemSelectedListener? = null

	constructor(context: Context) : this(context, null)
	constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

	init {
		val typedArray = context.obtainStyledAttributes(attrs, R.styleable.BottomTabView)
		if (typedArray.hasValue(R.styleable.BottomTabView_selected_color))
			config.selectedColor = typedArray.getColor(R.styleable.BottomTabView_selected_color, config.selectedColor)
		if (typedArray.hasValue(R.styleable.BottomTabView_unselected_color))
			config.unSelectedColor = typedArray.getColor(R.styleable.BottomTabView_unselected_color, config.unSelectedColor)
		if (typedArray.hasValue(R.styleable.BottomTabView_margin_top))
			config.marginTop = typedArray.getDimension(R.styleable.BottomTabView_margin_top, config.marginTop)
		if (typedArray.hasValue(R.styleable.BottomTabView_margin_bottom))
			config.marginBottom = typedArray.getDimension(R.styleable.BottomTabView_margin_bottom, config.marginBottom)
		if (typedArray.hasValue(R.styleable.BottomTabView_line_height))
			config.lineHeight = typedArray.getDimension(R.styleable.BottomTabView_line_height, config.lineHeight)
		if (typedArray.hasValue(R.styleable.BottomTabView_item_text_size))
			config.itemTextSize = typedArray.getDimension(R.styleable.BottomTabView_item_text_size, config.itemTextSize)
		if (typedArray.hasValue(R.styleable.BottomTabView_item_icon_size))
			config.itemIconSize = typedArray.getDimension(R.styleable.BottomTabView_item_icon_size, config.itemIconSize)
		if (typedArray.hasValue(R.styleable.BottomTabView_show_ripple))
			config.isShowRipple = typedArray.getBoolean(R.styleable.BottomTabView_show_ripple, config.isShowRipple)
		if (typedArray.hasValue(R.styleable.BottomTabView_show_gradient_colors))
			config.isShowGradientColors = typedArray.getBoolean(R.styleable.BottomTabView_show_gradient_colors, config.isShowGradientColors)
		typedArray.recycle()

		orientation = LinearLayout.HORIZONTAL
		minimumHeight = 24
	}

	fun setCheckedItem(index: Int) {
		if (index !in 0..childCount)
			throw NullPointerException("index must be less than $childCount")
		val oldIndex = currentItem
		val newIndex = index
		val oldBottomTabItem = list!![oldIndex]
		val newBottomTabItem = list!![newIndex]
		oldBottomTabItem.isChecked = false
		newBottomTabItem.isChecked = true
		val oldTextView = getChildAt(oldIndex).findViewById<TextView>(R.id.textView)
		oldTextView.setCompoundDrawablesWithIntrinsicBounds(null, drawDrawable(oldBottomTabItem), null, null)
		oldTextView.setTextColor(config.unSelectedColor)
		val newTextView = getChildAt(newIndex).findViewById<TextView>(R.id.textView)
		newTextView.setCompoundDrawablesWithIntrinsicBounds(null, drawDrawable(newBottomTabItem), null, null)
		newTextView.setTextColor(config.selectedColor)
		currentItem = index
	}

	fun setConfig(config: BottomTabViewConfig): BottomTabView {
		this.config = config
		return this
	}

	fun setMenuList(list: List<BottomTabItem>): BottomTabView {
		this.list = list
		return this
	}

	fun setOnItemSelectedListener(listener: (BottomTabItem) -> Unit) {
		onItemSelectedListener = object : OnItemSelectedListener {
			override fun onItemSelected(bottomTabItem: BottomTabItem) {
				listener.invoke(bottomTabItem)
			}
		}
	}

	fun init() {
		updateView()
		setCheckedItem(currentItem)
	}

	private fun updateView() {
		list?.forEach {
			addView(createItemView(it))
		}
	}

	private fun drawDrawable(bottomTabItem: BottomTabItem): Drawable? {
		if (!bottomTabItem.isChecked) {
			val drawable = ContextCompat.getDrawable(context, bottomTabItem.icon)!!
			DrawableCompat.setTint(drawable.mutate(), config.unSelectedColor)
			return drawable
		}
		val size = DensityTools.dp2px(context, config.itemIconSize.toFloat())
		val drawable = ContextCompat.getDrawable(context, bottomTabItem.icon)!!
		val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
		val canvas = Canvas(bitmap)
		drawable.setBounds(0, 0, canvas.width, canvas.height)
		drawable.draw(canvas)
		val bitmapShader = BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
		//创建线性着色器并配置着色
		val linearGradient = LinearGradient(size / 2f, 0f, size / 2f, size.toFloat(), config.gradientColors, null, Shader.TileMode.CLAMP)
		val composeShader = ComposeShader(bitmapShader, linearGradient, PorterDuff.Mode.MULTIPLY)
		val paint = Paint()
		paint.shader = composeShader
		canvas.drawRect(0f, 0f, size.toFloat(), size.toFloat(), paint)
		return BitmapDrawable(context.resources, bitmap)
	}

	private fun createItemView(bottomTabItem: BottomTabItem): View {
		val itemView = inflater.inflate(R.layout.layout_bottom_tab_item, null)
		if (!config.isShowRipple)
			itemView.background = null
		val line = itemView.findViewById<View>(R.id.line)
		line.setBackgroundColor(config.lineColor)
		val lineLayoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, config.lineHeight.toInt())
		line.layoutParams = lineLayoutParams
		val frameLayoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
		itemView.layoutParams = frameLayoutParams
		val textView = itemView.findViewById<TextView>(R.id.textView)
		textView.text = bottomTabItem.name
		textView.textSize = config.itemTextSize
		textView.setCompoundDrawablesWithIntrinsicBounds(null, drawDrawable(bottomTabItem), null, null)
		val textViewLayoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT)
		textViewLayoutParams.setMargins(0, DensityTools.dp2px(context, config.marginTop), 0, DensityTools.dp2px(context, config.marginBottom))
		textViewLayoutParams.gravity = Gravity.CENTER
		textView.layoutParams = textViewLayoutParams
		textView.gravity = Gravity.CENTER
		itemView.setOnClickListener {
			val position = indexOfChild(it)
			if (!list!![position].isChecked) {
				setCheckedItem(position)
				onItemSelectedListener?.onItemSelected(bottomTabItem)
			}
		}
		return itemView
	}

	interface OnItemSelectedListener {
		fun onItemSelected(bottomTabItem: BottomTabItem)
	}
}