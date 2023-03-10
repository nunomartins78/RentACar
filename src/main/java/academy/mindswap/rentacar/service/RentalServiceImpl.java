package academy.mindswap.rentacar.service;

import academy.mindswap.rentacar.converter.RentalConverter;
import academy.mindswap.rentacar.dto.RentalCreateDto;
import academy.mindswap.rentacar.dto.RentalDto;
import academy.mindswap.rentacar.model.Rental;
import academy.mindswap.rentacar.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalServiceImpl implements RentalService {
    private RentalRepository rentalRepository;
    private RentalConverter rentalConverter;

    @Autowired
    public RentalServiceImpl(RentalRepository rentalRepository, RentalConverter rentalConverter) {
        this.rentalRepository = rentalRepository;
        this.rentalConverter = rentalConverter;
    }

    @Override
    public RentalDto createRental (RentalCreateDto rentalCreateDto) {
        Rental rental = rentalConverter.fromRentalCreateDtoToEntity(rentalCreateDto);
        rental = rentalRepository.save(rental);
        return rentalConverter.fromRentalEntityToRentalDto(rental);
    }


    @Override
    public RentalDto getRentalById(Long userId) {
        Rental rental = rentalRepository.getReferenceById(userId);
        return rentalConverter.fromRentalEntityToRentalDto(rental);
    }

    @Override
    public List<RentalDto> getAllRentals() {
        List<Rental> rentals = rentalRepository.findAll();
        List<RentalDto> rentalDtos = rentals.stream()
                .map(rentalConverter::fromRentalEntityToRentalDto)
                .toList();
        return rentalDtos;
    }

    @Override
    public RentalDto updateRental(Long id, RentalDto rentalDto) {
        Rental existingRental = rentalRepository.getReferenceById(id);
        if (existingRental == null) {
            throw new IllegalArgumentException("Rental not found.");
        }
        existingRental.setPickUpDate(rentalDto.getPickUpDate());
        existingRental.setDeliveryDate(rentalDto.getDeliveryDate());
        Rental updatedRental = rentalRepository.save(existingRental);
        return rentalConverter.fromRentalEntityToRentalDto(updatedRental);
    }

    @Override
    public void deleteRental (Long rentalId){
        Rental rental = rentalRepository.getReferenceById(rentalId);
        if(rental == null){
            throw new IllegalArgumentException("Rental not found.");
        }
        rentalRepository.delete(rental);
    }
}
