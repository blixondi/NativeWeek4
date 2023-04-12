package com.shem.adv160420033week4.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shem.adv160420033week4.R
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val observable = Observable.just("Hello","Welcome to","Ubaya")

        val observer = object : Observer<String> {
            override fun onSubscribe(d: Disposable) {
                TODO("Not yet implemented")
            }

            override fun onError(e: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onComplete() {
                TODO("Not yet implemented")
            }

            override fun onNext(t: String) {
                TODO("Not yet implemented")
            }

        }
    }
}