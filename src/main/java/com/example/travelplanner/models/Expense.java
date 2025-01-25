package com.example.travelplanner.models;

import jakarta.persistence.*;


@Entity
@Table(name = "expense")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String expenseCategory;

    @Column(nullable = false)
    private double amount;

    @Column(columnDefinition = "TEXT")
    private String remarks;

    @ManyToOne
    @JoinColumn(name = "budget_id", referencedColumnName = "id")
    private Budget budget;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExpenseCategory() {
        return expenseCategory;
    }

    public void setExpenseCategory(String expenseCategory) {
        this.expenseCategory = expenseCategory;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    


}
