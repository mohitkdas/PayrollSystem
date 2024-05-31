package org.example.payrollsystem.service.event;

import org.example.payrollsystem.model.AmountEvent;
import org.example.payrollsystem.model.Employee;

import java.util.ArrayList;

public class BonusEvent implements Event {
    private final AmountEvent amountEvent;

    public BonusEvent(AmountEvent amountEvent) {
        this.amountEvent = amountEvent;
    }

    @Override
    public void process(Employee employee) {
        if (employee.getBonusEvents() == null) {
            employee.setBonusEvents(new ArrayList<>());
        }
        employee.getBonusEvents().add(amountEvent);
    }
}