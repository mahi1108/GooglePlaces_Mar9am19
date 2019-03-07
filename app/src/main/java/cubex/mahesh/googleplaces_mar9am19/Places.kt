package cubex.mahesh.googleplaces_mar9am19

import com.google.gson.annotations.SerializedName

data class Places(@SerializedName("results")
                  val results: List<ResultsItem>?)