package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
public class Item {

    @Id
    private Integer id;

    @Column(name = "contained_in")
    private Integer contained;

    @Nullable
    private String color;

    @OneToOne
    @JoinColumn(name = "contained_in", referencedColumnName = "id", insertable = false, updatable = false)
    private Box box;
}
