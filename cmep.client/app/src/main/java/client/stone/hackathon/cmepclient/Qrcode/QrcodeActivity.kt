package client.stone.hackathon.cmepclient.Qrcode

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar

import client.stone.hackathon.cmepclient.R

class QrcodeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qrcode)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        toolbar.title = "QR Code"
        setSupportActionBar(toolbar)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        setResult(0)
    }
}
