package com.safetynet.safetynetalerts.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@SuperBuilder
@Setter
@Getter
@NoArgsConstructor
public class PersonFullName {

    private String firstName;
    private String lastName;
}
