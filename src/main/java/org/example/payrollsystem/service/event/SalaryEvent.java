package org.example.payrollsystem.service.event;

import org.example.payrollsystem.model.AmountEvent;
import org.example.payrollsystem.model.Employee;

import java.util.ArrayList;

public class SalaryEvent implements Event {
    private final AmountEvent amountEvent;

    public SalaryEvent(AmountEvent amountEvent) {
        this.amountEvent = amountEvent;
    }

    @Override
    public void process(Employee employee) {
        if (employee.getSalaryEvents() == null) {
            employee.setSalaryEvents(new ArrayList<>());
        }
        employee.getSalaryEvents().add(amountEvent);
    }
}
