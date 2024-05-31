package org.example.payrollsystem.service.report;

import org.example.payrollsystem.model.Employee;

import java.util.List;

public interface ReportStrategy {
    void generateReport(List<Employee> employees);
}