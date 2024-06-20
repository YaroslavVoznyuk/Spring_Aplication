package com.danIT.HW.Spring_Aplication.HW4.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

import static jakarta.persistence.TemporalType.TIMESTAMP;

@Data
@Getter
@Setter
public abstract class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @LastModifiedDate
    @Temporal(TIMESTAMP)
    @Column(name = "lastModifiedDate")
    protected Date lastModifiedDate;

    @CreatedDate
    @Temporal(TIMESTAMP)
    @Column(name = "creationDate")
    protected Date creationDate;


}
