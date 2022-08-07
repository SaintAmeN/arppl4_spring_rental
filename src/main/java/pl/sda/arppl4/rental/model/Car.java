package pl.sda.arppl4.rental.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import pl.sda.arppl4.rental.model.dto.CarDTO;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Data // Getter Setter ToString EqualsAndHashCode
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    ///
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    ///

    private String name;
    private String make;
    private LocalDate productionDate;

    @Enumerated(EnumType.STRING)
    private CarBodyType bodyType;

    private Integer seats;

    @Enumerated(EnumType.STRING)
    private CarGearBox carGearBox;

    private Double engineCapacity;

    // carRentals a. zawiera wszystkie wynajmy w bazie?
    //            b. zawiera tylko wynajmy powiązane z tym samochodem?
    @OneToMany(mappedBy = "car", fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @JsonManagedReference
    private Set<CarRental> carRentals;

    public CarDTO mapToCarDTO() {
        return new CarDTO(
                id,
                name,
                make,
                productionDate,
                bodyType,
                seats,
                carGearBox,
                engineCapacity
        );
    }
}

