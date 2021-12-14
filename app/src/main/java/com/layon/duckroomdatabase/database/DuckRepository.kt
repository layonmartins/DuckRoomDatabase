package com.layon.duckroomdatabase.database

import com.layon.duckroomdatabase.model.Duck

class DuckRepository(private val duckDao: DuckDao) {

    suspend fun saveDuck(duck: Duck) {
        duckDao.save(duck)
    }

    suspend fun removeDuck() {
        duckDao.deleteAll()
    }

    suspend fun read() : List<Duck>{
        return duckDao.read()
    }

}