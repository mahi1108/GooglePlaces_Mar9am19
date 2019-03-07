package cubex.mahesh.googleplaces_mar9am19

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PlacesAPI {
    @GET("maps/api/place/nearbysearch/json?radius=1500&key=AIzaSyAGfTs9-jYFFluY3tKDORYZlsXmkiJ5S34")
    fun getPlaces(@Query("location") l:String,
                 @Query("type") t:String):Call<Places>
}