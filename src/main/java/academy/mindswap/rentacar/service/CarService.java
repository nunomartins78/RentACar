package academy.mindswap.rentacar.service;

import academy.mindswap.rentacar.dto.CarCreateDto;
import academy.mindswap.rentacar.dto.CarDto;


import java.util.List;

public interface CarService {

    CarDto createCar(CarCreateDto carDto);

    CarDto getCarById(Long carId);

    List<CarDto> getAllCars();

    CarDto updateCar(Long id, CarDto carDto);

    void deleteCar(Long carId);
}
