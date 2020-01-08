package org.fiek.bloodmanagementsystem.repository;

import org.fiek.bloodmanagementsystem.entity.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationRepository extends JpaRepository<Donation, Long> {
}
