package academy.mindswap.rentacar.service;

import academy.mindswap.rentacar.dto.RentalCreateDto;
import academy.mindswap.rentacar.dto.RentalDto;

import java.util.List;

public interface RentalService {

    RentalDto createRental (RentalDto rentalDto);

    RentalDto getRentalById(Long rentalId);

    List<RentalDto> getAllRentals();

    RentalDto updateRental(Long id, RentalDto rentalDto);

    void deleteRental(Long rentalId);
}
