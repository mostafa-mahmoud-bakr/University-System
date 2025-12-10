package com.mycompany.universitysystem.fees;

import java.util.ArrayList;
import java.util.HashMap;

public class FeesService {

    private static HashMap<String, FeeRecord> feeDatabase = new HashMap<>();

    // Add or update a student's fee record
    public static void addOrUpdateFeeRecord(FeeRecord record) {
        feeDatabase.put(record.getStudentId(), record);
    }

    // Get fee record for a student
    public static FeeRecord getFeeRecord(String studentId) {
        return feeDatabase.get(studentId);
    }
    
    public static ArrayList<String> getPendingStudents() {
        ArrayList<String> pendingList = new ArrayList<>();
    for (FeeRecord record : feeDatabase.values()) {
        if (!record.isFullyPaid()) {
            pendingList.add(record.getStudentId() + " - Remaining: " + record.getRemainingFees());
        }
    }
    return pendingList;
}
    
    public static ArrayList<String> getPaidStudents() {
    ArrayList<String> paidList = new ArrayList<>();
    for (FeeRecord record : feeDatabase.values()) {
        if (record.isFullyPaid()) {
            paidList.add(record.getStudentId() + " - Paid in full");
        }
    }
    return paidList;
}

    // Pay fees
    public static boolean payFees(String studentId, double amount) {
        FeeRecord record = feeDatabase.get(studentId);

        if (record == null)
            return false;

        record.pay(amount);
        return true;
    }
    
    public static boolean deleteFeeRecord(String studentId) {
    return feeDatabase.remove(studentId) != null;
}
    
    public static HashMap<String, FeeRecord> getFeeDatabase() {
    return feeDatabase;
}
}

