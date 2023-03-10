package academy.mindswap.rentacar.converter;

import academy.mindswap.rentacar.dto.CarCreateDto;
import academy.mindswap.rentacar.dto.CarDto;
import academy.mindswap.rentacar.model.Car;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CarConverter {

    @Autowired
    ObjectMapper objectMapper;

    public CarDto fromCarEntityToCarDto(Car car){
        return CarDto.builder()
                .brand(car.getBrand())
                .model(car.getModel())
                .price(car.getPrice())
                .licencePlate(car.getLicencePlate())
                .build();
    }

    public Car fromCarDtoToCarEntity(CarDto carDto){
        return objectMapper.convertValue(carDto, Car.class);
    }

    public Car fromCarCreateDtoToEntity (CarCreateDto carDto){
        return Car.builder()
                .brand(carDto.getBrand())
                .model(carDto.getModel())
                .price(carDto.getPrice())
                .licencePlate(carDto.getLicencePlate())
                .build();
    }
}
