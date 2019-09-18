package com.example.mysavings;

import android.app.Application;
import android.transition.Transition;

import androidx.annotation.NonNull;
import androidx.annotation.TransitionRes;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class TransactionViewModel extends AndroidViewModel {

    private MysavingRepository repository;
    private LiveData<List<Transaction>> last10Transaction;
    private LiveData<List<Transaction>> allTransaction;

    public TransactionViewModel(@NonNull Application application)
    {
        super(application);
        repository = new MysavingRepository(application);
        allTransaction = repository.getAllTransaction();
        /*
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        last10Transaction =repository.getTransactionForDay(dateFormat.format(cal.getTime()));
        */
    }

    public void insert(Transaction transaction)
    {
        repository.insert(transaction);
    }

    public void update(Transaction transaction)
    {
        repository.update(transaction);
    }

    public void delete(Transaction transaction)
    {
        repository.delete(transaction);
    }

    public LiveData<List<Transaction>> getLast10Transaction()
    {
        return repository.getLatest10Transaction();
    }

    public LiveData<List<Transaction>> getTransactionForDay(String date)
    {
        return repository.getTransactionForDay(date);
    }

    public LiveData<List<Transaction>> getTransactionForAccount(int account_id)
    {
        return repository.getTransactionForAccount(account_id);
    }
}
