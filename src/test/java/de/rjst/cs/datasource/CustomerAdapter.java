package de.rjst.cs.datasource;

import de.rjst.cs.api.CustomerEndpoints;
import de.rjst.cs.api.model.CreateCustomerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.client.RestTestClient;

@RequiredArgsConstructor
@Service
public class CustomerAdapter {

    private final RestTestClient restTestClient;

    public RestTestClient.ResponseSpec getCustomers() {
        return restTestClient.get()
                             .uri(CustomerEndpoints.CUSTOMERS)
                             .accept(MediaType.APPLICATION_JSON)
                             .exchange();
    }

    public RestTestClient.ResponseSpec getCustomerById(final Long id) {
        return restTestClient.get()
                             .uri(CustomerEndpoints.CUSTOMERS, id)
                             .accept(MediaType.APPLICATION_JSON)
                             .exchange();
    }

    public RestTestClient.ResponseSpec postCustomers(final CreateCustomerDto createCustomerDto) {
        return restTestClient.post()
                             .uri(CustomerEndpoints.CUSTOMERS)
                             .contentType(MediaType.APPLICATION_JSON)
                             .body(createCustomerDto)
                             .accept(MediaType.APPLICATION_JSON)
                             .exchange();
    }

}
