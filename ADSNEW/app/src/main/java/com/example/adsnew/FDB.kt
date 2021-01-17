package com.example.adsnew

class FDB {

    var adres: String? = null
    var barcode: String? = null
    var stock: String? = null
    var pk: String? = null
    var name: String? = null
    var lm: String? = null
    var hours: String? = null
    var date: String? = null

    constructor(){

    }

    constructor(
        adres: String?,
        barcode: String?,
        stock: String?,
        pk: String?,
        name: String?,
        lm: String?,
        hours: String?,
        date: String?
    ) {
        this.adres = adres
        this.barcode = barcode
        this.stock = stock
        this.pk = pk
        this.name = name
        this.lm = lm
        this.hours = hours
        this.date = date
    }

}