package com.wilderpereira.multiplatform.globojuntos.adapters


import android.content.res.ColorStateList
import android.support.v4.view.ViewCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.wilderpereira.multiplatform.globojuntos.models.Challenge
import com.wilderpereira.multiplatform.globojuntos.R
import com.wilderpereira.multiplatform.globojuntos.extensions.toggleVisibility
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener



class ChallengesAdapter(val list: List<Challenge>, val clickListener: (Challenge) -> Unit) : RecyclerView.Adapter<ChallengeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChallengeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ChallengeViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ChallengeViewHolder, position: Int) {
        val challenge: Challenge = list[position]
        holder.bind(challenge, clickListener)
    }

    override fun getItemCount(): Int = list.size

}



class ChallengeViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.question_item, parent, false)) {

    private var questionContainter: LinearLayout? = null
    private var activeUsersContainer: LinearLayout? = null
    private var timeContainer: LinearLayout? = null
    private var titleTv: TextView? = null
    private var statusTv: TextView? = null
    private var activeUsersCountTv: TextView? = null
    private var progressBar: View? = null
    private var imageView: ImageView? = null


    init {
        titleTv = itemView.findViewById(R.id.titleTv)
//        descriptionTv = itemView.findViewById(R.id.descriptionTv)
        activeUsersCountTv = itemView.findViewById(R.id.activeUsersCountTv)
        questionContainter = itemView.findViewById(R.id.questionContainter)
        statusTv = itemView.findViewById(R.id.status)
        progressBar = itemView.findViewById(R.id.progressBar)
        imageView = itemView.findViewById(R.id.imageView)
        activeUsersContainer = itemView.findViewById(R.id.activeUsersContainer)
        timeContainer = itemView.findViewById(R.id.timeContainer)
    }


    fun bind(challenge: Challenge, clickListener: (Challenge) -> Unit) {

        imageView?.setImageBitmap(challenge.image)

        if (challenge.friendsCount == -1) {
            activeUsersContainer?.toggleVisibility()
        }

        statusTv?.text = challenge.status

        if (challenge.status == "AO VIVO") {

            questionContainter?.setOnClickListener { clickListener(challenge) }

            val green = statusTv!!.resources.getColor(R.color.green)
            ViewCompat.setBackgroundTintList(
                    statusTv as View,
                    ColorStateList.valueOf(green));

            progressBar?.setBackgroundColor(green)

        } else if (challenge.status == "PRÓXIMO") {

            questionContainter?.setOnClickListener { clickListener(challenge) }

            val yellow = statusTv!!.resources.getColor(R.color.yellow)
            ViewCompat.setBackgroundTintList(
                    statusTv as View,
                    ColorStateList.valueOf(yellow))

            progressBar?.setBackgroundColor(yellow)
        } else {
            timeContainer?.toggleVisibility()
        }

        titleTv?.text = challenge.title
//        descriptionTv?.text = challenge.description
        activeUsersCountTv?.text = "${challenge.friendsCount} participantes"


        if (challenge.id == "1000") {
            listenToAudience()
        }


    }

    private fun listenToAudience() {
        val mDatabase = FirebaseDatabase.getInstance().reference.child("votos")

        val activeUsersListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val map = dataSnapshot.value as Map<String, Long>
                activeUsersCountTv?.text = "${map.values.sum()} participantes"
            }

            override fun onCancelled(databaseError: DatabaseError) {
            }
        }
        mDatabase.addValueEventListener(activeUsersListener)
    }


}

