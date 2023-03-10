package academy.mindswap.rentacar.dto;

import academy.mindswap.rentacar.model.Car;
import academy.mindswap.rentacar.model.User;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RentalDto {

    @Column(nullable = false)
    private Date pickUpDate;
    @Column(nullable = false)
    private Date deliveryDate;
    @Column
    private List<User> users = new ArrayList<>();
    @Column
    private List<Car> cars = new ArrayList<>();
}
