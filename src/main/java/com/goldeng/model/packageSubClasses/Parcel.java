package com.goldeng.model.packageSubClasses;

import com.goldeng.model.Package;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "parcels")
@Entity
@PrimaryKeyJoinColumn(name = "parcelId")
public class Parcel extends Package {
    
    private double weight;
}
