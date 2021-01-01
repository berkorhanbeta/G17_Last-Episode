package ise308.project1.g19_lastepisode

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity
import ise308.project1.g19_lastepisode.util.JSONSerializer
import ise308.project1.g19_lastepisode.util.TvSeries

class SettingsActivity : AppCompatActivity() {


    private var mSerializer: JSONSerializer? = null
    private var seriesList: ArrayList<TvSeries>? = null
    lateinit var eraseBTN : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setTitle(getString(R.string.action_settings))

        jsonOpener()
        defineUI()

        eraseBTN.setOnClickListener {

            deleteContent()

        }


    }

    override fun onSupportNavigateUp(): Boolean {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
        return true
    }


    fun defineUI(){
        eraseBTN = findViewById(R.id.eraseBtn)
    }

    fun deleteContent(){

        seriesList!!.clear()
        try {
            // Save the note into JSON File
            mSerializer!!.save(this.seriesList!!)

        } catch (e: Exception) {
            Log.e("Error Saving Notes", "", e)
        }
    }


    fun jsonOpener(){
        // Creating/Opening G19 JSON File
        mSerializer = JSONSerializer("G19.json", applicationContext)

        try {
            seriesList = mSerializer!!.load() // Loading Tv Series Details
        } catch (e: Exception) {
            seriesList = ArrayList()
            Log.e("Error loading notes: ", "", e)
        }
    }
}
