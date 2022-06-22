package com.federicobonel.restapiapplication.repositories;

import com.federicobonel.restapiapplication.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
}
