package org.example.payrollsystem.service.event;

import org.example.payrollsystem.model.DateEvent;
import org.example.payrollsystem.model.Employee;

public class ExitEvent implements Event {
    private final DateEvent dateEvent;

    public ExitEvent(DateEvent dateEvent) {
        this.dateEvent = dateEvent;
    }

    @Override
    public void process(Employee employee) {
        employee.setExitEvent(dateEvent);
    }
}
