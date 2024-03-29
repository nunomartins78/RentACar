package academy.mindswap.rentacar.controller;

import academy.mindswap.rentacar.dto.RentalDto;
import academy.mindswap.rentacar.service.RentalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rental")
public class RentalController {

    private RentalService rentalService;


    @Autowired
    public RentalController(RentalService rentalService){
        this.rentalService = rentalService;
    }

    @GetMapping("")
    public ResponseEntity <List<RentalDto>> getRental(){
        return new ResponseEntity<>(rentalService.getAllRentals(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity <RentalDto> getRentalById(@PathVariable Long id){
        return new ResponseEntity<>(rentalService.getRentalById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RentalDto> createRental(@Valid @RequestBody RentalDto rentalDto,
                                                  BindingResult bindingResult){
        if (bindingResult.hasErrors()){

            List<FieldError> errors = bindingResult.getFieldErrors();

            for (FieldError error : errors) {
                System.out.println(error.getObjectName() + " - " + error.getDefaultMessage());
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        RentalDto savedRentalDto = rentalService.createRental(rentalDto);
        return new ResponseEntity<>(savedRentalDto, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<RentalDto> updateRental (@PathVariable Long id, @Valid @RequestBody RentalDto rentalDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){

            bindingResult.getAllErrors().forEach(System.out::println);
            //throw new IllegalArgumentException("Invalid user");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        rentalDto.setId(id);
        RentalDto savedRental = rentalService.updateRental(id, rentalDto);
        return new ResponseEntity<>(savedRental, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRental (@PathVariable Long id, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(System.out::println);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        rentalService.deleteRental(id);
        return new ResponseEntity<>("Rental has been deleted",HttpStatus.OK);
    }
}