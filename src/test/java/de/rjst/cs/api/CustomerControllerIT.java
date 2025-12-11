package de.rjst.cs.api;

import de.rjst.cs.TestcontainersConfiguration;
import de.rjst.cs.api.model.CreateCustomerDto;
import de.rjst.cs.datasource.CustomerAdapter;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestcontainersConfiguration.class)
class CustomerControllerIT {

    @Autowired
    private CustomerAdapter customerAdapter;

    @Test
    void getCustomers() {
        final var response = customerAdapter.getCustomers();
        response.expectStatus()
                .isOk();
    }

    @Test
    void getCustomerById() {
        final var response = customerAdapter.getCustomerById(1L);
        response.expectStatus()
                .isOk();
    }

    @Test
    void getCustomerById_notFound() {
        final var response = customerAdapter.getCustomerById(Long.MAX_VALUE);
        response.expectStatus()
                .isOk();
    }

    @Test
    void createCustomer() {
        final var createCustomerDto = new CreateCustomerDto();
        createCustomerDto.setFirstName("John");
        createCustomerDto.setLastName("Doe");
        createCustomerDto.setBirthDate(LocalDate.of(1990, 1, 1));
        createCustomerDto.setEmail("<EMAIL>");

        final var response = customerAdapter.postCustomers(createCustomerDto);
        response.expectStatus()
                .isCreated();
    }

    @Test
    void createCustomer_badRequest() {
        final var createCustomerDto = new CreateCustomerDto();
        createCustomerDto.setFirstName("");
        createCustomerDto.setLastName("Doe");
        createCustomerDto.setBirthDate(LocalDate.of(1990, 1, 1));
        createCustomerDto.setEmail("<EMAIL>");

        final var response = customerAdapter.postCustomers(createCustomerDto);
        response.expectStatus()
                .isBadRequest();
    }


}
