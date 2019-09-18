package com.example.mysavings;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Account.class,Transaction.class}, version = 1)
public abstract class MySavingDatabase extends RoomDatabase {

    private static MySavingDatabase instance;

    public abstract AccountDao accountDao();
    public abstract TransactionDao transactionDao();

    public static synchronized MySavingDatabase getInstance(Context context)
    {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    MySavingDatabase.class, "mysaving_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulatedbAsyncTask(instance).execute();
        }
    };

    private static class PopulatedbAsyncTask extends AsyncTask<Void,Void,Void>{
        private AccountDao accountDao;
        private TransactionDao transactionDao;

        private PopulatedbAsyncTask(MySavingDatabase db)
        {
            accountDao = db.accountDao();
            transactionDao = db.transactionDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Account account1 = new Account("MKB Szepkartya","08/20",5220);
            accountDao.insert(account1);
            accountDao.insert(new Account("OTP bankkártya","08/23",10000000));

            transactionDao.insert(new Transaction(2500, account1.getId(),"2019-09-16 15:30",
                    "DoubleDelight","ebéd",account1.getBalance()-2500,
                    0));
            return null;
        }
    }

}
