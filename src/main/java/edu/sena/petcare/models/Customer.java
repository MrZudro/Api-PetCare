package edu.sena.petcare.models;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"pets", "wishlists", "paymentMethods"})
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Table(name = "customer")
public class Customer extends User{

    //relacion OneToMany con Pet
    @OneToMany(mappedBy = "user")
    private List<Pet> pets;

    //relacion OneToMany con Wishlist
    @OneToMany(mappedBy = "user")
    private List<Wishlist> wishlists;

    //relacion OneToMany con MethodPaymentCustomer
    @OneToMany(mappedBy = "user")
    private List<MethodPaymentCustomer> paymentMethods;

}
