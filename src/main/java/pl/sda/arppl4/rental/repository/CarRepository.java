package pl.sda.arppl4.rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.arppl4.rental.model.Car;

public interface CarRepository extends JpaRepository<Car, Long> {
}
