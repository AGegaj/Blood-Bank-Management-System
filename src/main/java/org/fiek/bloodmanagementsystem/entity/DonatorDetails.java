package org.fiek.bloodmanagementsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.fiek.bloodmanagementsystem.type.Gender;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_donator_details")
public class DonatorDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "weigh", nullable = false)
    private Double weigh;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", columnDefinition = "ENUM('MALE','FEMALE')", nullable = false)
    private Gender gender;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "frequency", nullable = false)
    private Integer frequency = 0;

    @OneToOne(targetEntity = User.class, mappedBy = "donatorDetails", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User user;

    public DonatorDetails(Double weigh, Gender gender, Integer age) {
        this.weigh = weigh;
        this.gender = gender;
        this.age = age;
    }
}
