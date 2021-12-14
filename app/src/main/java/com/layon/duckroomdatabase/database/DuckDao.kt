package com.layon.duckroomdatabase.database


import androidx.room.*
import com.layon.duckroomdatabase.model.Duck

@Dao
interface DuckDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(duck: Duck)

    @Delete
    suspend fun delete(duck: Duck)

    @Query("DELETE FROM duck_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM duck_table")
    suspend fun read(): List<Duck>
}