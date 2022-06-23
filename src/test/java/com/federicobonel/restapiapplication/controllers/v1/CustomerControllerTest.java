package com.federicobonel.restapiapplication.controllers.v1;

import com.federicobonel.restapiapplication.api.v1.model.CustomerDTO;
import com.federicobonel.restapiapplication.services.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CustomerControllerTest {

    public static final long ID = 1L;
    public static final String NAME = "Federico";
    public static final String LASTNAME = "Bonel";
    public static final String CUSTOMER_URL = CustomerController.BASE_URL_CUSTOMERS + "/" + ID;

    CustomerDTO customer;
    List<CustomerDTO> customers;

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();

        customer = new CustomerDTO();
        customer.setName(NAME);
        customer.setLastname(LASTNAME);
        customer.setCustomerUrl(CUSTOMER_URL);

        customers = List.of(new CustomerDTO(), new CustomerDTO(), new CustomerDTO());
    }


    @Test
    void getAll() throws Exception {
        when(customerService.getAll()).thenReturn(customers);

        mockMvc.perform(get(CustomerController.BASE_URL_CUSTOMERS))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(customers.size())));

        verify(customerService).getAll();
    }

    @Test
    void findById() throws Exception {
        when(customerService.getById(ID)).thenReturn(customer);

        mockMvc.perform(get(CUSTOMER_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customer_url", equalTo(CUSTOMER_URL)));

        verify(customerService).getById(ID);
    }

    @Test
    void createNewCustomer() throws Exception {
        when(customerService.createCustomer(any(CustomerDTO.class))).thenReturn(customer);

        mockMvc.perform(post(CustomerController.BASE_URL_CUSTOMERS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ObjectToJson.convertToJson(customer)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo(NAME)))
                .andExpect(jsonPath("$.customer_url", equalTo(CUSTOMER_URL)));

        verify(customerService).createCustomer(any(CustomerDTO.class));
    }

    @Test
    void updateCustomer() throws Exception {
        when(customerService.updateCustomer(anyLong(), any(CustomerDTO.class))).thenReturn(customer);

        mockMvc.perform(put(CUSTOMER_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ObjectToJson.convertToJson(customer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME)))
                .andExpect(jsonPath("$.lastname", equalTo(LASTNAME)))
                .andExpect(jsonPath("$.customer_url", equalTo(CUSTOMER_URL)));

        verify(customerService).updateCustomer(anyLong(), any(CustomerDTO.class));
    }

    @Test
    void patchCustomer() throws Exception {
        when(customerService.patchCustomer(anyLong(), any(CustomerDTO.class))).thenReturn(customer);

        mockMvc.perform(patch(CUSTOMER_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ObjectToJson.convertToJson(customer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME)))
                .andExpect(jsonPath("$.lastname", equalTo(LASTNAME)))
                .andExpect(jsonPath("$.customer_url", equalTo(CUSTOMER_URL)));

        verify(customerService).patchCustomer(anyLong(), any(CustomerDTO.class));
    }

    @Test
    void deleteCustomer() throws Exception {

        mockMvc.perform(delete(CUSTOMER_URL))
                .andExpect(status().isOk());

        verify(customerService).deleteCustomerById(ID);
    }
}
