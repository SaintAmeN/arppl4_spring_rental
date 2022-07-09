package pl.sda.arppl4.rental.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sda.arppl4.rental.model.CarBodyType;
import pl.sda.arppl4.rental.model.CarGearBox;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarDTO {
    private Long carId;
    private String name;
    private String make;
    private LocalDate productionDate;
    private CarBodyType bodyType;
    private Integer seats;
    private CarGearBox carGearBox;
    private Double engineCapacity;
}
