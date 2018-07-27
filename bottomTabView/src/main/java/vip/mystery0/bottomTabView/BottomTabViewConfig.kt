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
	var itemTextSize = 14f
	var itemIconSize = 24

	var lineHeight = 1
	@ColorInt
	var lineColor = Color.parseColor("#e5e5e5")

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
}