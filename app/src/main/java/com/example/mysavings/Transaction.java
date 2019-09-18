package com.example.mysavings;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.NO_ACTION;


@Entity(tableName = "transaction_table",
        foreignKeys = @ForeignKey(entity = Account.class,
                                  parentColumns = "id",
                                  childColumns = "accountId",
                                  onDelete = NO_ACTION))
public class Transaction {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int value;

    private int accountId;

    private String date;

    private String place;

    private String description;

    private int balanceAfterTransaction;

    private int transactionType;

    public Transaction(int value, int accountId, String date, String place, String description, int balanceAfterTransaction, int transactionType) {
        this.value = value;
        this.accountId = accountId;
        this.date = date;
        this.place = place;
        this.description = description;
        this.balanceAfterTransaction = balanceAfterTransaction;
        this.transactionType = transactionType;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getValue() {
        return value;
    }

    public int getAccountId() {
        return accountId;
    }

    public String getDate() {
        return date;
    }

    public String getPlace() {
        return place;
    }

    public String getDescription() {
        return description;
    }

    public int getBalanceAfterTransaction() {
        return balanceAfterTransaction;
    }

    public int getTransactionType() {
        return transactionType;
    }
}
