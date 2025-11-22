package edu.sena.petcare.utility;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public final class ListaMappeo {

    //Por defecto Java agrega un cosntructor, por lo que para evitar instancias de esta clase, creamos un constructor privado que lanza una excepcion
    private ListaMappeo() {
        throw new IllegalStateException("Clase de utilidad, imposible instanciar");
    }

    public static <E, D> List<D> toDtoList(
            List<E> entidades,
            Function<E, D> funcionDeMappeo) {

        if (entidades == null) {
            return Collections.emptyList();
        }

        return entidades.stream()
                .map(funcionDeMappeo)
                .toList();
    }
}
