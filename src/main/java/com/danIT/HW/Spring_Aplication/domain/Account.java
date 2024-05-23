package com.danIT.HW.Spring_Aplication.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Objects;
import java.util.UUID;

@Data
public class Account {

    private Long id;

    private String number;

    private Currency currency;

    private Double balance;
    @JsonBackReference
    private Customer customer;

    public Account(Currency currency, Customer customer) {
        this.currency = currency;
        this.customer = customer;
        this.number = UUID.randomUUID().toString();
        this.balance = 0.0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) && Objects.equals(number, account.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, currency, balance, customer);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", currency=" + currency +
                ", balance=" + balance +
                ", customer=" + customer +
                '}';
    }
}
