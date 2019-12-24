package org.fiek.bloodmanagementsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_blood_group")
public class BloodGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 3, nullable = false, unique = true)
    private String name;

    @OneToMany(targetEntity = Donation.class, mappedBy = "group", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Donation> donations;

    @OneToMany(targetEntity = BloodBank.class, mappedBy = "group", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<BloodBank> bloodBankList;

    @OneToMany(targetEntity = Request.class, mappedBy = "group", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Request> requests;
}
