package org.fiek.bloodmanagementsystem.repository;

import org.fiek.bloodmanagementsystem.entity.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Long> {

    List<Donation> findAllByUserId(Long userId);
}
