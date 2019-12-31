package org.fiek.bloodmanagementsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_request")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "required_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date requiredDate;

    @ManyToOne(targetEntity = BloodGroup.class)
    @JoinColumn(name = "blood_group", referencedColumnName = "id", nullable = false)
    private BloodGroup group;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user", referencedColumnName = "id", nullable = false)
    private User user;
}
