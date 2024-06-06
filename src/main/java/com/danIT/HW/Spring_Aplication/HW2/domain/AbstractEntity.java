package com.danIT.HW.Spring_Aplication.HW2.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public abstract class AbstractEntity {
    private Long id;
}
