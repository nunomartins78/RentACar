package academy.mindswap.rentacar.converter;

import academy.mindswap.rentacar.dto.RentalCreateDto;
import academy.mindswap.rentacar.dto.RentalDto;
import academy.mindswap.rentacar.model.Rental;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RentalConverter {

    @Autowired
    ObjectMapper objectMapper;

    public RentalDto fromRentalEntityToRentalDto(Rental rental){
        return RentalDto.builder()
                .pickUpDate(rental.getPickUpDate())
                .deliveryDate(rental.getDeliveryDate())
                .build();
    }

    public Rental fromRentalDtoToRentalEntity(RentalDto rentalDto){
        return objectMapper.convertValue(rentalDto, Rental.class);
    }

    public Rental fromRentalCreateDtoToEntity (RentalCreateDto rentalDto){
        return Rental.builder()
                .pickUpDate(rentalDto.getPickUpDate())
                .deliveryDate(rentalDto.getDeliveryDate())
                .build();
    }
}
