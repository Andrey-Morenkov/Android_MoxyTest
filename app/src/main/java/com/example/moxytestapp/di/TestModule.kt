package com.example.moxytestapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Singleton

@Module
class TestModule(private val ctx: Context)
{
    @Provides
    fun provideRealm(): Realm
    {
        Realm.init(ctx)
        val config = RealmConfiguration
            .Builder()
            .name("MyDb")
            .build()
        Realm.setDefaultConfiguration(config)
        return Realm.getDefaultInstance()
    }
}