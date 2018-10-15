package vip.mystery0.bottomTabView

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

class BottomTabItem {
	var name: String
	@DrawableRes
	var selectedIcon = 0
	@DrawableRes
	var unSelectedIcon = 0
	var isChecked = false
	private lateinit var selectedDrawable: Drawable
	private lateinit var unSelectedDrawable: Drawable

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

	fun getSelectedDrawable(context: Context): Drawable {
		if (!::selectedDrawable.isInitialized)
			selectedDrawable = ContextCompat.getDrawable(context, selectedIcon)!!
		return selectedDrawable
	}

	fun getUnSelectedDrawable(context: Context): Drawable {
		if (!::unSelectedDrawable.isInitialized)
			unSelectedDrawable = ContextCompat.getDrawable(context, selectedIcon)!!
		return unSelectedDrawable
	}
}