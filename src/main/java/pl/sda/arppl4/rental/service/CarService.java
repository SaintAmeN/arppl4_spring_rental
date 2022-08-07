package pl.sda.arppl4.rental.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.sda.arppl4.rental.model.Car;
import pl.sda.arppl4.rental.model.dto.CarDTO;
import pl.sda.arppl4.rental.repository.CarRepository;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface CarService {
    public List<CarDTO> findAll();
    public void addCar(Car car);
    public void deleteCar(Long carId);
    public void update(Long carId, Car editCarInformation);
}
