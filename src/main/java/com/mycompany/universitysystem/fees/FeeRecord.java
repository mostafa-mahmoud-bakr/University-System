package com.mycompany.universitysystem.fees;

public class FeeRecord {
    private String studentId;
    private double totalFees;
    private double paidAmount;
    private String studentName; 
    public FeeRecord(String studentId, String studentName, double totalFees, double paidAmount) {
        this.studentId = studentId;
        this.totalFees = totalFees;
        this.paidAmount = paidAmount;
        this.studentName = studentName;

    }

    public String getStudentId() {
        return studentId;
    }

    public double getTotalFees() {
        return totalFees;
    }
    
    public String getStudentName() { return studentName; } 
    
    public double getPaidAmount() {
        return paidAmount;
    }

    public void pay(double amount) {
        this.paidAmount += amount;
    }

    public double getRemainingFees() {
        return totalFees - paidAmount;
    }

    public boolean isFullyPaid() {
        return paidAmount >= totalFees;
    }
}