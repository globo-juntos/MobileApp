package com.wilderpereira.multiplatform.globojuntos.activities

import android.content.Intent
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.firebase.database.*
import com.wilderpereira.multiplatform.globojuntos.R
import com.wilderpereira.multiplatform.globojuntos.extensions.toggleVisibility
import com.wilderpereira.multiplatform.globojuntos.models.Challenge
import com.wilderpereira.multiplatform.globojuntos.repository.saveVote
import kotlinx.android.synthetic.main.activity_challenge_info.*


class ChallengeInfoActivity : AppCompatActivity() {

    private val mDatabase: DatabaseReference = FirebaseDatabase.getInstance().getReference()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_challenge_info)

        setSupportActionBar(toolbarr)

        supportActionBar?.setHomeButtonEnabled(true);
        supportActionBar?.setDisplayHomeAsUpEnabled(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.statusBarColor = resources.getColor(R.color.colorPrimaryDark, this.theme);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = this.resources.getColor(R.color.colorPrimaryDark);
        }

        val bundle: Bundle? = intent.extras
        val challenge = bundle?.getParcelable("question") as Challenge?

        challenge?.apply {
            challengeTitleTv.text = title
            challengeDescriptionTv.text = description
            btnOption1.text = option1
            btnOption2.text = option2
            challengeImageView.setImageBitmap(challenge.image)

            btnOption1.backgroundTintList = resources.getColorStateList(challenge.color)
            btnOption2.backgroundTintList = resources.getColorStateList(challenge.color)
            questionUserInfoOption1.backgroundTintList = resources.getColorStateList(challenge.color)
            questionUserInfoOption2.backgroundTintList = resources.getColorStateList(challenge.color)
            questionUserInfoOption3.backgroundTintList = resources.getColorStateList(challenge.color)

            shareButton.backgroundTintList = resources.getColorStateList(challenge.color)
            shareButton.setOnClickListener {
                val sharingIntent = Intent(android.content.Intent.ACTION_SEND)
                sharingIntent.type = "text/plain"
                startActivity(Intent.createChooser(sharingIntent, "Compartilhar via via"))
            }


            progressBar.indeterminateDrawable.setColorFilter(resources.getColor(challenge.color), PorterDuff.Mode.SRC_IN)
            progressBar.progressDrawable.setColorFilter(resources.getColor(challenge.color), PorterDuff.Mode.SRC_IN)

            this.userInfoQuestion?.apply {
                questionUserInfoTitleTv.text = title
                questionUserInfoDescriptionTv.text = description

                questionUserDateInfoTitleTv.text = title
                questionUserDateInfoDescriptionTv.text = description

                questionUserInfoOption1.text = option1
                questionUserInfoOption2.text = option2
                if (!option3.isNullOrBlank()) {
                    questionUserInfoOption3.toggleVisibility()
                    questionUserInfoOption3.text = option3
                }
                this.shareInfo?.apply {
                    shareTitleTv.text = this.title
                    shareDescriptionTv.text = this.subtitle
                    shareDescription2Tv.text = this.shareText
                }
            }

        }

        btnOption1.setOnClickListener {
            questionCardView.toggleVisibility()

            registerVote(op= 1, id= challenge?.id)

            if (challenge?.id == "1" || challenge?.id == "1000") {
                questionUserInfoCardView.toggleVisibility()
            } else {
                questionUserDateInfo.toggleVisibility()
            }

            incrementProgress()
        }
        btnOption2.setOnClickListener {
            questionCardView.toggleVisibility()
            registerVote(op= 2, id= challenge?.id)
            if (challenge?.id == "1" || challenge?.id == "1000") {
                questionUserInfoCardView.toggleVisibility()
            } else {
                questionUserDateInfo.toggleVisibility()
            }
            incrementProgress()
        }

        questionUserInfoOption1.setOnClickListener {
            questionUserInfoCardView.toggleVisibility()
            shareCardView.toggleVisibility()
            incrementProgress()
        }

        questionUserInfoOption2.setOnClickListener {
            questionUserInfoCardView.toggleVisibility()
            shareCardView.toggleVisibility()
            incrementProgress()
        }

        questionUserInfoOption3.setOnClickListener {
            questionUserInfoCardView.toggleVisibility()
            shareCardView.toggleVisibility()
            incrementProgress()
        }

        closeBtn.setOnClickListener {
            this.finish()
        }

        questionUserDateInfoContinueBtn.setOnClickListener {
            questionUserInfoCardView.toggleVisibility()
            shareCardView.toggleVisibility()
            incrementProgress()
        }

    }

    private fun registerVote(op: Int, id: String?) {
        saveVote(this)
        if (id == "1000") {
            mDatabase.child("votos").child("op$op").addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError?) {
                }

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    var value = dataSnapshot.value as Long
                    value += 1
                    dataSnapshot.ref.setValue(value)
                }
            })

        }
    }

    private fun incrementProgress() {
        progressBar.progress = progressBar.progress + 1
    }
}
