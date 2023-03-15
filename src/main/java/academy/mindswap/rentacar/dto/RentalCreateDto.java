package academy.mindswap.rentacar.dto;


import jakarta.persistence.Column;
import jakarta.persistence.ForeignKey;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.Date;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RentalCreateDto {

    @Column(nullable = false)
    private Date pickUpDate;
    @Column(nullable = false)
    private Date deliveryDate;
    @NotNull
    private Long carId;

}