package org.fiek.bloodmanagementsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_blood_bank")
public class BloodBank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = BloodGroup.class)
    @JoinColumn(name = "blood_group", referencedColumnName = "id", nullable = false)
    private BloodGroup group;

    @Column(name = "quantity", nullable = false)
    private Double quantity;
}
