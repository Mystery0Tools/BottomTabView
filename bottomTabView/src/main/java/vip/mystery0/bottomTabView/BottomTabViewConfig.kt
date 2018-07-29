package vip.mystery0.bottomTabView

import android.graphics.Color
import androidx.annotation.ColorInt

class BottomTabViewConfig {
	@ColorInt
	var selectedColor: Int = Color.BLACK
	@ColorInt
	var unSelectedColor: Int = Color.BLUE

	var marginTop = 2f//单位dp
	var marginBottom = 2f//单位dp
	var itemTextSize = 14f//单位sp
	var itemIconSize = 24f//单位dp
	var iconMargin = 2f//图标间距,单位dp

	var lineHeight = 0.33f//单位dp
	@ColorInt
	var lineColor = Color.parseColor("#e5e5e5")
	@ColorInt
	var gradientColors: IntArray = intArrayOf(Color.BLUE, Color.GREEN)

	var isShowGradientColors = false//是否显示渐变的图标色
	var isShowRipple = true//是否显示水波效果

	fun setSelectedColor(@ColorInt selectedColor: Int): BottomTabViewConfig {
		this.selectedColor = selectedColor
		return this
	}

	fun setUnSelectedColor(@ColorInt unSelectedColor: Int): BottomTabViewConfig {
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

	fun setLineHeight(lineHeight: Float): BottomTabViewConfig {
		this.lineHeight = lineHeight
		return this
	}

	fun setItemIconSize(itemIconSize: Float): BottomTabViewConfig {
		this.itemIconSize = itemIconSize
		return this
	}

	fun setGradientColors(gradientColors: IntArray): BottomTabViewConfig {
		if (gradientColors.size < 2)
			throw NumberFormatException("color size must be more than 1")
		this.gradientColors = gradientColors
		return this
	}

	fun isShowGradientColors(isShowGradientColors: Boolean): BottomTabViewConfig {
		this.isShowGradientColors = isShowGradientColors
		return this
	}

	fun isShowRipple(isShowRipple: Boolean): BottomTabViewConfig {
		this.isShowRipple = isShowRipple
		return this
	}
}