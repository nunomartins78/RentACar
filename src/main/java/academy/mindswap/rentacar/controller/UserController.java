package academy.mindswap.rentacar.controller;

import academy.mindswap.rentacar.dto.RentalDto;
import academy.mindswap.rentacar.dto.UserCreateDto;
import academy.mindswap.rentacar.dto.UserDto;
import academy.mindswap.rentacar.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private Boolean isDeleted = true;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/admin")
    public ResponseEntity <List<UserDto>> getAllUsers(){
        List<UserDto> userDto = userService.getAllUsers();
        return new ResponseEntity<>(userDto,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity <UserDto> getById(@PathVariable Long id){
        UserDto user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserCreateDto userDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            List<FieldError> errors = bindingResult.getFieldErrors();

            for (FieldError error: errors){
                System.out.println(error.getObjectName() + "-" + error.getDefaultMessage());
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        UserDto savedUser = userService.createUser(userDto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);

    }

    @PutMapping("admin/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id,@Valid @RequestBody UserDto userDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){

            bindingResult.getAllErrors().forEach(System.out::println);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        UserDto savedUser = userService.updateUser(id, userDto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);

    }
    @DeleteMapping("/admin")
    public ResponseEntity<RentalDto> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
