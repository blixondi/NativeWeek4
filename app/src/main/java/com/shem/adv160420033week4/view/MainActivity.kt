package com.shem.adv160420033week4.view

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.shem.adv160420033week4.R
import com.shem.adv160420033week4.util.createNotificationChannel
import com.shem.adv160420033week4.util.showNotification
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    init {
        instance = this
    }

    companion object {
        var instance:MainActivity ?= null

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createNotificationChannel(this,
            NotificationManagerCompat.IMPORTANCE_DEFAULT,
            false,
            getString(R.string.app_name),
            "App Student Data")

        val fab = findViewById<FloatingActionButton>(R.id.fabNotif)
        fab.setOnClickListener{
            val observable = Observable.timer(5, TimeUnit.SECONDS).apply {
                subscribeOn(Schedulers.io())
                observeOn(AndroidSchedulers.mainThread())
                subscribe(){
                    Log.d("observermessage", "five seconds")
                    showNotification("Dummy", "A new notification created", R.drawable.baseline_error_24)
                }
            }
        }

        val observable = Observable.just("Hello","Welcome to","Ubaya")

        val observer = object : Observer<String> {
            override fun onSubscribe(d: Disposable) {
                Log.d("observermessage","Begin subscribe")
            }

            override fun onError(e: Throwable) {
                Log.e("observermessage","Error: {${e.message.toString()}}")
            }

            override fun onComplete() {
                Log.d("observermessage","Subscribe Complete")
            }

            override fun onNext(t: String) {
                Log.d("observermessage","Data: $t")
            }
        }
        // alternative way to use observable
//        observable.apply {
//            subscribeOn(Schedulers.io())
//            observeOn(AndroidSchedulers.mainThread())
//            subscribe(observer)
//        }

        observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)
    }
}