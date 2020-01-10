package org.fiek.bloodmanagementsystem.repository;

import org.fiek.bloodmanagementsystem.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findAllByUserId(Long userId);

    List<Request> findAllByGroupId(Long groupId);
}
