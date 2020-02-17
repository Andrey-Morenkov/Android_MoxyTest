package com.example.moxytestapp

import android.app.Application
import com.example.moxytestapp.di.AppComponent
import com.example.moxytestapp.di.DaggerAppComponent
import com.example.moxytestapp.di.TestModule
import io.realm.Realm
import io.realm.RealmConfiguration

class MyApp : Application()
{
    companion object
    {
        private lateinit var daggerComponent: AppComponent

        fun getDComponent(): AppComponent = daggerComponent
    }

    override fun onCreate()
    {
        super.onCreate()

        daggerComponent = DaggerAppComponent
            .builder()
            .testModule(TestModule(this))
            .build()
    }
}