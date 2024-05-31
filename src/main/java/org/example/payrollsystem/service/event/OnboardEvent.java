package org.example.payrollsystem.service.event;

import org.example.payrollsystem.model.DateEvent;
import org.example.payrollsystem.model.Employee;

public class OnboardEvent implements Event {
    private final DateEvent onboardEvent;

    public OnboardEvent(DateEvent onboardEvent) {
        this.onboardEvent = onboardEvent;
    }

    @Override
    public void process(Employee employee) {
        employee.setOnboardEvent(onboardEvent);
    }
}
