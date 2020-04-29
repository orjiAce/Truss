package com.example.truss.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ActionMode
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.truss.R
import com.example.truss.activities.Adapters.InterestAdapter
import com.example.truss.activities.Interface.MainInterface
import com.example.truss.activities.Model.Interests
import com.robertlevonyan.views.chip.Chip
import com.robertlevonyan.views.chip.OnSelectClickListener
import java.util.*


class InterestActivity : AppCompatActivity(), MainInterface {
    override fun mainInterface(size: Int) {
        if (size > 0) actionMode?.setTitle("$size")
        else actionMode?.finish()
    }


    var actionMode: ActionMode? = null
    var myAdapter: InterestAdapter? = null

    companion object {
        var isMultiSelectOn = false
        val TAG = "InterestActivity"
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interest)

        val fadeIn = AnimationUtils.loadAnimation(this, R.anim.fadein)

        isMultiSelectOn = false


        val recyclerView = findViewById<RecyclerView>(R.id.card_holder)
        //recyclerView.layoutManager = LinearLayoutManager(this)
        val mGridLayoutManager = GridLayoutManager(this, 2)
        recyclerView.layoutManager = mGridLayoutManager

        recyclerView.startAnimation(fadeIn)
        myAdapter = InterestAdapter(this, this)
        recyclerView.adapter = myAdapter
        myAdapter?.modelList = getDummyData()
        myAdapter?.notifyDataSetChanged()


    }




    private fun getDummyData(): MutableList<Interests> {
        Log.d(TAG, "inside getDummyData")
        val list = ArrayList<Interests>()
        list.add(Interests(getRandomID(), " Social"))
        list.add(Interests(getRandomID(), " Technology"))
        list.add(Interests(getRandomID(), " Health"))
        list.add(Interests(getRandomID(), " Politics"))
        list.add(Interests(getRandomID(), " Education"))
        list.add(Interests(getRandomID(), " Fashion"))
        list.add(Interests(getRandomID(), " Business"))
        list.add(Interests(getRandomID(), " Sports"))
        list.add(Interests(getRandomID(), " Music"))
        list.add(Interests(getRandomID(), " Photography"))
        list.add(Interests(getRandomID(), " Movements"))
        list.add(Interests(getRandomID(), " Art"))

        Log.d(TAG, "The size is ${list.size}")
        return list
    }


    fun getRandomID() = UUID.randomUUID().toString()

    // Toast extension method
    fun Context.toast(message:String)=
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
}
