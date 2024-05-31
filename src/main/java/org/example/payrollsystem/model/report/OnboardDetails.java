package org.example.payrollsystem.model.report;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OnboardDetails {
    private String empId;
    private String firstName;
    private String lastName;
    private String designation;
}
