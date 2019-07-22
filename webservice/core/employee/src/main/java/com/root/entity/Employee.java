package com.root.entity;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "employees")
@NoArgsConstructor
public class Employee implements Serializable {
    private static final long serialVersionUID = 8742514980247859277L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @OneToOne
    @JoinColumn(name = "salary_id", referencedColumnName = "id")
    private Salary salary;

    public Employee(final String name, final Salary salary){
        this.name = name;
        this.salary = salary;
    }

}
