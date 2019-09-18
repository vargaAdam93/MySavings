package com.example.mysavings;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class AccountViewModel extends AndroidViewModel {

    private MysavingRepository repository;
    private LiveData<List<Account>> allAccount;

    public AccountViewModel(@NonNull Application application) {
        super(application);
        repository = new MysavingRepository(application);
        allAccount = repository.getAllAccount();
    }

    public void insert(Account account)
    {
        repository.insert(account);
    }

    public void update(Account account)
    {
        repository.update(account);
    }

    public void delete(Account account)
    {
        repository.delete(account);
    }

    public LiveData<List<Account>> getAllAccount()
    {
        return allAccount;
    }
}
