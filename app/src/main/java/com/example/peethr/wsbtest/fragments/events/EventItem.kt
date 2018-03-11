package com.example.peethr.wsbtest.fragments.events

/**
 * Created by thomas on 10.03.18.
 */
class EventItem {
    val id : Long
    val title : String
    val city : String
    val date : String
    val time : String
    val place : String
    val imageUrl : String

    constructor(id: Long, title: String, city: String, date: String, time: String, place: String, imageUrl: String) {
        this.id = id
        this.title = title
        this.city = city
        this.date = date
        this.time = time
        this.place = place
        this.imageUrl = imageUrl

    }
}
