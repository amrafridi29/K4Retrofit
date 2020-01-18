package com.kot.k4retrofit.simplified

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.Observer
import com.kot.k4retrofit.k4retro.delegates.kRetrofitCreate
import com.kot.k4retrofit.k4retro.enums.FailureType
import com.kot.k4retrofit.k4retro.ext.enqueue
import com.kot.k4retrofit.k4retro.imp.KResult
import com.kot.retro.simplified.R
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.http.GET


class MainActivity : AppCompatActivity() {
    private val TAG: String = MainActivity::class.java.simpleName
    private val apiService by kRetrofitCreate<ApiService>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { _ ->
//            gitApiService.getRepos().process {
//
//            }

            apiService.getRepos().enqueue().observe(this , Observer {
                when(it){
                    is KResult.Loading->{
                        Log.i(TAG , "LOADING")
                    }
                    is KResult.Success->{
                        it.data?.isDigitsOnly()
                        Log.i(TAG , "SUCCESS")
                    }
                    is KResult.Failure-> {
                        Log.i(TAG , "FAILURE")
                        when(it.failureType){
                            FailureType.CONVERSION->{}
                            FailureType.NETWORK->{}
                        }
                    }
                    is KResult.Error->{
                        Log.i(TAG , "ERROR $it")
                    }
                }
            })
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}

interface ApiService{
    @GET("index")
    fun getRepos(): Call<String>
}


