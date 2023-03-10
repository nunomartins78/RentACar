package academy.mindswap.rentacar.dto;

import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "Must have a price")
    private Integer price;
    @NotBlank(message = "Must have a licence plate")
    @Pattern(regexp = "\n" +
            "[0-9]{2}[\\s-]{0,1}[0-9]{2}[\\s-]{0,1}[A-IK-PR-VZ]{2}|[0-9]{2}[\\s-]{0,1}[A-IK-PR-VZ]{2}[\\s-]{0,1}[0-9]{2}|[A-IK-PR-WYZ]{2}[\\s-]{0,1}[0-9]{2}[\\s-]{0,1}[A-IK-PR-WYZ]{2}", message = "Invalid licence plate")
    private String licencePlate;
}
