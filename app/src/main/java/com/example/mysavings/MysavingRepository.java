package com.example.mysavings;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.List;

public class MysavingRepository {

    private AccountDao accountDao;
    private TransactionDao transactionDao;

    LiveData<List<Account>> allAccount;
    LiveData<List<Transaction>> allTransaction;

    public MysavingRepository(Application application)
    {
        MySavingDatabase database = MySavingDatabase.getInstance(application);

        accountDao = database.accountDao();
        allAccount = accountDao.getAllAccount();

        transactionDao = database.transactionDao();
        allTransaction = transactionDao.getAllTransaction();
    }

    public void insert(Account account)
    {
        new InsertAccountAsyncTask(accountDao).execute(account);
    }

    public void insert(Transaction transaction)
    {
        new InsertTransactionAsyncTask(transactionDao).execute(transaction);
    }

    public void update(Account account)
    {
        new UpdateAccountAsyncTask(accountDao).execute(account);
    }

    public void update(Transaction transaction)
    {
        new UpdateTransactionAsyncTask(transactionDao).execute(transaction);
    }

    public void delete(Account account)
    {
        new DeleteAccountAsyncTask(accountDao).execute(account);
    }

    public void delete(Transaction transaction)
    {
        new DeleteTransactionAsyncTask(transactionDao).execute(transaction);
    }

    public LiveData<List<Account>> getAllAccount()
    {
         return allAccount;
    }

    LiveData<List<Transaction>> getTransactionForAccount(int queryAccountId)
    {
        //TODO
        return allTransaction;
    }

    LiveData<List<Transaction>> getTransactionForDay(String queryDate)
    {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        allTransaction = transactionDao.getTransactionForDay(dateFormat.format(cal.getTime()));
        return allTransaction;
    }

    LiveData<List<Transaction>> getLatest10Transaction()
    {
        allTransaction = transactionDao.getLatest10Transaction();
        return allTransaction;
    }

    LiveData<List<Transaction>> getAllTransaction()
    {
        return allTransaction;
    }
/*--------------------ACCOUNT async function----------------------------------------*/
    private static class InsertAccountAsyncTask extends AsyncTask<Account,Void,Void>
    {
        private AccountDao accountDao;

        private InsertAccountAsyncTask(AccountDao accountDao)
        {
            this.accountDao = accountDao;
        }

        @Override
        protected Void doInBackground(Account... accounts) {
            accountDao.insert(accounts[0]);
            return null;
        }
    }

    private static class UpdateAccountAsyncTask extends AsyncTask<Account,Void,Void>
    {
        private AccountDao accountDao;

        private UpdateAccountAsyncTask(AccountDao accountDao)
        {
            this.accountDao = accountDao;
        }

        @Override
        protected Void doInBackground(Account... accounts) {
            accountDao.update(accounts[0]);
            return null;
        }
    }

    private static class DeleteAccountAsyncTask extends AsyncTask<Account,Void,Void>
    {
        private AccountDao accountDao;

        private DeleteAccountAsyncTask(AccountDao accountDao)
        {
            this.accountDao = accountDao;
        }

        @Override
        protected Void doInBackground(Account... accounts) {
            accountDao.delete(accounts[0]);
            return null;
        }
    }
    /*--------------------TRANSACTION async function----------------------------------------*/
    private static class InsertTransactionAsyncTask extends AsyncTask<Transaction,Void,Void>
    {
        private TransactionDao transactionDao;

        private InsertTransactionAsyncTask(TransactionDao transactionDao)
        {
            this.transactionDao = transactionDao;
        }

        @Override
        protected Void doInBackground(Transaction... transactions) {
            transactionDao.insert(transactions[0]);
            return null;
        }
    }

    private static class UpdateTransactionAsyncTask extends AsyncTask<Transaction,Void,Void>
    {
        private TransactionDao transactionDao;

        private UpdateTransactionAsyncTask(TransactionDao transactionDao)
        {
            this.transactionDao = transactionDao;
        }

        @Override
        protected Void doInBackground(Transaction... transactions) {
            transactionDao.update(transactions[0]);
            return null;
        }
    }

    private static class DeleteTransactionAsyncTask extends AsyncTask<Transaction,Void,Void>
    {
        private TransactionDao transactionDao;

        private DeleteTransactionAsyncTask(TransactionDao transactionDao)
        {
            this.transactionDao = transactionDao;
        }

        @Override
        protected Void doInBackground(Transaction... transactions) {
            transactionDao.update(transactions[0]);
            return null;
        }
    }
}
