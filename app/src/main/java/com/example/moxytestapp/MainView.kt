package com.example.moxytestapp

import moxy.MvpView
import moxy.viewstate.strategy.*

interface MainView : MvpView
{
    @StateStrategyType(SingleStateStrategy::class)
    fun startLoadingState()

    @StateStrategyType(SingleStateStrategy::class)
    fun finishLoadingState()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun updateTimer(sec: Int)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setText(txt: String)
}