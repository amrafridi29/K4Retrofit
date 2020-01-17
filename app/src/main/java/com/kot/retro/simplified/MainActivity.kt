package com.kot.retro.simplified

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.kot.retro.retrofitsimplified.delegates.kRetrofitCreate
import com.kot.retro.retrofitsimplified.ext.enqueue
import com.kot.retro.retrofitsimplified.imp.KResult
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.http.GET


class MainActivity : AppCompatActivity() {
    val TAG = MainActivity::class.java.simpleName
    val gitApiService by kRetrofitCreate<GithubService>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

       /* KRetrofitApi.initApi(API_BASE_URL , OkHttpClient.Builder().build())

        val gitApiService =  KRetrofitApi.create<GithubService>()
*/

        fab.setOnClickListener { _ ->
//            gitApiService.getRepos().process {
//
//            }

            gitApiService.getRepos().enqueue().observe(this , Observer {
                when(it){
                    is KResult.Loading->{
                        Log.i(TAG , "LOADING")
                    }
                    is KResult.Success->{
                        Log.i(TAG , "SUCCESS")
                    }
                    is KResult.Failure-> {
                        Log.i(TAG , "FAILURE")
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

interface GithubService{
    @GET("index")
    fun getRepos(): Call<String>
}


