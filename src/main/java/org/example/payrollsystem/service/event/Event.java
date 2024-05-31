package org.example.payrollsystem.service.event;

import org.example.payrollsystem.model.Employee;

public interface Event {
    void process(Employee employee);
}
