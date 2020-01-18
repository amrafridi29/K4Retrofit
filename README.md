# Current Release
![Release](https://jitpack.io/v/amrafridi29/K4Retrofit.svg)

(https://jitpack.io/#amrafridi29/K4Retrofit/Tag)

# KRetrofit

A kotlin library to simplify retrofit calls.



# Gradle

```
dependencies {
    ...
    implementation 'com.github.amrafridi29:K4Retrofit:$k4retrofit_version'
}

allprojects {
    repositories {
       ...
      maven { url 'https://jitpack.io' }
     }
   }
```

# How to use 

# Init KRetrofitApi

```
 class App : Application(){
   companion object{
       private const val API_BASE_URL: String = ""
   }

    override fun onCreate() {
        super.onCreate()
        KRetrofitApi.initialize(
            API_BASE_URL, OkHttpClient.Builder().
            addInterceptor(
                ConnectivityInterceptor(
                    this
                )
            )
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build())
    }
}
```

# Register Api Interface

```
interface ApiService{
    @GET("index")
    fun getRepos(): Call<String>
}


private val apiService by kRetrofitCreate<ApiService>()

OR

val apiService = KRetrofitApi.create<ApiService>()

```

# Use Fragment/Activity

```
 Async Request
 
 apiService.getRepos().process {kResult->}
 
 Async Request with LiveData Observer
 
 apiService.getRepos().enqueue().observe(this , Observer {kResult->})
 
 Sync Request
 
 apiService.getRepos().run{kResult->}
 
```



License
-------

    Copyright 2019 Muhammad Amir

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
