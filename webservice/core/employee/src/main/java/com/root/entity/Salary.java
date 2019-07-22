package com.root.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "salary")
@NoArgsConstructor
public class Salary implements Serializable {
    private static final long serialVersionUID = -7365302440771994598L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer summary;

    public Salary(final Integer summary){
        this.summary = summary;
    }
}
