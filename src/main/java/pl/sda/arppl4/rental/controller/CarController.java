package pl.sda.arppl4.rental.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.sda.arppl4.rental.model.Car;
import pl.sda.arppl4.rental.model.dto.CarDTO;
import pl.sda.arppl4.rental.service.CarService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/car")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @GetMapping()
    public List<CarDTO> list() {
        log.info("Received request: list");
        return carService.findAll();
    }

    @PostMapping()
    public void create(@RequestBody Car car) {
        log.info("Received request: create -> " + car);
        carService.addCar(car);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long carId) {
        log.info("Received request: delete -> " + carId);
        carService.deleteCar(carId);
    }

    @PatchMapping("/{id}")
    public void update(@PathVariable(name = "id") Long carId, @RequestBody Car car) {
        log.info("Received request: update -> " + car);
        carService.update(carId, car);
    }
}
