package pl.sda.arppl4.rental.model;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CarRental {
    ///
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    ///

    private String clientName;
    private String clientSurname;

    private LocalDateTime rentDateTime;
    private LocalDateTime returnDateTime;

    private Double price;

    @ManyToOne()
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Car car;
}
