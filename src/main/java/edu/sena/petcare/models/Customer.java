package edu.sena.petcare.models;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = { "pets", "wishlists", "paymentMethods", "addresses" })
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Table(name = "customer")
public class Customer extends User {

    // relacion OneToMany con Pet
    @OneToMany(mappedBy = "user")
    List<Pet> pets;

    // relacion OneToMany con Wishlist
    @OneToMany(mappedBy = "user")
    List<Wishlist> wishlists;

    // relacion OneToMany con MethodPaymentCustomer
    @OneToMany(mappedBy = "user")
    List<MethodPaymentCustomer> paymentMethods;

    // relacion OneToMany con Address
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Address> addresses;

}
