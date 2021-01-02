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
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ise308.project1.g19_lastepisode.MainActivity
import ise308.project1.g19_lastepisode.R
import ise308.project1.g19_lastepisode.util.JSONSerializer
import ise308.project1.g19_lastepisode.util.TvSeries
import ise308.project1.g19_lastepisode.util.TvSeriesAdapter

//import ise308.project1.g19_lastepisode.util.TvSeries

class ListSeriesFragment: Fragment() {

    private var mSerializer: JSONSerializer? = null
    private var seriesList: ArrayList<TvSeries>? = null
    private var recyclerView: RecyclerView? = null
    private var adapter: TvSeriesAdapter? = null

        override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ):View? {

        val view = inflater.inflate(R.layout.content_itemseries, container, false)
        //content ıtem serıes ı gostermek ıcın
            val activity = activity as MainActivity


            // Creating/Opening G19 JSON File
            mSerializer = JSONSerializer("G19.json", activity)

            try {
                seriesList = mSerializer!!.load() // Loading Series Details
            } catch (e: Exception) {
                seriesList = ArrayList()
                Log.e("Error loading notes: ", "", e)
            }
            recyclerView = view.findViewById(R.id.item_series) as RecyclerView
            // List the unfinished TV Series first.
            adapter = TvSeriesAdapter(activity, this.seriesList!!.sortedByDescending { !it.isFinished })


            val layoutManager = LinearLayoutManager(activity)

            recyclerView!!.layoutManager = layoutManager
            recyclerView!!.itemAnimator = DefaultItemAnimator()

            recyclerView!!.adapter = adapter
            adapter!!.notifyDataSetChanged()

            return view
        }

            override fun onResume() {
                super.onResume()
                adapter!!.notifyDataSetChanged()

                if (recyclerView!!.itemDecorationCount > 0)
                    recyclerView!!.removeItemDecorationAt(0)


            }
        }