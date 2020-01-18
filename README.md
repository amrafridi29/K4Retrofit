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

```
 Application Class
 
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

 
 
 
 In Activity or Fragment Observing for LiveData async request
 
 apiService.getRepos().enqueue().observe(this , Observer {
                when(it){
                    is KResult.Loading->{
                        Log.i(TAG , "LOADING")
                    }
                    is KResult.Success->{
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
            
            
    Or Simple Result async request
    
    apiService.getRepos().process{
    
        when(it){
          is KResult.Loading->{
               Log.i(TAG , "LOADING")
          }
          is KResult.Success->{
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
    }
    
    sync request
    
    apiService.getRepos().run{
    
        when(it){
          is KResult.Success->{
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
    }
    
 
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
