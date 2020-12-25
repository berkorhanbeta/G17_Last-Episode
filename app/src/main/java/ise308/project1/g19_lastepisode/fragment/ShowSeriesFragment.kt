package ise308.project1.g19_lastepisode.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import ise308.project1.g19_lastepisode.MainActivity
import ise308.project1.g19_lastepisode.R
import ise308.project1.g19_lastepisode.util.TvSeries

class ShowSeriesFragment : Fragment() {

    lateinit var btnDelete : Button
    lateinit var btnEdit : Button
    lateinit var txtTitle : TextView
    lateinit var txtLastEpisode : TextView
    var season : String? = "5"
    var episode : String? = "5"

    private var mSerializer : JSONSerializer? = null
    private var seriesList : ArrayList<TvSeries>? = null
    private var series : TvSeries? = null

    override fun onCreateView(inflater : LayoutInflater, container : ViewGroup?, savedInstanceState : Bundle?): View? {



         // we match fragment and layout
        val view: View = inflater.inflate(R.layout.content_detail, container, false)

        // taking positionID value from main activity
        val positionID = arguments?.getInt("positionID")

        txtTitle = view.findViewById(R.id.tvSeriesName)
        txtLastEpisode = view.findViewById(R.id.tvSeriesLastEpisode)
        btnEdit = view.findViewById(R.id.editSeries)
        btnDelete = view.findViewById(R.id.deleteSeries)

        btnEdit.setOnClickListener {
            (activity as MainActivity).editSeries(positionID!!)
        }


        btnDelete.setOnClickListener {

            (activity as MainActivity).deleteSeries(positionID!!)
            (activity as MainActivity).onBackPressed()

        }

        return view
    }


    fun selectedSeries(tvseries : TvSeries){
        series = tvseries
    }

    fun setDetails(){

        season = series!!.sLastSeason
        episode = series!!.sLastEpisode
        txtTitle.text = series!!.sName
        txtLastEpisode.text = "The last episode you watched in this series is Season $season, $episode"

    }

    // we open JSON file to see data inside of it.
    fun jsonOpener(){
        // Openning g19 JSON File
        val activity = activity as MainActivity
        mSerializer = JSONSerializer("G19.json", activity)

        try {
            seriesList = mSerializer!!.load() // Loading Series Details
        } catch (e: Exception) {
            seriesList = ArrayList()
            Log.e("Error : ", "TV Series Details Cannot Be Loaded", e)
        }
    }


}