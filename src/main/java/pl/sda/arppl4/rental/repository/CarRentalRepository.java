package pl.sda.arppl4.rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.arppl4.rental.model.Car;
import pl.sda.arppl4.rental.model.CarRental;

public interface CarRentalRepository extends JpaRepository<CarRental, Long> {
}
