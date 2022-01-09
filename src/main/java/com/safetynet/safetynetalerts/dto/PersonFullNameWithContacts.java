package com.safetynet.safetynetalerts.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode
@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
public class PersonFullNameWithContacts extends PersonFullName {

    private String address;
    private String phone;
}
