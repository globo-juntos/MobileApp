package com.wilderpereira.multiplatform.globojuntos.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.wilderpereira.multiplatform.globojuntos.R
import com.wilderpereira.multiplatform.globojuntos.extensions.toggleVisibility
import com.wilderpereira.multiplatform.globojuntos.models.Challenge
import kotlinx.android.synthetic.main.activity_challenge_info.*

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

            this.userInfoQuestion?.apply {
                questionUserInfoTitleTv.text = title
                questionUserInfoDescriptionTv.text = description
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
            questionUserInfoCardView.toggleVisibility()
            incrementProgress()
        }
        btnOption2.setOnClickListener {
            questionUserInfoCardView.toggleVisibility()
            questionUserInfoCardView.toggleVisibility()
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

    }

    private fun incrementProgress() {
        progressBar.progress = progressBar.progress + 1
    }
}
