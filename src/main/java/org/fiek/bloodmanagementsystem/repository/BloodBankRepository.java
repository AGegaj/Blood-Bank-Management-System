package org.fiek.bloodmanagementsystem.repository;

import org.fiek.bloodmanagementsystem.entity.BloodBank;
import org.fiek.bloodmanagementsystem.entity.BloodGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BloodBankRepository extends JpaRepository<BloodBank, Long> {

    Optional<BloodBank> findByGroup(BloodGroup group);

    List<BloodBank> findByGroup(int groupId);

    @Query("SELECT sum(b.quantity) FROM BloodBank b ")
    Double getSum();
}
