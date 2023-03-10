package academy.mindswap.rentacar.converter;
import academy.mindswap.rentacar.dto.UserCreateDto;
import academy.mindswap.rentacar.dto.UserDto;
import academy.mindswap.rentacar.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserConverter {

    ObjectMapper objectMapper;

    public UserDto fromUserEntityToUserDto(User user) {
        return UserDto.builder()
                .firstName(user.getFirstName())
                .email(user.getEmail())
                .build();
    }

    public User fromUserDTOToUserEntity(UserDto userDto) {
        return objectMapper.convertValue(userDto, User.class);
    }

    public User fromUserCreateDtoToEntity(UserCreateDto userDto){
        return User.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .role(userDto.getRole())
                .build();
    }

}