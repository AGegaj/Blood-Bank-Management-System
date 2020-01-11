package org.fiek.bloodmanagementsystem.specification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.fiek.bloodmanagementsystem.entity.Request;
import org.fiek.bloodmanagementsystem.entity.User;
import org.fiek.bloodmanagementsystem.type.RequestStatus;
import org.fiek.bloodmanagementsystem.type.Status;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestWithStatus implements Specification<Request> {
    RequestStatus status;
    @Override
    public Predicate toPredicate(Root<Request> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        if (status == null) {
            return null;
        }
        return criteriaBuilder.equal(root.get("status"), status);
    }
}
