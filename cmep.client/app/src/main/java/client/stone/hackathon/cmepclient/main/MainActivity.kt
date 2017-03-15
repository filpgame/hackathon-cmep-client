package client.stone.hackathon.cmepclient.main

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import client.stone.hackathon.cmepclient.Qrcode.QrcodeActivity
import client.stone.hackathon.cmepclient.R
import client.stone.hackathon.cmepclient.menu.MenuFragment
import client.stone.hackathon.cmepclient.order.OrderFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var tableText = "qr code"
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu!!.add(tableText).setOnMenuItemClickListener {
            startActivityIfNeeded(Intent(applicationContext, QrcodeActivity::class.java), 0)
            false
        }.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        tableText = "Mesa 1"
        invalidateOptionsMenu()
    }
}
