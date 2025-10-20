# Guía de MapStruct para PetCare

## Configuración Completada

✅ MapStruct 1.6.3 instalado
✅ Procesador de anotaciones configurado
✅ Binding con Lombok configurado (0.2.0)
✅ Compatible con Spring Boot 3.5.6 y Java 21

## Estructura Recomendada

```
src/main/java/edu/sena/petcare/
├── model/
│   └── User.java                    # Entidad JPA
├── dto/
│   └── UserDTO.java                 # DTO
└── mapper/
    └── UserMapper.java              # Mapper de MapStruct
```

## Ejemplos de Uso

### 1. Mapper Básico

#### Entidad (User.java)
```java
package edu.sena.petcare.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
}
```

#### DTO (UserDTO.java)
```java
package edu.sena.petcare.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String fullName; // Combinación de firstName + lastName
}
```

#### Mapper (UserMapper.java)
```java
package edu.sena.petcare.mapper;

import edu.sena.petcare.dto.UserDTO;
import edu.sena.petcare.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface UserMapper {
    
    @Mapping(target = "fullName", source = "user", qualifiedByName = "getFullName")
    UserDTO toDTO(User user);
    
    @Mapping(target = "firstName", ignore = true)
    @Mapping(target = "lastName", ignore = true)
    @Mapping(target = "password", ignore = true)
    User toEntity(UserDTO dto);
    
    @Named("getFullName")
    default String getFullName(User user) {
        return user.getFirstName() + " " + user.getLastName();
    }
}
```

### 2. Mapper con Relaciones

#### Entidades
```java
@Entity
@Data
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String species;
    private Integer age;
    
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;
}

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String username;
    
    @OneToMany(mappedBy = "owner")
    private List<Pet> pets;
}
```

#### DTOs
```java
@Data
public class PetDTO {
    private Long id;
    private String name;
    private String species;
    private Integer age;
    private String ownerName; // Solo el nombre del dueño
}

@Data
public class UserWithPetsDTO {
    private Long id;
    private String username;
    private List<PetDTO> pets;
}
```

#### Mapper
```java
@Mapper(componentModel = "spring")
public interface PetMapper {
    
    @Mapping(target = "ownerName", source = "owner.username")
    PetDTO toDTO(Pet pet);
    
    @Mapping(target = "owner", ignore = true)
    Pet toEntity(PetDTO dto);
    
    List<PetDTO> toDTOList(List<Pet> pets);
}

@Mapper(componentModel = "spring", uses = PetMapper.class)
public interface UserMapper {
    
    UserWithPetsDTO toDTO(User user);
    
    @Mapping(target = "pets", ignore = true)
    User toEntity(UserWithPetsDTO dto);
}
```

### 3. Mapper con Expresiones Java

```java
@Mapper(componentModel = "spring", imports = {LocalDateTime.class})
public interface AppointmentMapper {
    
    @Mapping(target = "createdAt", expression = "java(LocalDateTime.now())")
    @Mapping(target = "status", constant = "PENDING")
    Appointment toEntity(AppointmentDTO dto);
    
    AppointmentDTO toDTO(Appointment appointment);
}
```

### 4. Mapper con Configuración Global

```java
@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.WARN,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface BaseMapper<E, D> {
    D toDTO(E entity);
    E toEntity(D dto);
    List<D> toDTOList(List<E> entities);
}
```

### 5. Mapper con Actualización de Entidades

```java
@Mapper(componentModel = "spring")
public interface UserMapper {
    
    UserDTO toDTO(User user);
    
    User toEntity(UserDTO dto);
    
    // Actualiza una entidad existente con datos del DTO
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    void updateEntityFromDTO(UserDTO dto, @MappingTarget User user);
}
```

## Uso en Servicios

```java
@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        return userMapper.toDTO(user);
    }
    
    public UserDTO createUser(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        User savedUser = userRepository.save(user);
        return userMapper.toDTO(savedUser);
    }
    
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        userMapper.updateEntityFromDTO(userDTO, user);
        User updatedUser = userRepository.save(user);
        return userMapper.toDTO(updatedUser);
    }
    
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }
}
```

