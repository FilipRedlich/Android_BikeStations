package com.example.poznanbike.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_bike_stations")
data class BikeStationDB (
    @ColumnInfo
    val bikes: Int,
    @ColumnInfo
    val freeRacks: Int,
    @ColumnInfo
    val Label: String,
    @ColumnInfo
    val updated: String,
    @ColumnInfo
    val Longitude: Double,
    @ColumnInfo
    val Latitude: Double,
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L
)

//class BikeStationDB {
//}