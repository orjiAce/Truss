package com.example.truss.activities.Adapters

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.truss.R
import com.example.truss.activities.InterestActivity
import com.example.truss.activities.Interface.MainInterface
import com.example.truss.activities.Interface.ViewHolderClickListener
import com.example.truss.activities.Model.Interests
import com.example.truss.activities.MyViewHolder
import java.util.ArrayList
//Adapter requires Context and MainInterface to be passed into its constructor
//This adapter class  extends RecyclerView.Adapter<MyViewHolder> as required, in order to qualify as a RecyclerView Adapter



//MyAdapter implements ViewHolderClickListerner with onLongTap and onTap callbacks.
//onLongTap callback is executed when a user long clicks a viewHolder. It accepts the position of the viewHolder that was long clicked.
// The code here checks if MultiSelection mode is enabled, if not set it to true then calls addIDIntoSelectedIds while passing the position of the viewHolder that was clicked.
class InterestAdapter (val context: Context, val mainInterface: MainInterface) : RecyclerView.Adapter<MyViewHolder>(),
    ViewHolderClickListener {


    override fun onLongTap(index: Int) {
        if (!InterestActivity.isMultiSelectOn) {
            InterestActivity.isMultiSelectOn = true

            addIDIntoSelectedIds(index)
        }
        if(selectedIds.size > 4){
            InterestActivity.isMultiSelectOn = false
            addIDIntoSelectedIds(index)
        }





    }

    override fun onTap(index: Int) {
        if (InterestActivity.isMultiSelectOn) {
            addIDIntoSelectedIds(index)

            // Toast.makeText(context, "Clicked Item @ Position"+ modelList[index].title, Toast.LENGTH_SHORT).show()
        }

    }

    //this is a function that gets the Id of the model which is got from the position of indexOfModel in modelList in the recyclerView.
    fun addIDIntoSelectedIds(index: Int) {
        val id = modelList[index].id
        if (selectedIds.contains(id))
            selectedIds.remove(id)
        else
            selectedIds.add(id)

        notifyItemChanged(index)
        //checks if selected interest is between 0 and 5
        if (selectedIds.size == 0 ){
            InterestActivity.isMultiSelectOn = true
            mainInterface.mainInterface(selectedIds.size)

        }
        if (selectedIds.size == 5) {
            InterestActivity.isMultiSelectOn = false
            mainInterface.mainInterface(selectedIds.size)
            Toast.makeText(context, "You can only select up to "+selectedIds.size, Toast.LENGTH_LONG).show()


        }
       // if(selectedIds.size > 4)  InterestActivity.isMultiSelectOn = false
    }

    var modelList: MutableList<Interests> = ArrayList<Interests>()
    //selectedIds is a list that stores the Ids of selected models
    val selectedIds: MutableList<String> = ArrayList<String>()

//returns the size of Models in the RecyclerView in order to let the onBindViewHolder know how many models it will bind to the viewHolder.
    override fun getItemCount() = modelList.size



    @RequiresApi(Build.VERSION_CODES.M)
    //binds Models to ViewHolder (new or recycled) and is responsible for adding or removing overlay effect (foreground color) to the
// viewHolder depending on whether the Id of the model to be inflated is present in the list of selectedIDs.
    override fun onBindViewHolder(holder: MyViewHolder, index: Int) {


        holder?.textView?.setText(modelList[index].title)

        val id = modelList[index].id

        if (selectedIds.contains(id)) {
            //if item is selected then,set foreground color of FrameLayout.
            holder?.cardInterest?.foreground = ColorDrawable(ContextCompat.getColor(context, R.color.colorAccent))
           Toast.makeText(context, "This item is selected"+ modelList[index].title, Toast.LENGTH_SHORT).show()


        } else {
            //tells which item has been deselected

            //else remove selected item color.
            holder?.cardInterest?.foreground = ColorDrawable(ContextCompat.getColor(context, android.R.color.transparent))

        }
    }

    fun deleteSelectedIds() {
        if (selectedIds.size < 1) return
        val selectedIdIteration = selectedIds.listIterator();

        while (selectedIdIteration.hasNext()) {
            val selectedItemID = selectedIdIteration.next()
            Log.d(InterestActivity.TAG, "The ID is $selectedItemID")
            var indexOfModelList = 0
            val modelListIteration: MutableListIterator<Interests> = modelList.listIterator();
            while (modelListIteration.hasNext()) {
                val model = modelListIteration.next()
                if (selectedItemID.equals(model.id)) {
                    modelListIteration.remove()
                    selectedIdIteration.remove()
                    notifyItemRemoved(indexOfModelList)
                }
                indexOfModelList++
            }

            InterestActivity.isMultiSelectOn = false
        }
    }


//this creates ViewHolders by inflating view_holder_layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val inflater = LayoutInflater.from(parent?.context)
        val itemView = inflater.inflate(R.layout.interests_card_item, parent, false)
        return MyViewHolder(itemView, this)

    }

}