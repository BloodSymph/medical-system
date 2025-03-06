package com.company.auth.exception.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@ToString
public class ErrorEntity {

    @NotNull(message = "Status code shod not contains null value!")
    private Integer statusCode;

    @NotNull(message = "Error message shod not contains null value!")
    @NotBlank(message = "Error message shod not be empty!")
    @Length(max = 120, message = "Error message field shod be max: {max} of characters!")
    private String errorMessage;

    @DateTimeFormat(pattern = "E, dd MMM yyyy HH:mm:ss z")
    private Date errorTimeStamp;

}
