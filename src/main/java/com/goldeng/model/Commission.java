package com.goldeng.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.goldeng.model.enums.Status;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "commissions")
public class Commission {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commissionId;

    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDate date; 

    private float price;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private Receiver receiver;

    @OneToMany(mappedBy = "commission", fetch = FetchType.LAZY)
    private List<Package> packages = new ArrayList<>();
}
