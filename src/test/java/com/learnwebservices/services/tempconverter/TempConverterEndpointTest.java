package com.learnwebservices.services.tempconverter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TempConverterEndpointTest {

    @Mock
    TempConverterService tempConverterService;

    @InjectMocks
    DelegatingTempConverterEndpoint tempConverterEndpoint;

    @Test
    void testConvertToFahrenheit() {
        when(tempConverterService.convertFahrenheitToCelsius(anyDouble())).thenReturn(-17.778);

        var response =
                tempConverterEndpoint.convertToCelsius(new FahrenheitToCelsiusRequest(0.0));
        verify(tempConverterService).convertFahrenheitToCelsius(0.0);
        assertEquals(-17.778, response.getTemperatureInCelsius(), 0.0005);
    }

    @Test
    void testConvertToCelsius() {
        when(tempConverterService.convertCelsiusToFahrenheit(anyDouble())).thenReturn(32.0);

        CelsiusToFahrenheitResponse response =
                tempConverterEndpoint.convertToFahrenheit(new CelsiusToFahrenheitRequest(0.0));
        verify(tempConverterService).convertCelsiusToFahrenheit(0.0);
        assertEquals(32.0, response.getTemperatureInFahrenheit(), 0.0005);
    }
}
