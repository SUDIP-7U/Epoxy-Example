package com.example.myapplication.uicontroller

import com.airbnb.epoxy.AsyncEpoxyController
import com.example.myapplication.uimodel.HeaderItemModel_

class HomeController : AsyncEpoxyController(){
    private lateinit var title: String

    override fun buildModels() {
        //Before work with Epoxy Run This Project
        //Then Everything work Properly--->
        //HeaderItemModel_
       HeaderItemModel_(title)
           .id("${title}_id")
           .addTo(this)

        HeaderItemModel_(title)
            .id("${title}_id")
            .addTo(this)
    }

    fun setTitle(title: String) {
        this.title = title
        requestModelBuild()
    }
}