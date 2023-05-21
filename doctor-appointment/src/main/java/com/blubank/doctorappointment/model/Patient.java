package com.blubank.doctorappointment.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Patient")
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Patient {

    @Id
    private Long id;

    @Column
    private String name;

    @Column(name = "phone_number")
    @NotNull
    private String phoneNumber;

    @OneToMany
    @JoinColumn(name = "appointment_list")
    private List<Appointment> appointmentList;

    @Column(name = "created_date")
    @CreatedDate
    private Date createdDate;

    @Column(name = "created_by")
    @CreatedBy
    private String createdBy;

    @Column(name = "modified_date")
    @LastModifiedDate
    private Date modifiedDate;

    @Column(name = "modified_by")
    @LastModifiedBy
    private String modifiedBy;

}
