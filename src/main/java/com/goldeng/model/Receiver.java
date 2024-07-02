package com.goldeng.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "receivers")
@AttributeOverride(name = "personId", column = @Column(name = "receiver_id"))
public class Receiver extends Person {
    
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate date;

    @Column(name = "opening_hour")
    private LocalTime openingHour;

    @Column(name = "closing_hour")
    private LocalTime closingHour;

    @OneToMany(mappedBy = "receiver", fetch = FetchType.LAZY)
    private List<Commission> receivedCommissions;
}
