package pl.sda.arppl4.rental.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.sda.arppl4.rental.model.Car;
import pl.sda.arppl4.rental.model.CarRental;
import pl.sda.arppl4.rental.repository.CarRentalRepository;
import pl.sda.arppl4.rental.repository.CarRepository;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarRentalService {
    private final CarRentalRepository carRentalRepository;
    private final CarRepository carRepository;

    public List<Car> getAllAvailableCars() {
        List<Car> carList = carRepository.findAll();

        List<Car> cars = new ArrayList<>();
        for (Car car : carList) {
            if(!isRented(car)){
                // samochód jest dostępny, zwróć go...
                cars.add(car);
            }
        }

        return cars;
    }

    /**
     * Metoda sprawdza czy dany samochód jest wynajęty. Jeśli jego data zwrotu (dowolnego najmu)
     * jest równa null, to samochód jest wynajęty.
     * @param car - sprawdzany samochód.
     * @return informacja czy samochód jest wynajęty (true/false).
     */
    private boolean isRented(Car car) {
        for (CarRental carRental : car.getCarRentals()) {
            if (carRental.getReturnDateTime() == null) {
                return true;
            }
        }
        return false;
    }
}
