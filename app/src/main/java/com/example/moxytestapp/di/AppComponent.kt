package com.example.moxytestapp.di

import com.example.moxytestapp.MainActivity
import com.example.moxytestapp.MainPresenter
import dagger.Component
import javax.inject.Singleton

@Component(modules = [TestModule::class])
interface AppComponent
{
    fun inject(presenter: MainPresenter)
}