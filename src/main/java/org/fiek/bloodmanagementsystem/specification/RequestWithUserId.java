package org.fiek.bloodmanagementsystem.specification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.fiek.bloodmanagementsystem.entity.Request;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestWithUserId implements Specification<Request> {
    Long userId;
    @Override
    public Predicate toPredicate(Root<Request> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        if (userId == null) {
            return null;
        }
        return criteriaBuilder.equal(root.get("user"), userId);
    }
}
