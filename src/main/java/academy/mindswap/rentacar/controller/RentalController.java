package academy.mindswap.rentacar.controller;

import academy.mindswap.rentacar.dto.RentalCreateDto;
import academy.mindswap.rentacar.dto.RentalDto;
import academy.mindswap.rentacar.service.RentalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
    public ResponseEntity <List<RentalDto>> createRental(){
        List<RentalDto> rental = rentalService.getAllRentals();
        return new ResponseEntity<>(rental,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity <RentalDto> getById(@PathVariable Long id){
        RentalDto rental = rentalService.getRentalById(id);
        return new ResponseEntity<>(rental, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RentalDto> createRental(@Valid @RequestBody RentalCreateDto rental,
                                                  BindingResult bindingResult){
        if (bindingResult.hasErrors()){

            bindingResult.getAllErrors().forEach(System.out::println);
            //throw new IllegalArgumentException("Invalid user");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        RentalDto savedRental = rentalService.createRental(rental);
        return new ResponseEntity<>(savedRental, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<RentalDto> updateRental (@PathVariable Long id,
                                                   @Valid @RequestBody RentalDto rentalDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){

            bindingResult.getAllErrors().forEach(System.out::println);
            //throw new IllegalArgumentException("Invalid user");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        RentalDto savedRental = rentalService.updateRental(id, rentalDto);
        return new ResponseEntity<>(savedRental, HttpStatus.CREATED);

    }
}
