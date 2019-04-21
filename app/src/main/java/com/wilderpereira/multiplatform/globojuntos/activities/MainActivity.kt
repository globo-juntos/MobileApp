package com.wilderpereira.multiplatform.globojuntos.activities

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.wilderpereira.multiplatform.globojuntos.models.Challenge
import com.wilderpereira.multiplatform.globojuntos.R
import com.wilderpereira.multiplatform.globojuntos.adapters.ChallengesAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        questionsRv.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = ChallengesAdapter(listOf(
                    Challenge("","Solta os Cachorros!", "Até na páscoa a gente é MAIS VOCÊ! Qual receitinha você quer ver a Ana Maria Braga fazer para deixar essa data ainda mais deliciosa?", "Ovo de chocolate trufado", "Ovo de chocolate com recheio de limão", Challenge("", "Hm, até eu quero!", "Falando em páscoa, conta pra gente: que tipo de pessoa você é?", "Que gosta de tudo #Formiga", "Prefere chocolate branco ou ao leite", option3 = "Meio amargo ou zero açúcar #TeamFitness"), 365),
                    Challenge("","Solta os Cachorros!", "Até na páscoa a gente é MAIS VOCÊ! Qual receitinha você quer ver a Ana Maria Braga fazer para deixar essa data ainda mais deliciosa?", "Ovo de chocolate trufado", "Ovo de chocolate com recheio de limão", Challenge("", "Hm, até eu quero!", "Falando em páscoa, conta pra gente: que tipo de pessoa você é?", "Que gosta de tudo #Formiga", "Prefere chocolate branco ou ao leite", option3 = "Meio amargo ou zero açúcar #TeamFitness"), 365),
                    Challenge("","Solta os Cachorros!", "Até na páscoa a gente é MAIS VOCÊ! Qual receitinha você quer ver a Ana Maria Braga fazer para deixar essa data ainda mais deliciosa?", "Ovo de chocolate trufado", "Ovo de chocolate com recheio de limão", Challenge("", "Hm, até eu quero!", "Falando em páscoa, conta pra gente: que tipo de pessoa você é?", "Que gosta de tudo #Formiga", "Prefere chocolate branco ou ao leite", option3 = "Meio amargo ou zero açúcar #TeamFitness"), 365),
                    Challenge("","Solta os Cachorros!", "Até na páscoa a gente é MAIS VOCÊ! Qual receitinha você quer ver a Ana Maria Braga fazer para deixar essa data ainda mais deliciosa?", "Ovo de chocolate trufado", "Ovo de chocolate com recheio de limão", Challenge("", "Hm, até eu quero!", "Falando em páscoa, conta pra gente: que tipo de pessoa você é?", "Que gosta de tudo #Formiga", "Prefere chocolate branco ou ao leite", option3 = "Meio amargo ou zero açúcar #TeamFitness"), 365),
                    Challenge("","Solta os Cachorros!", "Até na páscoa a gente é MAIS VOCÊ! Qual receitinha você quer ver a Ana Maria Braga fazer para deixar essa data ainda mais deliciosa?", "Ovo de chocolate trufado", "Ovo de chocolate com recheio de limão", Challenge("", "Hm, até eu quero!", "Falando em páscoa, conta pra gente: que tipo de pessoa você é?", "Que gosta de tudo #Formiga", "Prefere chocolate branco ou ao leite", option3 = "Meio amargo ou zero açúcar #TeamFitness"), 365)
            )) { challenge -> goToChallengeInfo(challenge)}
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

    }

    private fun goToChallengeInfo(challenge: Challenge) {
        val intent = Intent(this@MainActivity, ChallengeInfoActivity::class.java)
        intent.putExtra("question", challenge)
        startActivity(intent)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
