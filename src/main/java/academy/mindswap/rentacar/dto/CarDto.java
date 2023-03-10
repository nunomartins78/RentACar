package academy.mindswap.rentacar.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CarDto {

    @NotBlank(message = "Must have a brand")
    private String brand;
    @NotBlank(message = "Must have a model")
    private String model;
    @NotNull
    private Integer price;
    @NotBlank(message = "Must have a licence plate")
    @Pattern(regexp = "[0-9A-Z]{2}-[0-9A-Z]{2}-[0-9A-Z]{2}", message = "Invalid licence plate")
    private String licencePlate;
}
