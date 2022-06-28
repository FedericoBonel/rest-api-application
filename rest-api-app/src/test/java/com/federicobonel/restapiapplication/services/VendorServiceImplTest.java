package com.federicobonel.restapiapplication.services;

import com.federicobonel.restapiapplication.api.v1.mapper.VendorMapper;
import com.federicobonel.restapiapplication.api.v1.model.VendorDTO;
import com.federicobonel.restapiapplication.controllers.v1.VendorController;
import com.federicobonel.restapiapplication.model.Vendor;
import com.federicobonel.restapiapplication.repositories.VendorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class VendorServiceImplTest {

    public static final long ID = 1L;
    public static final String NAME = "Carrefour";
    public static final String VENDOR_URL = VendorController.BASE_URL_VENDOR + "/" + ID;
    final VendorMapper vendorMapper = VendorMapper.INSTANCE;
    Vendor vendor;
    List<Vendor> vendors;
    @Mock
    VendorRepository vendorRepository;
    VendorService vendorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        vendorService = new VendorServiceImpl(vendorRepository, vendorMapper);

        vendor = new Vendor();
        vendor.setId(ID);
        vendor.setName(NAME);

        vendors = List.of(new Vendor(), new Vendor(), new Vendor());
    }


    @Test
    void getAll() {
        when(vendorRepository.findAll()).thenReturn(vendors);

        List<VendorDTO> foundVendors = vendorService.getAll();

        assertEquals(vendors.size(), foundVendors.size());
        verify(vendorRepository).findAll();
    }

    @Test
    void getById() {
        when(vendorRepository.findById(ID)).thenReturn(Optional.of(vendor));

        VendorDTO foundVendor = vendorService.getById(ID);

        assertEquals(VENDOR_URL, foundVendor.getVendorUrl());
        verify(vendorRepository).findById(ID);
    }

    @Test
    void createVendor() {
        when(vendorRepository.save(any(Vendor.class))).thenReturn(vendor);

        VendorDTO savedCustomer = vendorService.createVendor(vendorMapper.vendorToVendorDTO(vendor));

        assertEquals(NAME, savedCustomer.getName());
        assertEquals(VENDOR_URL, savedCustomer.getVendorUrl());
    }

    @Test
    void updateVendor() {
        when(vendorRepository.save(any(Vendor.class))).thenReturn(vendor);

        VendorDTO savedCustomer = vendorService.updateVendor(ID, vendorMapper.vendorToVendorDTO(vendor));

        assertEquals(NAME, savedCustomer.getName());
        assertEquals(VENDOR_URL, savedCustomer.getVendorUrl());
    }

    @Test
    void patchVendor() {
        when(vendorRepository.findById(ID)).thenReturn(Optional.of(vendor));
        when(vendorRepository.save(any(Vendor.class))).thenReturn(vendor);

        VendorDTO savedCustomer = vendorService.patchVendor(ID, vendorMapper.vendorToVendorDTO(vendor));

        assertEquals(NAME, savedCustomer.getName());
        assertEquals(VENDOR_URL, savedCustomer.getVendorUrl());
    }

    @Test
    void deleteById() {
        vendorService.deleteById(ID);

        verify(vendorRepository).deleteById(ID);
    }
}