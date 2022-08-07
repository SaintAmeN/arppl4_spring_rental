package pl.sda.arppl4.rental.controller;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.sda.arppl4.rental.Arppl4SpringRentalApplication;
import pl.sda.arppl4.rental.model.Car;
import pl.sda.arppl4.rental.model.CarBodyType;
import pl.sda.arppl4.rental.model.dto.CarDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Paweł Recław, AmeN
 * @project arppl4_spring_rental
 * @created 07.08.2022
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Arppl4SpringRentalApplication.class)
public class CarControllerTest {

    @LocalServerPort
    public int randomServerPort;

    @Test
    public void test_emptyListAfterStartDoesNotThrowErrorsAndReturnsStatusCodeOK() {
        TestRestTemplate testRestTemplate = new TestRestTemplate();
//        testRestTemplate.exchange("/api/car", HttpMethod.GET, HttpEntity.EMPTY, CarDTO.class)
        ResponseEntity<List<CarDTO>> responseEntity = testRestTemplate.exchange("http://localhost:" + randomServerPort + "/api/car", HttpMethod.GET, HttpEntity.EMPTY, new ParameterizedTypeReference<List<CarDTO>>() {
        });

        // weryfikacja status code
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // weryfikacja treści
        List<CarDTO> carDTOS = responseEntity.getBody();
        Assertions.assertEquals(0, carDTOS.size());
    }

    @Test
    public void test_addFirstObjectDoesNotThrowErrorsAndReturnsStatusCodeCreated() {
        TestRestTemplate testRestTemplate = new TestRestTemplate();

        ResponseEntity<Void> addResponseEntity = testRestTemplate.exchange(
                "http://localhost:" + randomServerPort + "/api/car",
                HttpMethod.POST,
                new HttpEntity<>(
                        Car.builder()
                                .name("Maluch")
                                .make("Fiat")
                                .bodyType(CarBodyType.CABRIO)
                                .seats(5)
                                .engineCapacity(0.666)
                                .build()
                ),
                Void.class);

        // weryfikacja status code
        Assertions.assertEquals(HttpStatus.CREATED, addResponseEntity.getStatusCode());

        ////////// Weryfikacja stanu bazy
        ResponseEntity<List<CarDTO>> responseEntity = testRestTemplate.exchange("http://localhost:" + randomServerPort + "/api/car", HttpMethod.GET, HttpEntity.EMPTY, new ParameterizedTypeReference<List<CarDTO>>() {
        });

        // weryfikacja status code
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // weryfikacja treści
        List<CarDTO> carDTOS = responseEntity.getBody();

        Assertions.assertEquals(1, carDTOS.size());
        CarDTO theOnlyElement = carDTOS.get(0);

        // DONE_TODO: wyciągnąć z listy ten 1 element i zwreryfikować że powinien mieć odpowiednią nazwę, make, body, skrzynię(podchwytliwe), seats, engine....
        Assertions.assertEquals("Maluch", theOnlyElement.getName());
        Assertions.assertEquals("Fiat", theOnlyElement.getMake());
        Assertions.assertEquals(CarBodyType.CABRIO, theOnlyElement.getBodyType());
        Assertions.assertEquals(5, theOnlyElement.getSeats());
        Assertions.assertEquals(0.666, theOnlyElement.getEngineCapacity());
        Assertions.assertNull(theOnlyElement.getCarGearBox());
        Assertions.assertNull(theOnlyElement.getProductionDate());
        Assertions.assertNotNull(theOnlyElement.getCarId());
        //////////
    }

    // TODO: stwórz nowy plik, skopiuj poprzedni test do nowego pliku, napisz następujący test:
    //          1. masz pustą listę, dodaj element (baza powinna mieć jeden element), potem usuń element, (baza nie powinna posiadać elementu)
    //              - masz pustą listę (opcjonalne)
    //              - dodaj element
    //              - pobierz listę
    //              - sprawdz czy element jest na liscie
    //              - usun element
    //              - pobierz liste
    //              - sprawdz czy element (nie) jest na liscie

    // TODO: stwórz kolejny plik z testami:
    //      - test 1 (nie można dodać car rental (wynajmu)) do nieistniejacego samochodu:
    //          - pobierz listę samochodów
    //          - wybierz ID KTÓRE NIE ISTNIEJE NA LIŚCIE ZWRÓCONYCH SAMOCHODÓW (ponieważ chcemy dodać nieistniejacy samochód)
    //          - wyślij zapytanie o wynajem samochodu i jako identyfikator samochodu wyślij NIEISTNIEJĄCY SAMOCHÓD
    //          - wynik zapytania (które się nie udało) powinno mieć odopwiedni status kod (4XX cośtam) - assert'uj że tak jest
    //      - test 2 można wynająć samochód:
    //          - pobierz listę samochodów
    //          - wybierz ID KTÓRE ISTNIEJE NA LIŚCIE ZWRÓCONYCH SAMOCHODÓW lub
    //              - jeśli lista jest pusta, to dodaj samochód
    //          - pobierz listę samochodów
    //          - wybierz ID KTÓRE ISTNIEJE NA LIŚCIE ZWRÓCONYCH SAMOCHODÓW (jeśli mimo dodania nie masz samochodu to powinna być asercja)
    //          - wyślij zapytanie o wynajem samochodu
    //          - wynik zapytania powinno mieć odopwiedni status - assert'uj że tak jest
    //      - test 3 nie można wynająć samochodu który już był wynajęty:
    //          - pobierz listę samochodów
    //          - wybierz ID KTÓRE ISTNIEJE NA LIŚCIE ZWRÓCONYCH SAMOCHODÓW lub
    //              - jeśli lista jest pusta, to dodaj samochód
    //          - pobierz listę samochodów
    //          - wybierz ID KTÓRE ISTNIEJE NA LIŚCIE ZWRÓCONYCH SAMOCHODÓW (jeśli mimo dodania nie masz samochodu to powinna być asercja)
    //          - wyślij zapytanie o wynajem samochodu
    //          - wynik zapytania powinno mieć odopwiedni status - assert'uj że tak jest
    //          - wyślij zapytanie o wynajem samochodu
    //          - wynik zapytania (które się nie udało) powinno mieć odopwiedni status kod (4XX cośtam) - assert'uj że tak jest
}
