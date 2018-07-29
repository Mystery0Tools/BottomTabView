package vip.mystery0.bottomTabView

import androidx.annotation.DrawableRes

class BottomTabItem {
	var name: String
	@DrawableRes
	var selectedIcon = 0
	@DrawableRes
	var unSelectedIcon = 0
	var isChecked = false

	constructor(name: String, @DrawableRes icon: Int) {
		this.name = name
		this.selectedIcon = icon
		this.unSelectedIcon = icon
	}

	constructor(name: String, selectedIcon: Int, unSelectedIcon: Int) {
		this.name = name
		this.selectedIcon = selectedIcon
		this.unSelectedIcon = unSelectedIcon
	}
}