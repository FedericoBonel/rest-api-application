package com.federicobonel.restapiapplication.controllers.v1;

import com.federicobonel.restapiapplication.api.v1.model.VendorDTO;
import com.federicobonel.restapiapplication.api.v1.model.VendorListDTO;
import com.federicobonel.restapiapplication.services.VendorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(VendorController.BASE_URL_VENDOR)
public class VendorController {

    public static final String BASE_URL_VENDOR = "/api/v1/vendors";

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public VendorListDTO getAllVendors() {
        log.info("Getting all vendors");

        return new VendorListDTO(vendorService.getAll());
    }

    @GetMapping("/{vendorId}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO getById(@PathVariable Long vendorId) {
        log.info("Getting vendor with id " + vendorId);

        return vendorService.getById(vendorId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VendorDTO createNewVendor(@RequestBody VendorDTO vendorDTO) {
        log.info("Creating new vendor");

        return vendorService.createVendor(vendorDTO);
    }

    @PutMapping("/{vendorId}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO updateVendor(@PathVariable Long vendorId, @RequestBody VendorDTO vendorDTO) {
        log.info("Updating vendor with id " + vendorId);

        return vendorService.updateVendor(vendorId, vendorDTO);
    }

    @PatchMapping("/{vendorId}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO patchVendor(@PathVariable Long vendorId, @RequestBody VendorDTO vendorDTO) {
        log.info("Patching vendor with id " + vendorId);

        return vendorService.patchVendor(vendorId, vendorDTO);
    }

    @DeleteMapping("/{vendorId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteVendor(@PathVariable Long vendorId){
        log.info("Deleting vendor with id " + vendorId);

        vendorService.deleteById(vendorId);
    }
}
