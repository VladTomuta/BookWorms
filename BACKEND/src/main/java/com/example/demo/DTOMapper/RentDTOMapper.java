package com.example.demo.DTOMapper;

import com.example.demo.DTO.RentDTO;
import com.example.demo.Entity.Rent;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class RentDTOMapper implements Function<Rent, RentDTO> {

    @Override
    public RentDTO apply(Rent rent) {
        return new RentDTO(
                rent.getRent_id(),
                rent.getRented_by_id(),
                rent.getRenter_id(),
                rent.getBook_id(),
                rent.getDate_of_rental(),
                rent.getDate_of_return(),
                rent.getStatus()
        );
    }
}
