package vip.mystery0.bottomTabView

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.graphics.drawable.DrawableCompat
import androidx.viewpager.widget.ViewPager
import vip.mystery0.bottomTabView.util.DensityTools
import vip.mystery0.bottomTabView.util.ImageUtil
import java.util.ArrayList

class BottomTabView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : LinearLayout(context, attrs, defStyleAttr) {
	private val inflater: LayoutInflater = LayoutInflater.from(context)
	var menuList = ArrayList<BottomTabItem>()
		private set
	var config = BottomTabViewConfig()
		private set
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
			config.marginTop = DensityTools.px2dp(context, typedArray.getDimension(R.styleable.BottomTabView_margin_top, 0f))
		if (typedArray.hasValue(R.styleable.BottomTabView_margin_bottom))
			config.marginBottom = DensityTools.px2dp(context, typedArray.getDimension(R.styleable.BottomTabView_margin_bottom, 0f))
		if (typedArray.hasValue(R.styleable.BottomTabView_line_height))
			config.lineHeight = DensityTools.px2dp(context, typedArray.getDimension(R.styleable.BottomTabView_line_height, 0f))
		if (typedArray.hasValue(R.styleable.BottomTabView_item_text_size))
			config.itemTextSize = DensityTools.px2dp(context, typedArray.getDimension(R.styleable.BottomTabView_item_text_size, config.itemTextSize))
		if (typedArray.hasValue(R.styleable.BottomTabView_item_icon_size))
			config.itemIconSize = DensityTools.px2dp(context, typedArray.getDimension(R.styleable.BottomTabView_item_icon_size, 0f))
		if (typedArray.hasValue(R.styleable.BottomTabView_icon_margin))
			config.iconMargin = DensityTools.px2dp(context, typedArray.getDimension(R.styleable.BottomTabView_icon_margin, 0f))
		if (typedArray.hasValue(R.styleable.BottomTabView_show_ripple))
			config.isShowRipple = typedArray.getBoolean(R.styleable.BottomTabView_show_ripple, config.isShowRipple)
		if (typedArray.hasValue(R.styleable.BottomTabView_show_gradient_colors))
			config.isShowGradientColors = typedArray.getBoolean(R.styleable.BottomTabView_show_gradient_colors, config.isShowGradientColors)
		typedArray.recycle()

