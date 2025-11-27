package org.example.hospital.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Data
@Entity
@Table(name = "billing_officers")
public class BillingOfficer extends User {
    @Column(name="room_num")
    private String roomNum;

    @OneToMany(mappedBy = "billingOfficer", cascade = CascadeType.ALL)
    private List<Bill> bills;
}
