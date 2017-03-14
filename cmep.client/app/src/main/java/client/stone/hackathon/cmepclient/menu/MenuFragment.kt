package client.stone.hackathon.cmepclient.menu


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import client.stone.hackathon.cmepclient.R
import client.stone.hackathon.cmepclient.detail.DetailActivity
import client.stone.hackathon.cmepclient.entity.Item
import client.stone.hackathon.cmepclient.util.FirebaseConstants
import com.bumptech.glide.Glide
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_menu.*
import kotlinx.android.synthetic.main.item_grid_menu.view.*


/**
 * A simple [Fragment] subclass.
 */
class MenuFragment : Fragment() {
    lateinit var mAdapter: FirebaseRecyclerAdapter<Item, MenuHolder>
    lateinit var mRef: DatabaseReference
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater!!.inflate(R.layout.fragment_menu, container, false)
        return v
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gridMenu.setHasFixedSize(true)
        gridMenu.layoutManager = GridLayoutManager(activity, 2)
        execute()
    }

    fun initReference() {
        mRef = FirebaseDatabase.getInstance().reference.child(FirebaseConstants.MENU)
    }

    fun createAdapter() {
        mAdapter = object : FirebaseRecyclerAdapter<Item, MenuHolder>(Item::class.java, R.layout.item_grid_menu, MenuHolder::class.java, mRef) {
            public override fun populateViewHolder(holder: MenuHolder, menu: Item, position: Int) {
                holder.itemView.setOnClickListener {
                    val intent = Intent(activity, DetailActivity::class.java)
                    intent.putExtra("menu", menu)
                    activity.startActivity(intent)
                    activity.finish()
                }
                Glide.with(activity).load(menu.foto).into(holder.itemView.foto)
                holder.itemView.nome.text = menu.nome
            }
        }
        gridMenu.adapter = mAdapter
    }

    fun execute() {
        initReference()
        createAdapter()
    }
}
