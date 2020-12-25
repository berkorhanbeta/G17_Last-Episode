package ise308.project1.g19_lastepisode.fragment



import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import ise308.project1.g19_lastepisode.MainActivity
import ise308.project1.g19_lastepisode.R
import ise308.project1.g19_lastepisode.util.TvSeries


class SeriesFragment : Fragment(), AdapterView.OnItemSelectedListener {

    companion object {

        fun newInstance(): SeriesFragment {
            return SeriesFragment()
        }
    }

    lateinit var seriesTitle : EditText
    lateinit var seriesGenre : Spinner
    lateinit var selectedGenre : String
    lateinit var seriesLastSeason : EditText
    lateinit var seriesLastEpisode : EditText
    lateinit var cbFinished : CheckBox
    lateinit var btnAdd : Button


    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.content_add, container, false)

        val activity = activity as MainActivity
        activity.setSupportActionBar(view.findViewById(R.id.toolbar))
       activity.supportActionBar?.setTitle(getString(R.string.addNew))

        btnAdd = view.findViewById(R.id.seriesAdd)
        seriesTitle = view.findViewById(R.id.seriesName)
        seriesLastSeason = view.findViewById(R.id.seriesSeason)
        seriesLastEpisode = view.findViewById(R.id.seriesEpisode)
        cbFinished = view.findViewById(R.id.isFinished)
        seriesGenre = view.findViewById(R.id.spinnerGenre)

        val adapter = ArrayAdapter.createFromResource(
                activity,
                R.array.genres,
                android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        seriesGenre.adapter = adapter
        seriesGenre.onItemSelectedListener = this


        btnAdd.setOnClickListener {
            addSeries()
            activity.onBackPressed()
        }
        return view
    }



    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val text: String = parent?.getItemAtPosition(position).toString()
         selectedGenre = text
    }

    fun addSeries(){

        // Create a new note
        val newSeries = TvSeries()


        newSeries.sName = seriesTitle.text.toString()
        newSeries.sLastSeason = seriesLastSeason.text.toString()
        newSeries.sLastEpisode = seriesLastEpisode.text.toString()
        newSeries.sGenre = selectedGenre
        // Which one is checked?
        newSeries.isFinished= cbFinished.isChecked

        // Calling a function from the Main Activity
        (activity as MainActivity).createNewSeries(newSeries)
    }

}