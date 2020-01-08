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
@Table(name = "tbl_donation")
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantity", nullable = false)
    private Double quantity;

    @Column(name = "donated_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date donatedDate;

    @Column(name = "details", length = 1000)
    private String details;

    @ManyToOne(targetEntity = BloodGroup.class)
    @JoinColumn(name = "blood_group", referencedColumnName = "id", nullable = false)
    private BloodGroup group;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user", referencedColumnName = "id", nullable = false)
    private User user;

    @ManyToOne(targetEntity = Camp.class)
    @JoinColumn(name = "camp", referencedColumnName = "id", nullable = false)
    private Camp camp;

    public Donation(Double quantity, String details) {
        this.quantity = quantity;
        this.details = details;

    }
}
