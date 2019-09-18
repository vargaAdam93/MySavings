package com.example.mysavings;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "account_table")
public class Account {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    private String validThrough;

    private int balance;

    public Account(String name, String validThrough, int balance) {
        this.name = name;
        this.validThrough = validThrough;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getValidThrough() {
        return validThrough;
    }

    public int getBalance() {
        return balance;
    }

    public void setId(int id) {
        this.id = id;
    }
}
