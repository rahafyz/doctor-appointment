package com.blubank.doctorappointment.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Patient")
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Patient {

    @Id
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone_number")
    @NotNull
    private String phoneNumber;


    /*@Column(name = "username", unique = true)
    @NotNull
    private String userName;

    @Column(name = "password")
    @NotNull
    private String password;*/

}
