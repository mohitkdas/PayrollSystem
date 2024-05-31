package org.example.payrollsystem.service.report;

import org.example.payrollsystem.model.AmountEvent;
import org.example.payrollsystem.model.Employee;
import org.example.payrollsystem.model.report.MonthlySalaryReport;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Order(4)
public class MonthlySalaryReportStrategy implements ReportStrategy {
    @Override
    public void generateReport(List<Employee> employees) {
        Map<String, MonthlySalaryReport> monthlyReportMap = new HashMap<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");

        for (Employee employee : employees) {
            if (employee.getSalaryEvents() != null) {
                for (AmountEvent salaryEvent : employee.getSalaryEvents()) {
                    LocalDate salaryDate = salaryEvent.getEventDate();
                    String monthYearKey = salaryDate.format(formatter);

                    MonthlySalaryReport report = monthlyReportMap.computeIfAbsent(monthYearKey, k -> new MonthlySalaryReport(k, 0, 0));
                    report.setTotalSalary(report.getTotalSalary() + salaryEvent.getAmount());
                    report.setTotalEmployees(report.getTotalEmployees() + 1);
                }
            }
        }

        // Print or store the report
        System.out.println("<===== Monthly Salary Report =====>");
        monthlyReportMap.values().forEach(report -> System.out.println("Month: " + report.getMonthYear() + ", Total Salary: " + report.getTotalSalary() + ", Total Employees: " + report.getTotalEmployees()));
    }
}
