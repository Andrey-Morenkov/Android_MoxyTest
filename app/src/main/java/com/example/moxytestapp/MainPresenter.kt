package com.example.moxytestapp

import com.example.moxytestapp.realm.DbTestClass
import com.pawegio.kandroid.i
import com.pawegio.kandroid.toast
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import kotlinx.coroutines.*
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class MainPresenter: MvpPresenter<MainView>(), MainInteractor
{
    private var seconds: Int = 0
    private var ticker: Job

    @Inject
    lateinit var db: Realm

    init
    {
        MyApp.getDComponent().inject(this)

        i("<<<< Try to find saved seconds ... >>>>")
        db.where<DbTestClass>()
            .findFirst()
            ?.let {
                i("<<<< Found: ${it.saved} ... >>>>")
                seconds = it!!.saved
                viewState.updateTimer(seconds)
            }

        ticker = GlobalScope.launch(Dispatchers.Main)
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

    override fun onDestroy()
    {
        ticker.cancel()

        super.onDestroy()

        i("<<<< Save seconds ($seconds) to db... >>>>")
        db.executeTransaction {
            it.deleteAll()
            val tstVal = it.createObject<DbTestClass>()
            tstVal.saved = seconds
        }

        i("<<<< DB closed >>>>")

        db.close()
    }
}