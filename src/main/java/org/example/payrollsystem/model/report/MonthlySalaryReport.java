package org.example.payrollsystem.model.report;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MonthlySalaryReport {
    private String monthYear;
    private double totalSalary;
    private int totalEmployees;
}
