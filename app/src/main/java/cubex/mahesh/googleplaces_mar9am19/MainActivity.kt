package cubex.mahesh.googleplaces_mar9am19

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.indiview.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getLocation.setOnClickListener {
                getCurrentLocation()
        }

        getPlaces.setOnClickListener {
                getNearByPlaces()
        }

    }  //onCreate

    @SuppressLint("MissingPermission")
    fun  getCurrentLocation( )
    {
        var lManager = getSystemService(
            Context.LOCATION_SERVICE) as LocationManager
        lManager.requestLocationUpdates(
            LocationManager.NETWORK_PROVIDER,
            1000L, 1f,
            object : LocationListener {
                override fun onLocationChanged(loc: Location?) {
                    tv_lati.setText(loc?.latitude.toString())
                    tv_longi.setText(loc?.longitude.toString())
                    lManager.removeUpdates(this)
                }
                override fun onProviderEnabled(p0: String?) {
                }
                override fun onProviderDisabled(p0: String?) {
                }
                override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {
                }
            }
        )


    }


    fun getNearByPlaces( )
    {
        var r = Retrofit.Builder().
            baseUrl("https://maps.googleapis.com/").
            addConverterFactory(GsonConverterFactory.create()).
            build()
        var api:PlacesAPI = r.create(PlacesAPI::class.java)
        var   call: Call<Places> = api.getPlaces(
                tv_lati.text.toString()+","+tv_longi.text.toString(),
                sp1.selectedItem.toString())
        call.enqueue(object:Callback<Places>{
            override fun onResponse(call: Call<Places>,
                                    response: Response<Places>) {
            var places =  response.body()
            var places_list = places?.results
            lview.adapter = object:BaseAdapter(){
                override fun getCount(): Int = places_list!!.size
                override fun getItem(p0: Int): Any = 0
                override fun getItemId(p0: Int): Long = 0
                override fun getView(pos: Int,
                                     p1: View?, p2: ViewGroup?): View {
                    var inflater = LayoutInflater.from(
                        this@MainActivity)
                     var v = inflater.inflate(R.layout.indiview,null)

                    v.tv_name.text = places_list?.get(pos)?.name
                    v.tv_address.text = places_list?.get(pos)?.vicinity
                    v.tv_rating.text = places_list?.get(pos)?.rating.toString()


                    return  v
                }
            }

            }
            override fun onFailure(call: Call<Places>, t: Throwable) {

            }
        })
    }

}
