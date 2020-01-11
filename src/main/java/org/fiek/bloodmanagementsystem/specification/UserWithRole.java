package org.fiek.bloodmanagementsystem.specification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.fiek.bloodmanagementsystem.entity.Role;
import org.fiek.bloodmanagementsystem.entity.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserWithRole implements Specification<User> {
    Role role;
    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        if (role == null) {
            return null;
        }
        return criteriaBuilder.equal(root.get("role"), this.role );
    }
}