## Características Avanzadas

### 1. Mapeo de Fechas

```java
@Mapper(componentModel = "spring")
public interface DateMapper {
    
    @Mapping(target = "createdDate", dateFormat = "dd-MM-yyyy HH:mm:ss")
    EntityDTO toDTO(Entity entity);
}
```

### 2. Mapeo de Enums

```java
public enum Status {
    ACTIVE, INACTIVE, PENDING
}

public enum StatusDTO {
    ACTIVO, INACTIVO, PENDIENTE
}

@Mapper(componentModel = "spring")
public interface StatusMapper {
    
    @ValueMapping(source = "ACTIVE", target = "ACTIVO")
    @ValueMapping(source = "INACTIVE", target = "INACTIVO")
    @ValueMapping(source = "PENDING", target = "PENDIENTE")
    StatusDTO toDTO(Status status);
}
```

### 3. Mapeo Condicional

```java
@Mapper(componentModel = "spring")
public interface ConditionalMapper {
    
    @Mapping(target = "email", source = "email", 
             conditionExpression = "java(user.getEmail() != null && !user.getEmail().isEmpty())")
    UserDTO toDTO(User user);
}
```

### 4. Mapeo con Decoradores

```java
@Mapper(componentModel = "spring")
@DecoratedWith(UserMapperDecorator.class)
public interface UserMapper {
    UserDTO toDTO(User user);
}

@Component
public abstract class UserMapperDecorator implements UserMapper {
    
    @Autowired
    private UserMapper delegate;
    
    @Override
    public UserDTO toDTO(User user) {
        UserDTO dto = delegate.toDTO(user);
        // Lógica adicional personalizada
        dto.setFullName(dto.getFullName().toUpperCase());
        return dto;
    }
}
```

## Integración con Lombok

MapStruct funciona perfectamente con Lombok gracias al binding configurado:

```java
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String username;
    private String email;
}

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private String email;
}

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = false))
public interface UserMapper {
    UserDTO toDTO(User user);
    User toEntity(UserDTO dto);
}
```

## Compilación y Generación

MapStruct genera las implementaciones en tiempo de compilación:

```bash
# Compilar y generar mappers
mvn clean compile

# Ver las clases generadas en:
# target/generated-sources/annotations/
```

## Clases Generadas

MapStruct genera automáticamente implementaciones como:

```java
// Generado automáticamente por MapStruct
@Component
public class UserMapperImpl implements UserMapper {
    
    @Override
    public UserDTO toDTO(User user) {
        if (user == null) {
            return null;
        }
        
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setFullName(getFullName(user));
        
        return userDTO;
    }
    
    // ... más métodos generados
}
```

## Ventajas de MapStruct

1. ✅ **Type-safe**: Errores en tiempo de compilación
2. ✅ **Rápido**: Sin reflexión, código generado
3. ✅ **Mantenible**: Código limpio y legible
4. ✅ **Flexible**: Personalización mediante métodos custom
5. ✅ **Integrado**: Funciona con Spring y Lombok
6. ✅ **Sin dependencias runtime**: Solo anotaciones

## Debugging

Para ver el código generado por MapStruct:

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-compiler-plugin</artifactId>
    <configuration>
        <annotationProcessorPaths>
            <!-- ... -->
        </annotationProcessorPaths>
        <compilerArgs>
            <arg>-Amapstruct.verbose=true</arg>
            <arg>-Amapstruct.defaultComponentModel=spring</arg>
        </compilerArgs>
    </configuration>
</plugin>
```

## Recursos Adicionales

- [Documentación Oficial MapStruct](https://mapstruct.org/)
- [MapStruct Reference Guide](https://mapstruct.org/documentation/stable/reference/html/)
- [MapStruct Examples](https://github.com/mapstruct/mapstruct-examples)
