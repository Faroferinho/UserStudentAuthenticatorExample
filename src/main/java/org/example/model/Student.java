package org.example.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document
@Getter @Setter
public class Student {
    @Id
    private String email;
    private String name;
    private BigDecimal firstGrade;
    private BigDecimal secondGrade;
    private BigDecimal average;
}
