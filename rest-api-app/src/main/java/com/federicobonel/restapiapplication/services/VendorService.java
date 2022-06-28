package com.federicobonel.restapiapplication.services;

import com.federicobonel.restapiapplication.api.v1.model.VendorDTO;

import java.util.List;

public interface VendorService {

    List<VendorDTO> getAll();

    VendorDTO getById(Long id);

    VendorDTO createVendor(VendorDTO vendorDTO);

    VendorDTO updateVendor(Long id, VendorDTO vendorDTO);

    VendorDTO patchVendor(Long id, VendorDTO vendorDTO);

    void deleteById(Long id);
}
