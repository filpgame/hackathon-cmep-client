package client.stone.hackathon.cmepclient.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import client.stone.hackathon.cmepclient.R
import client.stone.hackathon.cmepclient.menu.MenuFragment
import client.stone.hackathon.cmepclient.order.OrderFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                replace(MenuFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                replace(OrderFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                Toast.makeText(this, "Em breve", Toast.LENGTH_SHORT).show()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        mOnNavigationItemSelectedListener.onNavigationItemSelected(navigation.menu.getItem(0))
    }

    fun replace(fragment: Fragment) {
        supportFragmentManager.beginTransaction().add(R.id.container, fragment).commit()
    }
}
