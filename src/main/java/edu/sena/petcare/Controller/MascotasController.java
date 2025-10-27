package edu.sena.petcare.Controller;

import edu.sena.petcare.models.Mascotas;
import edu.sena.petcare.repositories.MascotasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mascotas")
public class MascotasController {

    @Autowired
    private MascotasRepository mascotaRepository;


    @PostMapping
    public Mascotas crearMascota(@RequestBody Mascotas mascota) {
        return mascotaRepository.save(mascota);
    }

    @GetMapping
    public List<Mascotas> listarMascotas() {
        return mascotaRepository.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Mascotas> obtenerMascota(@PathVariable Long id) {
        Optional<Mascotas> mascota = mascotaRepository.findById(id);
        return mascota.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mascotas> editarMascota(@PathVariable Long id, @RequestBody Mascotas detalles) {
        Optional<Mascotas> mascotaExistente = mascotaRepository.findById(id);

        if (mascotaExistente.isPresent()) {
            Mascotas mascota = mascotaExistente.get();
            mascota.setNombre(detalles.getNombre());
            mascota.setRaza(detalles.getRaza());
            mascota.setEdad(detalles.getEdad());
            mascota.setActivo(detalles.isActivo());
            return ResponseEntity.ok(mascotaRepository.save(mascota));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}/desactivar")
    public ResponseEntity<Mascotas> desactivarMascota(@PathVariable Long id) {
        Optional<Mascotas> mascotaExistente = mascotaRepository.findById(id);

        if (mascotaExistente.isPresent()) {
            Mascotas mascota = mascotaExistente.get();
            mascota.setActivo(false);
            return ResponseEntity.ok(mascotaRepository.save(mascota));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

