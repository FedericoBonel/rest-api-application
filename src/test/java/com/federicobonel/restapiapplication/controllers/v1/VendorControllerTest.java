package com.federicobonel.restapiapplication.controllers.v1;

import com.federicobonel.restapiapplication.api.v1.mapper.VendorMapper;
import com.federicobonel.restapiapplication.api.v1.model.VendorDTO;
import com.federicobonel.restapiapplication.model.Vendor;
import com.federicobonel.restapiapplication.services.VendorService;
import com.federicobonel.restapiapplication.services.VendorServiceImpl;
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

class VendorControllerTest {

    public static final long ID = 1L;
    public static final String NAME = "Carrefour";
    public static final String VENDOR_URL = VendorController.BASE_URL_VENDOR + "/" + ID;

    VendorDTO vendor;
    List<VendorDTO> vendors;

    @Mock
    VendorService vendorService;

    @InjectMocks
    VendorController vendorController;

    VendorMapper vendorMapper;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        vendorMapper = VendorMapper.INSTANCE;
        vendor = new VendorDTO();
        vendor.setId(ID);
        vendor.setName(NAME);
        vendor.setVendorUrl(VENDOR_URL);
        vendors = List.of(new VendorDTO(), new VendorDTO(), new VendorDTO());

        mockMvc = MockMvcBuilders.standaloneSetup(vendorController).build();
    }

    @Test
    void getAllVendors() throws Exception {
        when(vendorService.getAll()).thenReturn(vendors);

        mockMvc.perform(get(VendorController.BASE_URL_VENDOR))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendors", hasSize(vendors.size())));
    }

    @Test
    void getById() throws Exception {
        when(vendorService.getById(ID)).thenReturn(vendor);

        mockMvc.perform(get(VENDOR_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(Math.toIntExact(ID))))
                .andExpect(jsonPath("$.name", equalTo(NAME)))
                .andExpect(jsonPath("$.vendor_url", equalTo(VENDOR_URL)));
    }

    @Test
    void createNewVendor() throws Exception {
        when(vendorService.createVendor(any(VendorDTO.class))).thenReturn(vendor);

        mockMvc.perform(post(VendorController.BASE_URL_VENDOR)
                        .content(ObjectToJson.convertToJson(vendor))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", equalTo(Math.toIntExact(ID))))
                .andExpect(jsonPath("$.name", equalTo(NAME)))
                .andExpect(jsonPath("$.vendor_url", equalTo(VENDOR_URL)));
    }

    @Test
    void updateVendor() throws Exception {
        when(vendorService.updateVendor(anyLong(), any(VendorDTO.class))).thenReturn(vendor);

        mockMvc.perform(put(VENDOR_URL)
                        .content(ObjectToJson.convertToJson(vendor))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(Math.toIntExact(ID))))
                .andExpect(jsonPath("$.name", equalTo(NAME)))
                .andExpect(jsonPath("$.vendor_url", equalTo(VENDOR_URL)));
    }

    @Test
    void patchVendor() throws Exception {
        when(vendorService.patchVendor(anyLong(), any(VendorDTO.class))).thenReturn(vendor);

        mockMvc.perform(patch(VENDOR_URL)
                        .content(ObjectToJson.convertToJson(vendor))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(Math.toIntExact(ID))))
                .andExpect(jsonPath("$.name", equalTo(NAME)))
                .andExpect(jsonPath("$.vendor_url", equalTo(VENDOR_URL)));
    }

    @Test
    void deleteVendor() throws Exception {
        mockMvc.perform(delete(VENDOR_URL))
                .andExpect(status().isOk());

        verify(vendorService).deleteById(ID);
    }
}