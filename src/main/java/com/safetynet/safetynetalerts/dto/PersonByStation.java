package com.safetynet.safetynetalerts.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class PersonByStation {

    private List<PersonFullNameWithContacts> persons;
    private int numberOfAdults;
    private int numberOfChildren;
}
