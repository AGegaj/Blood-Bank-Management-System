package org.fiek.bloodmanagementsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_user_details")
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "weigh", nullable = false)
    private Double weigh;

    @Column(name = "personal_number", length = 45, nullable = false, unique = true)
    private String personalNumber;

    @Column(name = "birthday", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date birthday;

    @Column(name = "country", length = 45, nullable = false)
    private String country;

    @Column(name = "city", length = 45, nullable = false)
    private String city;

    @OneToOne(targetEntity = User.class, mappedBy = "userDetails", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User user;

}
