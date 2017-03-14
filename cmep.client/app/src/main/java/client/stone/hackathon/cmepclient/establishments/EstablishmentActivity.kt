package client.stone.hackathon.cmepclient.establishments

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import client.stone.hackathon.cmepclient.R
import client.stone.hackathon.cmepclient.entity.Establishment
import client.stone.hackathon.cmepclient.main.MainActivity
import client.stone.hackathon.cmepclient.menu.MenuHolder
import client.stone.hackathon.cmepclient.util.FirebaseConstants
import com.bumptech.glide.Glide
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_establishment.*
import kotlinx.android.synthetic.main.item_grid_establishment.view.*

class EstablishmentActivity : AppCompatActivity() {
    lateinit var mRef: DatabaseReference
    lateinit var mAdapter: FirebaseRecyclerAdapter<Establishment, MenuHolder>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_establishment)
        execute()
    }

    fun execute() {
        initReference()
        configureList()
        createAdapter()
    }

    fun configureList() {
        establishmentList.setHasFixedSize(true)
        establishmentList.layoutManager = GridLayoutManager(this, 2)
    }

    fun createAdapter() {
        mAdapter = object : FirebaseRecyclerAdapter<Establishment, MenuHolder>(Establishment::class.java, R.layout.item_grid_establishment, MenuHolder::class.java, mRef) {
            public override fun populateViewHolder(holder: MenuHolder, establishment: Establishment, position: Int) {
                holder.itemView.setOnClickListener {
                    startActivity(Intent(this@EstablishmentActivity, MainActivity::class.java))
                    finish()
                }
                Glide.with(this@EstablishmentActivity).load(establishment.url).into(holder.itemView.establishment)
            }
        }
        establishmentList.adapter = mAdapter
    }

    fun initReference() {

        mRef = FirebaseDatabase.getInstance().reference.child(FirebaseConstants.ESTABLISHMENTS)
        mRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                Log.e("data", dataSnapshot.toString())
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
    }
}
