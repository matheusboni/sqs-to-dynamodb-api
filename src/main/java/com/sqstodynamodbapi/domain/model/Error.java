package com.sqstodynamodbapi.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Error<T> {

    private T code;
    private String message;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String cause;
}
