package pl.sda.arppl4.rental.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.sda.arppl4.rental.model.Car;
import pl.sda.arppl4.rental.model.CarRental;
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
    public List<Car> getAvailableCars() {
        log.info("Requested rental available cars");
        return carRentalService.getAllAvailableCars();
    }

    @PostMapping("/rent")
    public void rentCar(@RequestParam Long carId, @RequestBody CarRental carRental) {
        log.info("Requested rental of car with id: " + carId);
        carRentalService.rentCar(carId, carRental);
    }

//    @PostMapping("/rent/{carId}")
//    public void rentCar(@PathVariable Long carId, @RequestBody CarRental carRental) {
//        log.info("Requested rental of car with id: " + carId);
//        carRentalService.rentCar(carId, carRental);
//    }
//    ###
//    POST http://localhost:18085/api/rental/rent/3
//    Content-Type: application/json
//
//    {
//        "clientName": "Pawel",
//            "clientSurname": "Gawel",
//            "price": 300.0
//    }
}
