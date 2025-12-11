package edu.sena.petcare.services.neighborhood;

import java.util.List;

import edu.sena.petcare.dto.neighborhood.NeighborhoodReadDTO;

public interface NeighborhoodService {
    List<NeighborhoodReadDTO> getAll();

    NeighborhoodReadDTO getById(Long id);

    List<NeighborhoodReadDTO> getByLocalityId(Long localityId);

    List<NeighborhoodReadDTO> getByLocalityIdSorted(Long localityId);
}
