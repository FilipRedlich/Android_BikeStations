package com.example.poznanbike.database

import androidx.room.*

@Dao
interface BikeStationDAO {
    // Insert new bike station to database
    @Insert
    fun insert(bikeStation: BikeStationDB)
    // Update an existing bike station
    @Update
    fun update(bikeStation: BikeStationDB)
// Get all bike stations with number of bikes greater than num
    @Query("SELECT * FROM saved_bike_stations WHERE bikes > :num")
    fun getStationsWithBikes(num: Int): List<BikeStationDB>
// Get all bike stations with number of free racks greater than num
    @Query("SELECT * FROM saved_bike_stations WHERE freeRacks > :num")
    fun getStationsWithFreeRacks(num: Int): List<BikeStationDB>
// Get all saved bike stations
    @Query("SELECT * FROM saved_bike_stations")
    fun getAll() : List<BikeStationDB>
// Get a bike station by Label
    @Query("SELECT * from saved_bike_stations WHERE Label = :label")
    fun getByLabel(label: String): List<BikeStationDB>
    // Clear the database
    @Query("DELETE from saved_bike_stations")
    fun clear()
    // Delete an existing bike station
    @Delete
    fun deLete(bikeStation: BikeStationDB)
}