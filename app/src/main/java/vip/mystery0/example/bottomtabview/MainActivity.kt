package vip.mystery0.example.bottomtabview

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import vip.mystery0.bottomTabView.BottomTabItem
import java.util.*

class MainActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		button.setOnClickListener {
			val rr = Random().nextInt(3)
			bottomTabView.setCheckedItem(rr)
		}
		bottomTabView.setMenuList(arrayListOf(
				BottomTabItem("11", R.drawable.ic_android_black_24dp),
				BottomTabItem("12", R.drawable.ic_android_black_24dp),
				BottomTabItem("33133", R.drawable.ic_android_black_24dp)))
				.config { it.setGradientColors(intArrayOf(Color.BLUE, Color.WHITE)) }
				.setOnItemSelectedListener {
					println(it.name)
				}
				.init()
	}
}
