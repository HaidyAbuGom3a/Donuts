package com.example.donuts.domain.entities

import androidx.annotation.DrawableRes

data class DonutEntity(
    val id:Int,
    val name:String? = null,
    val description:String? = null,
    @DrawableRes val image:Int? = null,
    val oldPrice:Int? = null,
    val newPrice:Int? = null,
    val hasOffer: Boolean? = null
)