package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "contained_in")
    private Integer containedIn;

    @Nullable
    @Enumerated(EnumType.STRING)
    private Color color;

    @OneToOne
    @JoinColumn(name = "contained_in", referencedColumnName = "id", insertable = false, updatable = false)
    private Box box;
}
