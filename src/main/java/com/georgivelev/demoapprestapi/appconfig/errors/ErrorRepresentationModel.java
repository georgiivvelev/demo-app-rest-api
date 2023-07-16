package com.georgivelev.demoapprestapi.appconfig.errors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorRepresentationModel {
    private Date date;
    private String message;
}
