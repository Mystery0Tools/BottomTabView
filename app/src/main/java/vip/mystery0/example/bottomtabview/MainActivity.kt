package vip.mystery0.example.bottomtabview

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import vip.mystery0.bottomTabView.BottomTabItem
import vip.mystery0.bottomTabView.BottomTabViewConfig
import java.util.*

class MainActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		button.setOnClickListener {
			val rr = Random().nextInt(3)
			bottomTabView.setCheckedItem(rr)
		}

		val menuList = arrayListOf(
				BottomTabItem("11", R.drawable.ic_android_black_24dp),
				BottomTabItem("12", R.drawable.ic_android_black_24dp),
				BottomTabItem("33133", R.drawable.ic_android_black_24dp)
		)
		bottomTabView.setConfig(BottomTabViewConfig()
				.setSelectedColor(Color.BLACK)
				.setUnSelectedColor(Color.BLUE)
				.setMarginTop(16f)
				.setMarginBottom(24f)
				.setItemIconSize(24f)
				.setItemTextSize(24f)
				.setLineHeight(1f)
				.isShowRipple(true)
				.isShowGradientColors(false)
				.setGradientColors(intArrayOf(Color.WHITE, Color.BLACK)))
				.init()
		bottomTabView.setMenuList(menuList).init()
		bottomTabView.setOnItemSelectedListener {
			println(it.name)
		}
	}
}
