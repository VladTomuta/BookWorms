package com.example.demo.Controller;

import com.example.demo.DTO.RentDTO;
import com.example.demo.Entity.Rent;
import com.example.demo.Service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@RestController
@RequestMapping("/rents")
@CrossOrigin("http://localhost:3000")
public class RentController {

    @Autowired
    private RentService rentService;

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
    @GetMapping("/getRent/{id}")
    public RentDTO getRent(@PathVariable int id){
        return rentService.getRent(id);
    }

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

    @PutMapping("/checkAndUpdateRentsThatArePastDue")
    public Set<RentDTO> checkAndUpdateRentsThatArePastDue() {return rentService.checkAndUpdateRentsThatArePastDue();}

    @DeleteMapping("/deleteRent/{id}")
    public ResponseEntity<String> deleteRent(@PathVariable int id){
        return rentService.deleteRent(id);
    }

    @GetMapping("/getAllRents")
    public Set<RentDTO> getAllRents(){
        return rentService.getAllRents();
    }

    @GetMapping("/getAllRentsWhereUserIsRenter/{id}")
    public Set<RentDTO> getAllRentsWhereUserIsRenter(@PathVariable int id) {return rentService.getAllRentsWhereUserIsRenter(id);}

    @GetMapping("/getAllRentsRentedByUser/{id}")
    public Set<RentDTO> getAllRentsRentedByUser(@PathVariable int id) {return rentService.getAllRentsRentedByUser(id);}
}
