package academy.mindswap.rentacar.service;

import academy.mindswap.rentacar.dto.UserDto;
import academy.mindswap.rentacar.model.User;

import java.util.List;

public interface AdminService {

    UserDto createAdmin (Long userId);
    void deleteUser(Long userId);
    void deleteCar(Long carId);
    void deleteRental(Long rentalId);
    List<User> getAllDeletedUsers(boolean isDeleted);
}
