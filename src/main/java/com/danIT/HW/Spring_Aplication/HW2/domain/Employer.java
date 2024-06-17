package com.danIT.HW.Spring_Aplication.HW2.domain;

import com.danIT.HW.Spring_Aplication.HW3.domain.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name="employers")
public class Employer extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;
}
