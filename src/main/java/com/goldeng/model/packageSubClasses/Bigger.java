package com.goldeng.model.packageSubClasses;

import com.goldeng.model.Package;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@SuperBuilder
@Table(name = "biggers_packages")
@Entity
@PrimaryKeyJoinColumn(name = "parcelId")
public class Bigger extends Package {
    
    private double width;

    private double height;
}
