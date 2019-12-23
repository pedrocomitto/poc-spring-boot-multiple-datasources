package com.pedrocomitto.poc.multipledatasources.second.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "SECOND_DATABASE_ENTITY")
@NoArgsConstructor
@AllArgsConstructor
public class SecondDatabaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String param;
}
