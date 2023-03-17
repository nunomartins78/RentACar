package academy.mindswap.rentacar.service;

import academy.mindswap.rentacar.converter.UserConverter;
import academy.mindswap.rentacar.dto.UserDto;
import academy.mindswap.rentacar.model.Role;
import academy.mindswap.rentacar.model.User;
import academy.mindswap.rentacar.repository.CarRepository;
import academy.mindswap.rentacar.repository.RentalRepository;
import academy.mindswap.rentacar.repository.UserRepository;
import jakarta.persistence.EntityManager;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    private UserRepository userRepository;
    private CarRepository carRepository;
    private RentalRepository rentalRepository;
    private EntityManager entityManager;
    UserConverter userConverter;

    @Autowired
    public AdminServiceImpl (UserRepository userRepository, CarRepository carRepository, RentalRepository rentalRepository){
        this.userRepository = userRepository;
        this.carRepository = carRepository;
        this.rentalRepository = rentalRepository;
    }

    @Override
    public UserDto createAdmin (Long userId){
        User userToAdmin = userRepository.findById(userId)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "The user with id "+ userId + "was not found"));
        userToAdmin.setRole(Role.ADMIN);
        User newAdmin = userRepository.save(userToAdmin);
        return userConverter.fromUserEntityToUserDto(newAdmin);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public List<User> getAllDeletedUsers(boolean isDeleted) {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedUserFilter");
        filter.setParameter("isDeleted", isDeleted);
        List<User> userDto = userRepository.findAll();
        session.disableFilter("deletedUserFilter");
        return userDto;
    }

    @Override
    public void deleteCar(Long carId) {
        carRepository.deleteById(carId);
    }

    @Override
    public void deleteRental(Long rentalId) {
        rentalRepository.deleteById(rentalId);
    }
}
