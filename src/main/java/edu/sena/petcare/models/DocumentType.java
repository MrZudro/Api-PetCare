package edu.sena.petcare.models;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString(exclude = "users")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "document_type")
public class DocumentType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    @EqualsAndHashCode.Include
    private String name;

    @Column(nullable = false, unique = true, length = 7)
    @EqualsAndHashCode.Include
    private String abreviation;

    // relacion ManyToOne con User
    @OneToMany(mappedBy = "documentType", fetch = FetchType.LAZY)
    private List<User> users;

}
