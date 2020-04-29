package com.example.truss.activities

import android.view.View
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.truss.R
import com.example.truss.activities.Adapters.InterestAdapter
import com.example.truss.activities.Interface.ViewHolderClickListener
//RecyclerView mandates the viewHolder pattern – all it does is make our RecyclerView memory efficient by re-using views to inflate modeled data
class MyViewHolder (itemView: View, val r_tap: ViewHolderClickListener) : RecyclerView.ViewHolder(itemView),
    View.OnLongClickListener, View.OnClickListener {
// View.onClickListener and View.onLongClickListener was implemented on our ViewHolder
// (instead of setting anonymous click listener) and click listeners were set to the view (in this case, the frame layout)
    val textView: TextView
    val cardViewLayout: LinearLayout
    val cardInterest: CardView

    init {

        cardInterest = itemView.findViewById(R.id.cardInterest)
        textView = itemView.findViewById(R.id.interestName)
        cardViewLayout = itemView.findViewById(R.id.cardViewLayout)
        cardViewLayout.setOnClickListener(this)
        cardViewLayout.setOnLongClickListener(this)
    }
    override fun onClick(v: View?) {
        r_tap.onTap(adapterPosition)
    }

    override fun onLongClick(v: View?): Boolean {

        r_tap.onLongTap(adapterPosition)
        return true
    }

}