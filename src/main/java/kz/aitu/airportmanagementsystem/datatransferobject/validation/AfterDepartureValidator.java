package kz.aitu.airportmanagementsystem.datatransferobject.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import kz.aitu.airportmanagementsystem.datatransferobject.FlightDetailsDto;

import java.sql.Timestamp;

class AfterDepartureValidator implements ConstraintValidator<ArrivalAfterDeparture, FlightDetailsDto> {
    @Override
    public void initialize(ArrivalAfterDeparture constraint) {
    }

    // validation process, checking...
    @Override
    public boolean isValid(FlightDetailsDto flightDetailsDto, ConstraintValidatorContext context) {
        if (flightDetailsDto == null) {
            return true;
        }
        Timestamp departureTime = flightDetailsDto.getDepartureTime();
        Timestamp arrivalTime = flightDetailsDto.getArrivalTime();
        return departureTime == null || arrivalTime == null || arrivalTime.after(departureTime);
    }
}