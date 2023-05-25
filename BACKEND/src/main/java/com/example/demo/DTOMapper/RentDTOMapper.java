package com.example.demo.DTOMapper;

import com.example.demo.DTO.RentDTO;
import com.example.demo.Entity.Rent;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.function.Function;

@Service
public class RentDTOMapper implements Function<Rent, RentDTO> {
    @Override
    public RentDTO apply(Rent rent) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        return new RentDTO(
                rent.getRentId(),
                rent.getRentedById(),
                rent.getRenterId(),
                rent.getBookId(),
                rent.getDateOfRental().toString(),
                rent.getDateOfReturn().toString(),
                rent.getStatus()
        );
    }
}
