package org.fiek.bloodmanagementsystem.repository;

import org.fiek.bloodmanagementsystem.entity.BloodBank;
import org.fiek.bloodmanagementsystem.entity.BloodGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BloodBankRepository extends JpaRepository<BloodBank, Long> {

    Optional<BloodBank> findByGroup(BloodGroup group);
}
