package ise308.project1.g19_lastepisode.util

import android.content.Context
import ise308.project1.g19_lastepisode.fragment.SeriesFragment
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException
import java.io.OutputStreamWriter
import java.io.Writer
import org.json.JSONTokener
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.util.ArrayList

//database baglantısı

class JSONSerializer (
    private val filename: String,
    private val context: Context) {

    @Throws(IOException::class, JSONException::class)
    fun save(series: List<TVSeries>){
        val jArray = JSONArray()

    for (n in series)
        jArray.put(n.convertToJSON())

    var writer: Writer?= null
        try {
            val out = context.openFileOutput(filename, Context.MODE_PRIVATE)

            writer=OutputStreamWriter(out)
            writer.write(jArray.toString())

        }finally {
            if (writer != null){
                writer.close()
            }
        }
    }
    @Throws(IOException::class, JSONException::class)
    fun load(): ArrayList<TvSeries>{
        val TvSeries = ArrayList<TvSeries>()
        var reader: BufferedReader? = null
        try {
            val `in` = context.openFileInput(filename)
            reader = BufferedReader(InputStreamReader(`in`))
            val jsonString = StringBuilder()
            for (line in reader.readLine()){
                jsonString.append(line)

        }
             val jArray = JSONTokener(jsonString.toString()).
             nextValue() as JSONArray

             for (i in 0 until jArray.length()){
                TvSeries.add(Series(jArray.getJSONObject(i)))
             }
    }catch (e: FileNotFoundException){

        }finally {
            reader!!.close()
        }
        return TvSeries
    }

)


