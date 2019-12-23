package com.pedrocomitto.poc.multipledatasources.first.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "FIRST_DATABASE_ENTITY")
@NoArgsConstructor
@AllArgsConstructor
public class FirstDatabaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String param;
}
