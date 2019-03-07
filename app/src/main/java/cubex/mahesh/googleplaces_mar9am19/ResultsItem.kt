package cubex.mahesh.googleplaces_mar9am19

import com.google.gson.annotations.SerializedName

data class ResultsItem(@SerializedName("rating")
                       val rating: Double = 0.0,
                       @SerializedName("name")
                       val name: String = "",
                       @SerializedName("vicinity")
                       val vicinity: String = "")