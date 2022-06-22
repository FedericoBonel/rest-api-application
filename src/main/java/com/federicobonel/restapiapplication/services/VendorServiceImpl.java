package com.federicobonel.restapiapplication.services;

import com.federicobonel.restapiapplication.api.v1.mapper.VendorMapper;
import com.federicobonel.restapiapplication.api.v1.model.VendorDTO;
import com.federicobonel.restapiapplication.controllers.v1.VendorController;
import com.federicobonel.restapiapplication.exceptions.ResourceNotFoundException;
import com.federicobonel.restapiapplication.model.Vendor;
import com.federicobonel.restapiapplication.repositories.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;
    private final VendorMapper mapper;

    public VendorServiceImpl(VendorRepository vendorRepository, VendorMapper mapper) {
        this.vendorRepository = vendorRepository;
        this.mapper = mapper;
    }

    @Override
    public List<VendorDTO> getAll() {
        return vendorRepository.findAll()
                .stream()
                .map(mapper::vendorToVendorDTO)
                .peek(vendorDTO -> vendorDTO.setVendorUrl(generateUrlForId(vendorDTO.getId())))
                .collect(Collectors.toList());
    }

    @Override
    public VendorDTO getById(Long id) {
        return vendorRepository.findById(id)
                .map(vendor -> {
                    VendorDTO vendorDTO = mapper.vendorToVendorDTO(vendor);
                    vendorDTO.setVendorUrl(generateUrlForId(vendor.getId()));
                    return vendorDTO;
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public VendorDTO createVendor(VendorDTO vendorDTO) {
        vendorDTO.setId(null);
        return saveVendor(vendorDTO);
    }

    @Override
    public VendorDTO updateVendor(Long id, VendorDTO vendorDTO) {
        vendorDTO.setId(id);
        return saveVendor(vendorDTO);
    }

    private VendorDTO saveVendor(VendorDTO vendorDTO) {
        Vendor savedVendor = vendorRepository.save(mapper.vendorDTOToVendor(vendorDTO));
        VendorDTO vendorToReturn = mapper.vendorToVendorDTO(savedVendor);
        vendorToReturn.setVendorUrl(generateUrlForId(savedVendor.getId()));

        return vendorToReturn;
    }

    @Override
    public VendorDTO patchVendor(Long id, VendorDTO vendorToSave) {
        return vendorRepository.findById(id)
                .map(vendorInDB -> {
                    if (vendorToSave.getName() != null) {
                        vendorInDB.setName(vendorToSave.getName());
                    }

                    VendorDTO savedVendor = mapper.vendorToVendorDTO(vendorRepository.save(vendorInDB));
                    savedVendor.setVendorUrl(generateUrlForId(savedVendor.getId()));
                    return savedVendor;
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteById(Long id) {
        vendorRepository.deleteById(id);
    }

    private String generateUrlForId(Long id) {
        return VendorController.BASE_URL_VENDOR + "/" + id;
    }
}
