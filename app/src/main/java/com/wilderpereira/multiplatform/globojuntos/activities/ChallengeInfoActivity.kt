package com.wilderpereira.multiplatform.globojuntos.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.wilderpereira.multiplatform.globojuntos.R
import com.wilderpereira.multiplatform.globojuntos.extensions.toggleVisibility
import com.wilderpereira.multiplatform.globojuntos.models.Challenge
import kotlinx.android.synthetic.main.activity_challenge_info.*
import kotlinx.android.synthetic.main.question_item.*

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
            }

        }

        btnOption1.setOnClickListener { questionClick() }
        btnOption2.setOnClickListener { questionClick() }

    }

    private fun questionClick() {
        questionCardView.toggleVisibility()
        questionUserInfoCardView.toggleVisibility()
        progressBar.progress = progressBar.progress + 1
    }
}
