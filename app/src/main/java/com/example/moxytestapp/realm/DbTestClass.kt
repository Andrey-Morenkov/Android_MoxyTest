package com.example.moxytestapp.realm

import io.realm.RealmObject

open class DbTestClass() : RealmObject()
{
    var saved: Int = 0

    constructor(saved: Int) : this()
    {
        this.saved = saved
    }
}