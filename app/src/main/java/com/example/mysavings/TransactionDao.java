package com.example.mysavings;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TransactionDao {

    @Insert
    void insert(Transaction transaction);

    @Update
    void update(Transaction transaction);

    @Delete
    void delete(Transaction transaction);

    @Query("SELECT * FROM transaction_table WHERE accountId == :queryAccountId ORDER BY date")
    LiveData<List<Transaction>> getTransactionForAccount(int queryAccountId);

    @Query("SELECT * FROM transaction_table WHERE date == :queryDate")
    LiveData<List<Transaction>> getTransactionForDay(String queryDate);

    @Query("SELECT * FROM transaction_table ORDER BY date LIMIT 10")
    LiveData<List<Transaction>> getLatest10Transaction();

    @Query("SELECT * FROM transaction_table ORDER BY date")
    LiveData<List<Transaction>> getAllTransaction();


}
