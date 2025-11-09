package org.example.hospital.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;


@Data
@Entity
@Table(name = "billing_officers")
public class BillingOfficer extends User {

    private String roomNum;

    @OneToMany(mappedBy = "billingOfficer", cascade = CascadeType.ALL)
    private List<Bill> bills;
}
