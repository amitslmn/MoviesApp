package com.amit.moviesapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp22.R
import com.amit.moviesapp.searchActors.SearchActorResponse
import com.squareup.picasso.Picasso

class ActorAdapter (
    var content: Context,
    var list: List<SearchActorResponse>,
    var actorClickListener: OnActorClickedListener
) : RecyclerView.Adapter<ViewHolders>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolders {
        val view : View = LayoutInflater.from(content).inflate(R.layout.card_view,parent,false)
        return ViewHolders(view)
    }

    override fun onBindViewHolder(holder: ViewHolders, position: Int) {
        holder.title.text = list[position].getName()
        Picasso.get().load(
            list[position].getProfilePath()).into(holder.poster)
        holder.homeContainer.setOnClickListener {
            holder.homeContainer.setOnClickListener {
                actorClickListener.getActorId(list[position].getId()!!)
                actorClickListener.getActorName(list[position].getName()!!)
                actorClickListener.getActorPopularity(list[position].getPopularity()!!)
                actorClickListener.getActorOriginalName(list[position].getOriginalName()!!)
                actorClickListener.getActorGender(list[position].getGender()!!)
                actorClickListener.getActorKnownForDepartment(list[position].getKnownForDepartment()!!)
                actorClickListener.getActorAdult(list[position].getAdult()!!)
                actorClickListener.getProfilePath(list[position].getProfilePath())

            }
        }

    }

    override fun getItemCount(): Int {
        return  list.size
    }

}
class ViewHolders(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var poster: ImageView = itemView.findViewById(R.id.imageView)
    var title: TextView = itemView.findViewById(R.id.actorName)
    var homeContainer: CardView = itemView.findViewById(R.id.homeContainer)

}