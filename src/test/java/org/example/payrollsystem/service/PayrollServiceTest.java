package org.example.payrollsystem.service;

import org.example.payrollsystem.exception.InvalidDataFormatException;
import org.example.payrollsystem.exception.MissingDataException;
import org.example.payrollsystem.model.AmountEvent;
import org.example.payrollsystem.model.DateEvent;
import org.example.payrollsystem.service.event.BonusEvent;
import org.example.payrollsystem.service.event.EventFactory;
import org.example.payrollsystem.service.event.OnboardEvent;
import org.example.payrollsystem.service.event.SalaryEvent;
import org.example.payrollsystem.service.report.ReportStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class PayrollServiceTest {

    @Mock
    List<ReportStrategy> reportStrategies;

    @Mock
    private EventFactory eventFactory;

    @InjectMocks
    private PayrollService payrollService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testProcessFile_withValidData() throws Exception {
        String fileContent = "1, E001, John, Doe, Developer, ONBOARD, 01-01-2020, 01-01-2020, Notes\n" +
                "2, E001, SALARY, 1000, 01-02-2020, Notes\n" +
                "3, E001, BONUS, 500, 01-03-2020, Notes\n";
        InputStream inputStream = new ByteArrayInputStream(fileContent.getBytes());

        when(eventFactory.createEvent(eq("ONBOARD"), any(DateEvent.class))).thenReturn(mock(OnboardEvent.class));
        when(eventFactory.createEvent(eq("SALARY"), any(AmountEvent.class))).thenReturn(mock(SalaryEvent.class));
        when(eventFactory.createEvent(eq("BONUS"), any(AmountEvent.class))).thenReturn(mock(BonusEvent.class));

        payrollService.processFile(inputStream);

        verify(eventFactory, times(1)).createEvent(eq("ONBOARD"), any(DateEvent.class));
        verify(eventFactory, times(1)).createEvent(eq("SALARY"), any(AmountEvent.class));
        verify(eventFactory, times(1)).createEvent(eq("BONUS"), any(AmountEvent.class));
        verify(reportStrategies, times(1)).forEach(any());
    }

    @Test
    void testProcessFile_withInvalidDataFormat() {
        String fileContent = "1, John, Doe, Developer, ONBOARD, 01-01-2020, 01-01-2020, Onboard\n";
        InputStream inputStream = new ByteArrayInputStream(fileContent.getBytes());

        assertThrows(InvalidDataFormatException.class, () -> payrollService.processFile(inputStream));
    }

    @Test
    void testProcessFile_withMissingData() {
        String fileContent = "1, , John, Doe, Developer, ONBOARD, 01-01-2020, 01-01-2020, Onboard\n";
        InputStream inputStream = new ByteArrayInputStream(fileContent.getBytes());

        assertThrows(MissingDataException.class, () -> payrollService.processFile(inputStream));
    }

}