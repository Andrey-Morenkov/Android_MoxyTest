package com.example.moxytestapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.pawegio.kandroid.hide
import com.pawegio.kandroid.i
import com.pawegio.kandroid.textWatcher
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter


class MainActivity : MvpAppCompatActivity(), MainView
{
    @InjectPresenter
    lateinit var mainPresenter: MainPresenter

    private lateinit var timer: TextView
    private lateinit var inputField: EditText
    private lateinit var testButton: Button
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        i("==== Activity Created ====")

        timer = findViewById(R.id.txtTimer)
        inputField = findViewById(R.id.txtInput)
        testButton = findViewById(R.id.btnTest)
        progressBar = findViewById(R.id.progressBarr)

        progressBar.hide(true)

        //inputField.textWatcher{
        //    //afterTextChanged { mainPresenter.onInputTextChanged(it.toString()) }
        //}

        testButton.setOnClickListener { mainPresenter.onTestButtonClicked() }
    }

    override fun startLoadingState()
    {
        i("==== StartLoadingState ====")
        timer.visibility = View.INVISIBLE
        inputField.visibility = View.INVISIBLE

        progressBar.visibility = View.VISIBLE
        testButton.isEnabled = false
    }

    override fun finishLoadingState()
    {
        i("==== FinishLoadingState ====")
        timer.visibility = View.VISIBLE
        inputField.visibility = View.VISIBLE

        progressBar.visibility = View.INVISIBLE
        testButton.isEnabled = true
    }

    override fun updateTimer(sec: Int)
    {
        i("==== UpdateTimer $sec ====")
        timer.text = sec.toString()
    }

    override fun setText(txt: String)
    {
        i("==== SetText $txt ====")
        inputField.setText(txt, TextView.BufferType.EDITABLE)
    }
}
