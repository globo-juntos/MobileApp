package com.wilderpereira.multiplatform.globojuntos.activities

import android.graphics.PorterDuff
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.wilderpereira.multiplatform.globojuntos.R
import com.wilderpereira.multiplatform.globojuntos.extensions.toggleVisibility
import com.wilderpereira.multiplatform.globojuntos.models.Challenge
import kotlinx.android.synthetic.main.activity_challenge_info.*
import android.content.Intent



class ChallengeInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_challenge_info)


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

            if (challenge?.id == "1") {
                questionUserInfoCardView.toggleVisibility()
            } else {
                questionUserDateInfo.toggleVisibility()
            }

            incrementProgress()
        }
        btnOption2.setOnClickListener {
            questionCardView.toggleVisibility()
            if (challenge?.id == "1") {
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
            this.finish()
        }

    }

    private fun incrementProgress() {
        progressBar.progress = progressBar.progress + 1
    }
}
