package client.stone.hackathon.cmepclient.order


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import client.stone.hackathon.cmepclient.R
import client.stone.hackathon.cmepclient.entity.Item
import client.stone.hackathon.cmepclient.menu.MenuHolder
import client.stone.hackathon.cmepclient.util.FirebaseConstants
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_order.*
import kotlinx.android.synthetic.main.item_order_list.view.*


/**
 * A simple [Fragment] subclass.
 */
class OrderFragment : Fragment() {

    lateinit var mAdapter: FirebaseRecyclerAdapter<Item, MenuHolder>
    lateinit var mRef: DatabaseReference
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_order, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        orderList.setHasFixedSize(true)
        orderList.layoutManager = LinearLayoutManager(activity)
        initReference()
        createAdapter()
    }

    fun initReference() {
        if (FirebaseAuth.getInstance().currentUser != null)
            mRef = FirebaseDatabase.getInstance().reference.child(FirebaseConstants.TABLE).child("7").child(FirebaseAuth.getInstance().currentUser?.uid)
    }

    fun createAdapter() {
        if (FirebaseAuth.getInstance().currentUser != null) {
            mAdapter = object : FirebaseRecyclerAdapter<Item, MenuHolder>(Item::class.java, R.layout.item_order_list, MenuHolder::class.java, mRef) {
                public override fun populateViewHolder(holder: MenuHolder, order: Item, position: Int) {
                    holder.itemView.productName.text = order.nome
                    when (order.status) {
                        1 -> holder.itemView.statusItem.text = "A fazer"
                        2 -> holder.itemView.statusItem.text = "Preparando"
                        3 -> holder.itemView.statusItem.text = "Servido"
                    }

                }
            }
            orderList.adapter = mAdapter
        }
    }

}
