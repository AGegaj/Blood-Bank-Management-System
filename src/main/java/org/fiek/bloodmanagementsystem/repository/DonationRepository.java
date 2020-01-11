package org.fiek.bloodmanagementsystem.repository;

import org.fiek.bloodmanagementsystem.entity.Donation;
import org.fiek.bloodmanagementsystem.entity.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DonationRepository extends JpaRepository<Donation, Long> {

    List<Donation> findAllByUserId(Long userId);


    @Query(value = "SELECT * FROM `blood-system`.tbl_donation d WHERE d.user = :userId ORDER BY d.donated_date DESC limit 1", nativeQuery = true)
    Donation findLastDonationByUser(@Param("userId") Long userId);

    List<Donation> findAll(Specification<Donation> spec);
}
