package org.fiek.bloodmanagementsystem.repository;

import org.fiek.bloodmanagementsystem.entity.Camp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CampRepository extends JpaRepository<Camp, Long> {

}
