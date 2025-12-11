package edu.sena.petcare.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;
import edu.sena.petcare.models.enums.AddressType;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = { "customer", "neighborhood" })
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "address")
@SQLDelete(sql = "UPDATE address SET is_active = false WHERE id = ?")
@FilterDef(name = "activeAddressesFilter", parameters = @ParamDef(name = "isActive", type = Boolean.class))
@Filter(name = "activeAddressesFilter", condition = "is_active = :isActive")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "address_line", length = 255, nullable = false)
    private String addressLine;

    @Column(name = "additional_info", length = 100)
    private String additionalInfo;

    @Column(name = "delivery_notes", length = 500)
    private String deliveryNotes;

    @Enumerated(EnumType.STRING)
    @Column(name = "address_type", nullable = false)
    private AddressType addressType;

    @Column(name = "is_default", nullable = false)
    private Boolean isDefault = false;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @Column(name = "created_date", nullable = false)
    private Instant createdDate = Instant.now();

    @Column(name = "updated_date", nullable = false)
    private Instant updatedDate = Instant.now();

    // ---------------- RELACIONES -----------------

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "neighborhood_id", nullable = false)
    private Neighborhood neighborhood;
}
