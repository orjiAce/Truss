package com.example.truss.activities.Interface

// this interface contains callbacks to notify MainActivity.kt of clicks/taps or long clicks(or long taps) in the view holder of RecyclerView
interface ViewHolderClickListener {

    fun onLongTap(index : Int)
    fun onTap(index : Int)
}