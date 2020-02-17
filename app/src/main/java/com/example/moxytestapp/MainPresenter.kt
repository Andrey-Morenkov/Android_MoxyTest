package com.example.moxytestapp

import com.pawegio.kandroid.i
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class MainPresenter: MvpPresenter<MainView>(), MainInteractor
{
    private var seconds: Int = 0
    init
    {
        GlobalScope.launch(Dispatchers.Main)
        {
            while(true)
            {
                delay(5000L)
                i("<<<< TICK >>>>")
                seconds++
                viewState.updateTimer(seconds)
            }
        }
    }

    override fun onTestButtonClicked()
    {
        i("<<<< onTestButtonClicked >>>>")
        viewState.startLoadingState()
        GlobalScope.launch(Dispatchers.Main)
        {
            repeat(10)
            {
                delay(300L)
            }
            viewState.finishLoadingState()
            viewState.updateTimer(seconds)
        }
    }

    override fun onInputTextChanged(txt: String)
    {
        i("<<<< onInputTextChanged >>>>")
    }
}