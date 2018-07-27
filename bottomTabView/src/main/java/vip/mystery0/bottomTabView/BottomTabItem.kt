package vip.mystery0.bottomTabView

import androidx.annotation.DrawableRes

data class BottomTabItem(
		val name: String,
		@DrawableRes
		val icon: Int
) {
	var isChecked = false
}