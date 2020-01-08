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
@Table(name = "tbl_camp")
public class Camp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "camp_title", length = 500, nullable = false)
    private String campTitle;

    @Column(name = "state", length = 45, nullable = false)
    private String state;

    @Column(name = "city", length = 45, nullable = false)
    private String city;

    @Column(name = "details", length = 1000)
    private String details;

    @Column(name = "img", length = 1000)
    private String img;

    @OneToMany(targetEntity = Donation.class, mappedBy = "camp", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Donation> donationList;

    public Camp(String campTitle, String state, String city, String details, String img) {
        this.campTitle = campTitle;
        this.state = state;
        this.city = city;
        this.details = details;
        this.img = img;
    }
}
