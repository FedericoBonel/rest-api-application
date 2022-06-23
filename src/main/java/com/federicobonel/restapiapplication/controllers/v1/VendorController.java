package com.federicobonel.restapiapplication.controllers.v1;

import com.federicobonel.restapiapplication.api.v1.model.VendorDTO;
import com.federicobonel.restapiapplication.api.v1.model.VendorListDTO;
import com.federicobonel.restapiapplication.config.SwaggerConfig;
import com.federicobonel.restapiapplication.services.VendorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Api(tags = {SwaggerConfig.VENDOR_TAG})
@RestController
@RequestMapping(VendorController.BASE_URL_VENDOR)
public class VendorController {

    public static final String BASE_URL_VENDOR = "/api/v1/vendors";

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @ApiOperation(value = "Gets all vendors")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public VendorListDTO getAllVendors() {
        log.info("Getting all vendors");

        return new VendorListDTO(vendorService.getAll());
    }

    @ApiOperation(value = "Gets the vendor with the given ID",
            notes = "If no such vendor is found, it will return a 404 code")
    @GetMapping("/{vendorId}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO getById(@PathVariable Long vendorId) {
        log.info("Getting vendor with id " + vendorId);

        return vendorService.getById(vendorId);
    }

    @ApiOperation(value = "Creates new vendor")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VendorDTO createNewVendor(@RequestBody VendorDTO vendorDTO) {
        log.info("Creating new vendor");

        return vendorService.createVendor(vendorDTO);
    }

    @ApiOperation(value = "Replaces all the information of the given vendor",
            notes = "If any field is not specified in the request payload, it will be set to a 'null' value")
    @PutMapping("/{vendorId}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO updateVendor(@PathVariable Long vendorId, @RequestBody VendorDTO vendorDTO) {
        log.info("Updating vendor with id " + vendorId);

        return vendorService.updateVendor(vendorId, vendorDTO);
    }

    @ApiOperation(value = "Updates the vendor's fields",
            notes = "Not specified fields are not going to change")
    @PatchMapping("/{vendorId}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO patchVendor(@PathVariable Long vendorId, @RequestBody VendorDTO vendorDTO) {
        log.info("Patching vendor with id " + vendorId);

        return vendorService.patchVendor(vendorId, vendorDTO);
    }

    @ApiOperation(value = "Deletes the vendor with the given ID",
            notes = "If the vendor is not present it will return a 404 code")
    @DeleteMapping("/{vendorId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteVendor(@PathVariable Long vendorId) {
        log.info("Deleting vendor with id " + vendorId);

        vendorService.deleteById(vendorId);
    }
}
