package pl.sda.arppl4.rental.model.dto;

// Request  (chcemy wprowadzić zmianę)
// Response (gdy zwracamy odpowiedź na zapytanie)
// DTO      (gdy odsyłamy model obiektu (np. lista samochodów))

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentCarRequest {
    private String surnameOfTheClient;
    private Double hourlyPrice;
    private String nameOfTheClient;
}
