package com.safetynet.safetynetalerts.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "medicalrecords")
public class MedicalRecord {

    @Id
    @GeneratedValue
    private int id;

    private String firstName;

    private String lastName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/mm/yyyy", timezone = "CET")
    private Date birthdate;

    @ElementCollection
    private List<String> medications;

    @ElementCollection
    private List<String> allergies;


}