		orientation = HORIZONTAL
		minimumHeight = 24
	}

	/**
	 * 设置当前选中的项
	 */
	fun setCheckedItem(newIndex: Int) {
		if (newIndex !in 0..childCount)
			throw NullPointerException("newIndex must be less than $childCount")
		val oldIndex = currentItem
		val oldBottomTabItem = menuList[oldIndex]
		val newBottomTabItem = menuList[newIndex]
		oldBottomTabItem.isChecked = false
		newBottomTabItem.isChecked = true
		val oldTextView = getChildAt(oldIndex).findViewById<TextView>(R.id.textView)
		oldTextView.setCompoundDrawablesWithIntrinsicBounds(null, drawDrawable(oldBottomTabItem), null, null)
		oldTextView.setTextColor(config.unSelectedColor)
		val newTextView = getChildAt(newIndex).findViewById<TextView>(R.id.textView)
		newTextView.setCompoundDrawablesWithIntrinsicBounds(null, drawDrawable(newBottomTabItem), null, null)
		newTextView.setTextColor(config.selectedColor)
		currentItem = newIndex
	}

	/**
	 * 设置菜单项列表
	 */
	fun setMenuList(list: List<BottomTabItem>): BottomTabView {
		menuList.clear()
		menuList.addAll(list)
		return this
	}

	/**
	 * 设置配置信息
	 * 尽量更改现有config对象，使用bottomTabView.getConfig()获取对应的config对象，这样子可以在保留在layout中自定义的属性值
	 */
	fun setConfig(config: BottomTabViewConfig): BottomTabView {
		this.config = config
		return this
	}

	fun config(listener: (BottomTabViewConfig) -> Unit): BottomTabView {
		listener.invoke(config)
		return this
	}

	/**
	 * 设置监听器
	 */
	fun setOnItemSelectedListener(listener: (BottomTabItem) -> Unit): BottomTabView {
		onItemSelectedListener = object : OnItemSelectedListener {
			override fun onItemSelected(bottomTabItem: BottomTabItem) {
				listener.invoke(bottomTabItem)
			}
		}
		return this
	}

	/**
	 * 设置链接的ViewPager
	 */
	fun linkViewPager(viewPager: ViewPager, listener: ((Int) -> Unit)?, isShowAnimationWhenClickItem: Boolean = true): BottomTabView {
		setOnItemSelectedListener { viewPager.setCurrentItem(indexOf(it), isShowAnimationWhenClickItem) }
		viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
			override fun onPageScrollStateChanged(state: Int) {
			}

			override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
			}

			override fun onPageSelected(position: Int) {
				setCheckedItem(position)
				listener?.invoke(position)
			}
		})
		return this
	}

	/**
	 * 必须在使用时显式调用init方法，否则不会更新界面的数据
	 * 避免二次绘制同时确保在初始化时数据正常
	 */
	fun init() {
		removeAllViews()
		updateView()
		setCheckedItem(currentItem)
	}

	/**
	 * 获取对应位置的item对象
	 */
	fun findItem(index: Int): BottomTabItem {
		if (index !in 0..childCount)
			throw NullPointerException("index must be less than $childCount")
		return menuList[index]
	}

	/**
	 * 查询对应item的所在位置
	 */
	fun indexOf(bottomTabItem: BottomTabItem): Int = menuList.indexOf(bottomTabItem)

	/**
	 * 更新视图
	 * 如果是因为数据更新之后需要更新界面，请调用 init 方法
	 */
	private fun updateView() {
		menuList.forEach {
			addView(createItemView(it))
		}
	}

	/**
	 * 根据配置项绘制对应位置的图标
	 */
	private fun drawDrawable(bottomTabItem: BottomTabItem): Drawable? {
		val size = DensityTools.dp2px(context, config.itemIconSize)
		if (!bottomTabItem.isChecked) {//如果是未选中的项，使用纯色填充
			val drawable = bottomTabItem.getUnSelectedDrawable(context)
			DrawableCompat.setTint(drawable.mutate(), config.unSelectedColor)
			return ImageUtil.zoomDrawable(context, drawable, size, size)
		}
		val drawable = bottomTabItem.getSelectedDrawable(context)
		val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
		val canvas = Canvas(bitmap)
		drawable.setBounds(0, 0, canvas.width, canvas.height)
		drawable.draw(canvas)
		val scaleBitmap = ImageUtil.zoomBitmap(bitmap, size, size)
		if (config.isShowGradientColors) {
			val gradientCanvas = Canvas(scaleBitmap)
			val bitmapShader = BitmapShader(scaleBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
			//创建线性着色器并配置着色
			val linearGradient = LinearGradient(size / 2f, 0f, size / 2f, size, config.gradientColors, null, Shader.TileMode.CLAMP)
			val composeShader = ComposeShader(bitmapShader, linearGradient, PorterDuff.Mode.MULTIPLY)
			val paint = Paint()
			paint.shader = composeShader
			gradientCanvas.drawRect(0f, 0f, size, size, paint)
		}
		bitmap.recycle()
		return BitmapDrawable(context.resources, scaleBitmap)
	}

	/**
	 * 根据item对象获取对应位置绘制的view
	 */
	private fun createItemView(bottomTabItem: BottomTabItem): View {
		val itemView = inflater.inflate(R.layout.layout_bottom_tab_item, null)
		if (!config.isShowRipple)
			itemView.background = null
		val line = itemView.findViewById<View>(R.id.line)
		line.setBackgroundColor(config.lineColor)
		val lineLayoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, config.lineHeight.toInt())
		line.layoutParams = lineLayoutParams
		val frameLayoutParams = LayoutParams(0, LayoutParams.WRAP_CONTENT, 1f)
		itemView.layoutParams = frameLayoutParams
		val textView = itemView.findViewById<TextView>(R.id.textView)
		textView.text = bottomTabItem.name
		textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, config.itemTextSize)
		textView.setTextColor(config.unSelectedColor)
		textView.setCompoundDrawablesWithIntrinsicBounds(null, drawDrawable(bottomTabItem), null, null)
		textView.compoundDrawablePadding = config.iconMargin.toInt()
		val textViewLayoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT)
		textViewLayoutParams.setMargins(0, DensityTools.dp2px(context, config.marginTop).toInt(), 0, DensityTools.dp2px(context, config.marginBottom).toInt())
		textViewLayoutParams.gravity = Gravity.CENTER
		textView.layoutParams = textViewLayoutParams
		textView.gravity = Gravity.CENTER
		itemView.setOnClickListener {
			val position = indexOfChild(it)
			if (!menuList[position].isChecked) {
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