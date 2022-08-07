package pl.sda.arppl4.rental.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.sda.arppl4.rental.model.Car;
import pl.sda.arppl4.rental.model.dto.CarDTO;

import java.util.*;

@Slf4j
@RequiredArgsConstructor
public class CarServiceInMemoryImpl implements CarService {
    private Long counter = 1L;
    private final Map<Long, Car> carMap = new HashMap<>();

    public List<CarDTO> findAll() {
        List<CarDTO> cars = new ArrayList<>();
        for (Car car : carMap.values()) {
            cars.add(car.mapToCarDTO());
        }

        return cars;
    }

    public void addCar(Car car) {
        carMap.put(++counter, car);
    }

    public void deleteCar(Long carId) {
        carMap.remove(carId);
    }

    public void update(Long carId, Car editCarInformation) {
        Car car = carMap.get(carId);
        if (editCarInformation.getName() != null) {
            car.setName(editCarInformation.getName());
        }
        if (editCarInformation.getCarGearBox() != null) {
            car.setCarGearBox(editCarInformation.getCarGearBox());
        }
        if (editCarInformation.getProductionDate() != null) {
            car.setProductionDate(editCarInformation.getProductionDate());
        }
        if (editCarInformation.getSeats() != null) {
            car.setSeats(editCarInformation.getSeats());
        }
        if (editCarInformation.getEngineCapacity() != null) {
            car.setEngineCapacity(editCarInformation.getEngineCapacity());
        }
        if (editCarInformation.getBodyType() != null) {
            car.setBodyType(editCarInformation.getBodyType());
        }

        carMap.put(carId, car);
    }
}
