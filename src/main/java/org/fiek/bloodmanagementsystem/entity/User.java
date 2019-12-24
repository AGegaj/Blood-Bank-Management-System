package org.fiek.bloodmanagementsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.fiek.bloodmanagementsystem.type.Status;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", length = 65, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 65, nullable = false)
    private String lastName;

    @Column(name = "username", length = 45, nullable = false, unique = true)
    private String username;

    @Column(name = "email", length = 45, nullable = false, unique = true)
    private String email;

    @Column(name = "password", length = 1024, nullable = false)
    private String password;

    @Column(name = "created_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "ENUM('ACTIVE','INACTIVE','DELETED')", nullable = false)
    private Status status;

    @Column(name = "image", length = 100)
    private String image;

    @ManyToOne(targetEntity = Role.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
    private Role role;

    @Column(name = "country", length = 45, nullable = false)
    private String country;

    @Column(name = "city", length = 45, nullable = false)
    private String city;

    @Column(name = "personal_number", length = 45, nullable = false, unique = true)
    private String personalNumber;

    @OneToOne(targetEntity = DonatorDetails.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "donator_details_id", referencedColumnName = "id")
    private DonatorDetails donatorDetails;

    @OneToMany(targetEntity = Donation.class, mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Donation> donationList;

    @OneToMany(targetEntity = Request.class, mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Request> requests;

    public User(String firstName, String lastName, String  username, String email, String image, String country, String city, String personalNumber){
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.image = image;
        this.country = country;
        this.city = city;
        this.personalNumber = personalNumber;
    }
}
