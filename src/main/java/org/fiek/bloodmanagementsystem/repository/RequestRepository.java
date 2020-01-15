package org.fiek.bloodmanagementsystem.repository;

import org.fiek.bloodmanagementsystem.entity.Request;
import org.fiek.bloodmanagementsystem.type.RequestStatus;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findAllByUserId(Long userId);

    List<Request> findAllByGroupId(Long groupId);

    List<Request> findAll(Specification<Request> spec);

    @Query("SELECT r FROM Request r WHERE r.user.id = :userId and r.status = :status")
    List<Request> findAllByUserIdandStatus(@Param("userId") Long userId, @Param("status") RequestStatus status);

}
