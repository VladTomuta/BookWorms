package com.example.demo.Service;

import com.example.demo.DTO.RentDTO;
import com.example.demo.DTOMapper.BookDTOMapper;
import com.example.demo.DTOMapper.RentDTOMapper;
import com.example.demo.Entity.Rent;
import com.example.demo.Exception.IncorrectIdException;
import com.example.demo.Repository.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RentService {

    @Autowired
    private RentRepository rentRepository;
    @Autowired
    private RentDTOMapper rentDTOMapper;

    public RentDTO addRent(Rent rent) {
        return rentDTOMapper.apply(rentRepository.saveAndFlush(rent));
    }

    public RentDTO getRent(int id){
        return rentRepository.findById(id)
                .stream()
                .findAny()
                .map(rentDTOMapper)
                .orElseThrow(IncorrectIdException::new);
    }

    public Set<RentDTO> getAllRents() {
        return  rentRepository.findAll()
                .stream()
                .map(rentDTOMapper)
                .collect(Collectors.toSet());
    }

    public RentDTO updateRent(int id, Rent rent) {
        if(rentRepository.findById(id).isPresent()) {
            Rent actualRent = rentRepository.findById(id).get();

            if(rent.getRenter_id() != 0){
                actualRent.setRenter_id(rent.getRenter_id());
            }

            if(rent.getRented_by_id() != 0) {
                actualRent.setRented_by_id(rent.getRented_by_id());
            }

            if(rent.getBook_id() != 0) {
                actualRent.setBook_id(rent.getBook_id());
            }

            if(rent.getDate_of_rental() != null) {
                actualRent.setDate_of_rental(rent.getDate_of_rental());
            }

            if(rent.getDate_of_return() != null) {
                actualRent.setDate_of_return(rent.getDate_of_return());
            }

            return rentDTOMapper.apply(rentRepository.saveAndFlush(actualRent));
        }

        return rentDTOMapper.apply(rent);
    }

    public ResponseEntity<String> deleteRent(int id) {
        if(rentRepository.findById(id).isPresent()){
            rentRepository.deleteById(id);
            return new ResponseEntity<String>("Sucsessfully deleted!", HttpStatus.OK);
        }

        return new ResponseEntity<String>("Can't find specified rent!", HttpStatus.NOT_FOUND);
    }
}
