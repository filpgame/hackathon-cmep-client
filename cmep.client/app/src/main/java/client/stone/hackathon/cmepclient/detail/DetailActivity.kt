package client.stone.hackathon.cmepclient.detail

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import client.stone.hackathon.cmepclient.R
import client.stone.hackathon.cmepclient.entity.Menu
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
        val menu = intent.getSerializableExtra("menu") as Menu
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
            addItemToCard(menu, view)
        }
    }

    fun addItemToCard(item: Menu, view: View) {
        if (FirebaseAuth.getInstance().currentUser != null)
            mRef.child(FirebaseConstants.TABLE).child("7").child(FirebaseAuth.getInstance().currentUser?.uid).setValue(item, { databaseError, databaseReference ->
                if (databaseError != null) {
                    Snackbar.make(view, "Erro ao realizar pedido", Snackbar.LENGTH_LONG).show()
                } else {
                    Snackbar.make(view, "Pedido realizado", Snackbar.LENGTH_LONG).show()
                }
            })
    }
}
