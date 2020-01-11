package org.fiek.bloodmanagementsystem.specification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.fiek.bloodmanagementsystem.entity.Donation;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DonationWithCity implements Specification<Donation> {
    String city;
    @Override
    public Predicate toPredicate(Root<Donation> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        if (city == null) {
            return null;
        }
        return criteriaBuilder.like(root.get("camp").get("city"), "%"+ this.city +"%");    }
}
