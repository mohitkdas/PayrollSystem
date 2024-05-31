package org.example.payrollsystem.service.event;

import org.example.payrollsystem.model.AmountEvent;
import org.example.payrollsystem.model.Employee;

import java.util.ArrayList;

public class ReimbursementEvent implements Event {
    private final AmountEvent amountEvent;

    public ReimbursementEvent(AmountEvent amountEvent) {
        this.amountEvent = amountEvent;
    }

    @Override
    public void process(Employee employee) {
        if (employee.getReimbursementEvents() == null) {
            employee.setReimbursementEvents(new ArrayList<>());
        }
        employee.getReimbursementEvents().add(amountEvent);
    }
}
