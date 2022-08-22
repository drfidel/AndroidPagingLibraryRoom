package com.fidelsoft.adroidpaginglib.data

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.fidelsoft.adroidpaginglib.models.Contact


@Dao
interface ContactDao {
    @Query("SELECT * FROM Contact ORDER BY id ASC LIMIT 1")
    fun getTopContact(): Contact

    @Query("SELECT * FROM Contact")
    fun getAllContacts(): List<Contact>

    @Query("SELECT id FROM Contact ORDER BY id DESC LIMIT 1")
    fun getLastId(): Int

    @Query("SELECT * FROM Contact")
    fun getContacts(): DataSource.Factory<Int, Contact>

    @Insert
    fun insertContact(contact: Contact)

    @Delete
    fun delete(contact: Contact)
}