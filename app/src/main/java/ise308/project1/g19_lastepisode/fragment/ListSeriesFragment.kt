package ise308.project1.g19_lastepisode.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import ise308.project1.g19_lastepisode.MainActivity
import ise308.project1.g19_lastepisode.util.JSONSerializer
//import ise308.project1.g19_lastepisode.util.TvSeries

class ListSeriesFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ):View? {

        val view = inflater.inflate(R.Layout.content_itemseries, container, false)
        //content ıtem serıes ı gostermek ıcın
        val itemseries = view.findViewById<TextView>(R.id.item_series)

        fun jsonOpener(){
            // Creating/Opening G19 JSON File
            val activity = activity as MainActivity
            mSerializer = JSONSerializer("G19.json", activity)

            try {
                seriesList = mSerializer!!.load() // Loading Series Details
            } catch (e: Exception) {
                seriesList = ArrayList()
                Log.e("Error loading notes: ", "", e)
            }
        }

        return view
    }

}

