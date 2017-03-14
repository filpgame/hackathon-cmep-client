package client.stone.hackathon.cmepclient.detail

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import client.stone.hackathon.cmepclient.R
import client.stone.hackathon.cmepclient.entity.Item
import client.stone.hackathon.cmepclient.util.FirebaseConstants
import com.bumptech.glide.Glide
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.SimpleTarget
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.*


class DetailActivity : AppCompatActivity() {
    lateinit var mRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)
        val menu = intent.getSerializableExtra("menu") as Item
        supportActionBar?.setDisplayShowTitleEnabled(false)
        toolbarLayout.title = ""
        toolbar.title = ""
        Glide.with(this).load(menu.foto).asBitmap().into(object : SimpleTarget<Bitmap>(300, 300) {
            override fun onResourceReady(resource: Bitmap, glideAnimation: GlideAnimation<in Bitmap>) {
                val drawable = BitmapDrawable(resource)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    toolbarLayout.background = drawable
                }
            }
        })
        productName.text = menu.nome
        productDescription.text = menu.descricao
        productPrice.text = "R$ " + menu.preco.toString()

        mRef = FirebaseDatabase.getInstance().reference

        val fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { view ->
            fab.isClickable = false
            addItemToCart(menu, view)
        }
    }

    fun addItemToCart(item: Item, view: View) {
        if (FirebaseAuth.getInstance().currentUser != null)
            item.status = 1
        val id = mRef.push().key
        item.id = id
        mRef.child(FirebaseConstants.TABLE).child("7").child(FirebaseAuth.getInstance().currentUser?.uid).child(id).setValue(item, { databaseError, databaseReference ->
            if (databaseError != null) {
                fab.isClickable = true
                Toast.makeText(this, "Erro ao realizar pedido", Toast.LENGTH_LONG).show()
                finish()
            } else {
                fab.isClickable = false
                Toast.makeText(this, "Pedido realizado", Toast.LENGTH_LONG).show()
                finish()
            }
        })
    }
}
