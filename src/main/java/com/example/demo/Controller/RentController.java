package com.example.demo.Controller;

import com.example.demo.DTO.RentDTO;
import com.example.demo.Entity.Rent;
import com.example.demo.Service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/rents")

public class RentController {

    @Autowired
    private RentService rentService;

    @PostMapping("/addRent")
    public RentDTO addBook(@RequestBody Rent rent){
        return rentService.addRent(rent);
    }

    @GetMapping("/getRent/{id}")
    public RentDTO getRent(@PathVariable int id){
        return rentService.getRent(id);
    }

    @PutMapping("/updateRent/{id}")
    public RentDTO updateRent(@PathVariable int id, @RequestBody Rent rent){
        return rentService.updateRent(id, rent);
    }

    @DeleteMapping("/deleteRent/{id}")
    public ResponseEntity<String> deleteRent(@PathVariable int id){
        return rentService.deleteRent(id);
    }


    @GetMapping("/getAllRents")
    public Set<RentDTO> getAllRents(){
        return rentService.getAllRents();
    }
}
