package com.blubank.doctorappointment.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Appointment")
@Data
public class Appointment {

    @Id
    private Long id;


}
