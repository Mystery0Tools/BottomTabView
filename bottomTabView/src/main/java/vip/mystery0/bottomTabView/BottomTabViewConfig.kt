package vip.mystery0.bottomTabView

import android.graphics.Color
import androidx.annotation.ColorInt

class BottomTabViewConfig {
	@ColorInt
	var selectedColor: Int = Color.BLACK
	@ColorInt
	var unSelectedColor: Int = Color.BLUE

	var marginTop: Float = 8f
	var marginBottom: Float = 8f
	var itemTextSize = 14f//单位sp
	var itemIconSize = 24//单位dp

	var lineHeight = 1//单位px
	@ColorInt
	var lineColor = Color.parseColor("#e5e5e5")
	@ColorInt
	var gradientColors: IntArray = intArrayOf(Color.BLUE, Color.GREEN)

	var isRippleShow = true//是否显示水波效果

	fun setSelectedColor(@ColorInt selectedColor: Int): BottomTabViewConfig {
		this.selectedColor = selectedColor
		return this
	}

	fun setUnSeletedColor(@ColorInt unSelectedColor: Int): BottomTabViewConfig {
		this.unSelectedColor = unSelectedColor
		return this
	}

	fun setMarginTop(marginTop: Float): BottomTabViewConfig {
		this.marginTop = marginTop
		return this
	}

	fun setMarginBottom(marginBottom: Float): BottomTabViewConfig {
		this.marginBottom = marginBottom
		return this
	}

	fun setItemTextSize(itemTextSize: Float): BottomTabViewConfig {
		this.itemTextSize = itemTextSize
		return this
	}

	fun setLineHeight(lineHeight: Int): BottomTabViewConfig {
		this.lineHeight = lineHeight
		return this
	}

	fun setItemIconSize(itemIconSize: Int): BottomTabViewConfig {
		this.itemIconSize = itemIconSize
		return this
	}

	fun setGradientColors(gradientColors: IntArray): BottomTabViewConfig {
		if (gradientColors.size<2)
			throw NumberFormatException("color size must be more than 1")
		this.gradientColors = gradientColors
		return this
	}
}