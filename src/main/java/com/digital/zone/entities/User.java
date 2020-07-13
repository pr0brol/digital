package com.digital.zone.entities;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String firstName;

}
