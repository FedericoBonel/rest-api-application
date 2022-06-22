package com.federicobonel.restapiapplication.api.v1.mapper;

import com.federicobonel.restapiapplication.api.v1.model.VendorDTO;
import com.federicobonel.restapiapplication.model.Customer;
import com.federicobonel.restapiapplication.model.Vendor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VendorMapperTest {

    public static final long ID = 1L;
    public static final String NAME = "Carrefour";

    Vendor vendor;
    VendorDTO vendorDTO;

    VendorMapper mapper = VendorMapper.INSTANCE;

    @BeforeEach
    void setUp() {
        vendor = new Vendor();
        vendor.setId(ID);
        vendor.setName(NAME);

        vendorDTO = new VendorDTO();
        vendorDTO.setId(ID);
        vendorDTO.setName(NAME);
    }

    @Test
    void vendorToVendorDTO() {
        VendorDTO convertedVendor = mapper.vendorToVendorDTO(vendor);

        assertEquals(ID, convertedVendor.getId());
        assertEquals(NAME, convertedVendor.getName());
    }

    @Test
    void vendorDTOToVendor() {
        Vendor convertedVendor = mapper.vendorDTOToVendor(vendorDTO);

        assertEquals(ID, convertedVendor.getId());
        assertEquals(NAME, convertedVendor.getName());
    }
}