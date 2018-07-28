package vip.mystery0.example.bottomtabview

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

	private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
		when (item.itemId) {
			R.id.navigation_home -> {
				message.setText(R.string.title_home)
				return@OnNavigationItemSelectedListener true
			}
			R.id.navigation_dashboard -> {
				message.setText(R.string.title_dashboard)
				return@OnNavigationItemSelectedListener true
			}
			R.id.navigation_notifications -> {
				message.setText(R.string.title_notifications)
				return@OnNavigationItemSelectedListener true
			}
		}
		false
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main2)

		navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
	}
}
