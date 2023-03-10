package academy.mindswap.rentacar.service;

import academy.mindswap.rentacar.converter.CarConverter;
import academy.mindswap.rentacar.converter.RentalConverter;
import academy.mindswap.rentacar.dto.RentalCreateDto;
import academy.mindswap.rentacar.dto.RentalDto;
import academy.mindswap.rentacar.model.Car;
import academy.mindswap.rentacar.model.Rental;
import academy.mindswap.rentacar.model.User;
import academy.mindswap.rentacar.repository.CarRepository;
import academy.mindswap.rentacar.repository.RentalRepository;
import academy.mindswap.rentacar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalServiceImpl implements RentalService {
    private RentalRepository rentalRepository;
    private RentalConverter rentalConverter;
    private UserRepository userRepository;
    private CarRepository carRepository;

    @Autowired
    public RentalServiceImpl(RentalRepository rentalRepository, RentalConverter rentalConverter, UserRepository userRepository, CarRepository carRepository) {
        this.rentalRepository = rentalRepository;
        this.rentalConverter = rentalConverter;
        this.userRepository = userRepository;
        this.carRepository = carRepository;
    }

    @Override
    public RentalDto createRental (RentalDto rentalDto) {
        Car car = carRepository.getReferenceById(rentalDto.getCarId());
        User user = userRepository.getReferenceById(rentalDto.getUserId());
        Rental rental = rentalConverter.fromRentalDtoToEntity(rentalDto);
        rental.setCar(car);
        rental.setUser(user);
        rentalRepository.save(rental);
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
