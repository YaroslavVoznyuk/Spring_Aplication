package com.danIT.HW.Spring_Aplication.HW4.domain;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "customers")
public class Customer extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private Integer age;

    @JsonManagedReference
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Account> accounts;

    @ManyToMany
    @JoinTable(name = "customer_employer",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "employer_id"))
    private List<Employer> employers;

    private String password;

    private String phoneNumber;

    public Customer(String name, String email, Integer age, String password, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.accounts = new ArrayList<>();
        this.employers = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }
}
