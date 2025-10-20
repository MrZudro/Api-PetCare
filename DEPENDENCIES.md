# Dependencias del Proyecto PetCare

## Versiones Instaladas

### JWT (JSON Web Token)
- **Versión**: 0.12.6 (JJWT)
- **Librería**: io.jsonwebtoken
- **Estado**: ✅ Última versión estable
- **Módulos incluidos**:
  - `jjwt-api`: API principal
  - `jjwt-impl`: Implementación (runtime)
  - `jjwt-jackson`: Integración con Jackson (runtime)

### MapStruct
- **Versión**: 1.6.3
- **Estado**: ✅ Última versión estable
- **Módulos incluidos**:
  - `mapstruct`: Librería principal
  - `mapstruct-processor`: Procesador de anotaciones
  - `lombok-mapstruct-binding`: Binding para compatibilidad con Lombok (v0.2.0)

## Compatibilidad Verificada

### Spring Boot
- **Versión**: 3.5.6
- ✅ Compatible con JWT 0.12.6
- ✅ Compatible con MapStruct 1.6.3

### Spring Security
- **Versión**: 6.5.5 (gestionada por Spring Boot)
- ✅ Compatible con JWT 0.12.6
- ✅ Sin conflictos de dependencias

### Java
- **Versión**: 21
- ✅ Compatible con todas las dependencias

### Otras Dependencias
- **Lombok**: 1.18.40 (gestionada por Spring Boot)
  - ✅ Compatible con MapStruct mediante `lombok-mapstruct-binding`
- **Jackson**: 2.19.2 (gestionada por Spring Boot)
  - ✅ Compatible con JWT mediante `jjwt-jackson`
- **MySQL Connector**: 9.4.0
- **Hibernate**: 6.6.29.Final

## Configuración del Compilador

El proyecto está configurado con los siguientes procesadores de anotaciones:
1. **Lombok** (v1.18.40)
2. **MapStruct Processor** (v1.6.3)
3. **Lombok-MapStruct Binding** (v0.2.0) - Para compatibilidad entre ambos

## Uso de JWT

### Ejemplo básico de generación de token:
```java
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;

public class JwtUtil {
    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 24 horas
                .signWith(SECRET_KEY)
                .compact();
    }
    
    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
```

## Uso de MapStruct

### Ejemplo básico de mapper:
```java
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDTO(User user);
    User toEntity(UserDTO dto);
}
```

## Verificación de Instalación

Para verificar que todas las dependencias se han descargado correctamente:
```bash
mvn clean install
```

Para ver el árbol de dependencias:
```bash
mvn dependency:tree
```

## Notas Importantes

1. **JWT 0.12.6** es la versión más reciente y estable, con soporte completo para Spring Security 6.x
2. **MapStruct 1.6.3** es compatible con Java 21 y Spring Boot 3.x
3. El binding `lombok-mapstruct-binding` asegura que Lombok y MapStruct trabajen correctamente juntos
4. Todas las dependencias han sido verificadas sin conflictos
5. La configuración del procesador de anotaciones está optimizada para el orden correcto de procesamiento

## Recursos Adicionales

- [Documentación JJWT](https://github.com/jwtk/jjwt)
- [Documentación MapStruct](https://mapstruct.org/)
- [Spring Security + JWT Guide](https://spring.io/guides/tutorials/spring-boot-oauth2/)
