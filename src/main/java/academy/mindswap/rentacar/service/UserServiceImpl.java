package academy.mindswap.rentacar.service;

import academy.mindswap.rentacar.converter.UserConverter;
import academy.mindswap.rentacar.dto.UserCreateDto;
import academy.mindswap.rentacar.dto.UserDto;
import academy.mindswap.rentacar.model.User;
import academy.mindswap.rentacar.repository.UserRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
   @Autowired
    private UserRepository userRepository;
   @Autowired
   private EntityManager entityManager;
   private UserConverter userConverter;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    @Override
    public UserDto createUser(UserCreateDto userCreateDto) {
        if (!userCreateDto.getPassword().equals(userCreateDto.getRetypePassword())) {
            throw new IllegalArgumentException("Passwords do not match.");
        }
        User user = userConverter.fromUserCreateDtoToEntity(userCreateDto);
        user = userRepository.save(user);
        return userConverter.fromUserEntityToUserDto(user);
    }


    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepository.getReferenceById(userId);
        return userConverter.fromUserEntityToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = users.stream()
                .map(userConverter::fromUserEntityToUserDto)
                .toList();
        return userDtos;
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
            User existingUser = userRepository.getReferenceById(id);
            if (existingUser == null) {
                throw new IllegalArgumentException("User not found.");
            }
            existingUser.setFirstName(userDto.getFirstName());
            existingUser.setLastName(userDto.getLastName());
            existingUser.setEmail(userDto.getEmail());
            User updatedUser = userRepository.save(existingUser);
            return userConverter.fromUserEntityToUserDto(updatedUser);
        }

        @Override
        public void deleteUser (Long userId){
        User user = userRepository.getReferenceById(userId);
        if(user == null){
            throw new IllegalArgumentException("User not found.");
        }
        userRepository.delete(user);
    }
}
