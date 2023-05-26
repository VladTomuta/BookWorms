package com.example.demo.Controller;

import com.example.demo.DTO.RentDTO;
import com.example.demo.Entity.Rent;
import com.example.demo.Service.RentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@RestController
@RequestMapping("/rents")
@CrossOrigin("http://localhost:3000")
@Tag(name = "Rent")
public class RentController {

    @Autowired
    private RentService rentService;

    @Operation(summary = "Add a rent")
    @PostMapping("/addRent")
    public RentDTO addRent(@RequestBody RentDTO rentDTO){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        Rent rent = new Rent(
                rentDTO.rentedById(),
                rentDTO.renterId(),
                rentDTO.bookId(),
                LocalDate.parse(rentDTO.dateOfRental(), formatter),
                LocalDate.parse(rentDTO.dateOfReturn(), formatter),
                "REQUEST"
        );

        return rentService.addRent(rent);
    }

    @Operation(summary = "Get a rent given by id")
    @GetMapping("/getRent/{id}")
    public RentDTO getRent(@PathVariable int id){
        return rentService.getRent(id);
    }

    @Operation(summary = "Update a rent given by id")
    @PutMapping("/updateRent/{id}")
    public RentDTO updateRent(@PathVariable int id, @RequestBody RentDTO rentDTO){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        Rent newRent = new Rent(
                rentDTO.rentId(),
                rentDTO.rentedById(),
                rentDTO.renterId(),
                rentDTO.bookId(),
                LocalDate.parse(rentDTO.dateOfRental(), formatter),
                LocalDate.parse(rentDTO.dateOfReturn(), formatter),
                rentDTO.status()
        );

        return rentService.updateRent(id, newRent);
    }

    @Operation(summary = "Update all rents in order for their status to be correct")
    @PutMapping("/checkAndUpdateRentsThatArePastDue")
    public Set<RentDTO> checkAndUpdateRentsThatArePastDue() {return rentService.checkAndUpdateRentsThatArePastDue();}

    @Operation(summary = "Delete a rent given by id")
    @DeleteMapping("/deleteRent/{id}")
    public ResponseEntity<String> deleteRent(@PathVariable int id){
        return rentService.deleteRent(id);
    }

    @Operation(summary = "Get all rents")
    @GetMapping("/getAllRents")
    public Set<RentDTO> getAllRents(){
        return rentService.getAllRents();
    }

    @Operation(summary = "Get all rents where the user given by id is the renter")
    @GetMapping("/getAllRentsWhereUserIsRenter/{id}")
    public Set<RentDTO> getAllRentsWhereUserIsRenter(@PathVariable int id) {return rentService.getAllRentsWhereUserIsRenter(id);}

    @Operation(summary = "Get all rents where the user given by id is the borrower")
    @GetMapping("/getAllRentsRentedByUser/{id}")
    public Set<RentDTO> getAllRentsRentedByUser(@PathVariable int id) {return rentService.getAllRentsRentedByUser(id);}
}
