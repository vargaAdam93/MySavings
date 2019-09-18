package com.example.mysavings;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.AccountHolder> {

    private List<Account> accounts =new ArrayList<>();

    @NonNull
    @Override
    public AccountHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View accountView = LayoutInflater.from(parent.getContext()).inflate(R.layout.account_item,parent,false);
        return new AccountHolder(accountView);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountHolder holder, int position) {
        Account currentAccount = accounts.get(position);
        holder.textViewName.setText(currentAccount.getName());
        holder.textViewBalance.setText(String.valueOf(currentAccount.getBalance()));
        holder.textViewValid.setText(currentAccount.getValidThrough());
    }

    @Override
    public int getItemCount() {
        return accounts.size();
    }

    public void setAccounts(List<Account> accounts)
    {
        this.accounts = accounts;
        notifyDataSetChanged();
    }

    class AccountHolder extends RecyclerView.ViewHolder{
        private TextView textViewId;
        private TextView textViewName;
        private TextView textViewValid;
        private TextView textViewBalance;

        public AccountHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.account_name);
            textViewValid = itemView.findViewById(R.id.account_valid);
            textViewBalance = itemView.findViewById(R.id.account_balance);
        }


    }
}
