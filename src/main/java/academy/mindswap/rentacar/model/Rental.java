package academy.mindswap.rentacar.model;

import jakarta.persistence.*;
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
@Entity
@Table(name="rentals")

public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Date pickUpDate;
    @Column(nullable = false)
    private Date deliveryDate;
    @ManyToOne (targetEntity = Car.class)
    @JoinColumn(name = "car_id")
    private Car car;
    @ManyToOne (targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;
/*    @ManyToMany (targetEntity = User.class, fetch = FetchType.EAGER)
    private List<User> users = new ArrayList<>();
    @ManyToMany (targetEntity = Car.class, fetch = FetchType.EAGER)
    private List<Car> cars = new ArrayList<>();*/
}
