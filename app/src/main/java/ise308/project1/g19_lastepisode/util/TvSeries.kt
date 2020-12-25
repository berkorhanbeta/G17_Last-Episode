package ise308.project1.g19_lastepisode.util

import org.json.JSONException
import org.json.JSONObject

class TvSeries {

    var sName: String? = null
    var sGenre: String? = null
    var sLastSeason: String? = null
    var sLastEpisode: String? = null
    var isFinished: Boolean = false
    var sGenreId : String? = null

    // Default empty Constructor
    constructor() {
    }


    // JSON file variable names
    private val JSON_SERIESNAME = "sName"
    private val JSON_SERIESGENRE = "sGenre"
    private val JSON_LASTSEASON = "sLastSeason"
    private val JSON_LASTEPISODE = "sLastEpisode"
    private val JSON_FINISHED = "finished"
    private val JSON_SERIESGENREID = "sGenreID"



    @Throws(JSONException::class)
    constructor(json: JSONObject) {

        // Getting Values inside of JSON file
        sName = json.getString(JSON_SERIESNAME)
        sGenre = json.getString(JSON_SERIESGENRE)
        sLastSeason = json.getString(JSON_LASTSEASON)
        sLastEpisode = json.getString(JSON_LASTEPISODE)
        isFinished = json.getBoolean(JSON_FINISHED)
        sGenreId = json.getString(JSON_SERIESGENREID)
    }

    @Throws(JSONException::class)
    fun convertToJSON(): JSONObject {

        val json = JSONObject()
        // Putting Values to inside of JSON file
        json.put(JSON_SERIESNAME, sName)
        json.put(JSON_SERIESGENRE, sGenre)
        json.put(JSON_LASTSEASON, sLastSeason)
        json.put(JSON_LASTEPISODE, sLastEpisode)
        json.put(JSON_FINISHED, isFinished)
        json.put(JSON_SERIESGENREID, sGenreId)

        return json
    }

}