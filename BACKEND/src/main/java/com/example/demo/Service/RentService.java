package com.example.demo.Service;

import com.example.demo.DTO.RentDTO;
import com.example.demo.DTOMapper.RentDTOMapper;
import com.example.demo.Entity.Rent;
import com.example.demo.Exception.IncorrectIdException;
import com.example.demo.Repository.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    public Set<RentDTO> getAllRentsWhereUserIsRenter(int id) {

        System.out.println(id);

        return  rentRepository.findAll()
                .stream()
                .map(rentDTOMapper)
                .filter(rentDTO -> rentDTO.renterId() == id)
                .collect(Collectors.toSet());
    }

    public Set<RentDTO> getAllRentsRentedByUser(int id) {
        return  rentRepository.findAll()
                .stream()
                .map(rentDTOMapper)
                .filter(rentDTO -> rentDTO.rentedById() == id)
                .collect(Collectors.toSet());
    }

    public RentDTO updateRent(int id, Rent rent) {
        if(rentRepository.findById(id).isPresent()) {
            Rent actualRent = rentRepository.findById(id).get();

            if(rent.getRenterId() != 0){
                actualRent.setRenterId(rent.getRenterId());
            }

            if(rent.getRentedById() != 0) {
                actualRent.setRentedById(rent.getRentedById());
            }

            if(rent.getBookId() != 0) {
                actualRent.setBookId(rent.getBookId());
            }

            if(rent.getDateOfRental() != null) {
                actualRent.setDateOfRental(rent.getDateOfRental());
            }

            if(rent.getDateOfReturn() != null) {
                actualRent.setDateOfReturn(rent.getDateOfReturn());
            }

            if(rent.getStatus() != null) {
                actualRent.setStatus(rent.getStatus());
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

    public Set<RentDTO> checkAndUpdateRentsThatArePastDue() {
        Set<RentDTO> rents = rentRepository.findAll()
                                           .stream()
                                           .map(rentDTOMapper)
                                           .collect(Collectors.toSet());

        LocalDate currentDate = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (RentDTO rentDTO : rents) {

            if (LocalDate.parse(rentDTO.dateOfReturn(), formatter).isBefore(currentDate)) {

                Rent newRent = new Rent(
                        rentDTO.rentId(),
                        0,
                        0,
                        0,
                        null,
                        null,
                        rentDTO.status()
                );

                switch (newRent.getStatus()) {
                    case "REQUEST" -> {
                        newRent.setStatus("DENIED");
                        updateRent(newRent.getRentId(), newRent);
                    }
                    case "ONGOING" -> {
                        newRent.setStatus("PAST DUE DATE");
                        updateRent(newRent.getRentId(), newRent);
                    }
                }
            }
        }

        return rents;
    }
}
