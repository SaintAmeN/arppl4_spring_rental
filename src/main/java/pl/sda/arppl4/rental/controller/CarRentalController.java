package pl.sda.arppl4.rental.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.sda.arppl4.rental.model.Car;
import pl.sda.arppl4.rental.model.CarRental;
import pl.sda.arppl4.rental.model.dto.CarDTO;
import pl.sda.arppl4.rental.model.dto.RentCarRequest;
import pl.sda.arppl4.rental.repository.CarRentalRepository;
import pl.sda.arppl4.rental.repository.CarRepository;
import pl.sda.arppl4.rental.service.CarRentalService;
import pl.sda.arppl4.rental.service.CarService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/rental")
@RequiredArgsConstructor
public class CarRentalController {
    private final CarRentalService carRentalService;

    @GetMapping("/available")
    public List<CarDTO> getAvailableCars() {
        log.info("Requested rental available cars");
        return carRentalService.getAllAvailableCars();
    }

    @PostMapping("/rent")
    public void rentCar(@RequestParam Long carId, @RequestBody RentCarRequest request) {
        log.info("Requested rental of car with id: " + carId);
        carRentalService.rentCar(carId, request);
    }

    @PatchMapping("/return")
    public void returnCar(@RequestParam Long carId) {
        log.info("Requested return of car with id: " + carId);
        carRentalService.returnCar(carId);
    }
}
