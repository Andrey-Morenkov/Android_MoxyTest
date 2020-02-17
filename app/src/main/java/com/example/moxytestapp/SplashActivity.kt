package com.example.moxytestapp

import android.Manifest
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nabinbhandari.android.permissions.PermissionHandler
import com.nabinbhandari.android.permissions.Permissions
import com.pawegio.kandroid.startActivity


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val permissions = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        val rationale = "НУ ПАЖАЛАСТА"
        val options: Permissions.Options = Permissions.Options()
            .setRationaleDialogTitle("ИНФО")
            .setSettingsDialogTitle("ВАРН")

        Permissions.check(
            this,
            permissions,
            rationale,
            options,
            object : PermissionHandler()
            {
                override fun onGranted()
                {
                    startActivity<MainActivity>()
                }

                override fun onDenied(
                    context: Context?,
                    deniedPermissions: ArrayList<String?>?
                )
                {
                    finish()
                }
            })
    }
}
