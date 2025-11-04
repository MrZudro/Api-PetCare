package edu.sena.petcare.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import edu.sena.petcare.dto.bill.BillReadDTO;
import edu.sena.petcare.dto.bill.BillDetailReadDTO;
import edu.sena.petcare.dto.bill.BillNewDTO;
import edu.sena.petcare.models.Bill;
import edu.sena.petcare.models.BillDetail;
import edu.sena.petcare.models.Product; // Necesitas importar Product

import java.util.List;

@Mapper(uses = {BillDetailMapper.class}) // Usar el mapper de detalle
public interface BillMapper {

    BillMapper mapper = Mappers.getMapper(BillMapper.class);

    // 1. Mapeo de Entidad a DTO de Lectura para el historial
    @Mapping(target = "veterinaryClinicName", source = "veterinaryClinic.name") // Asumiendo un campo 'name' en VeterinaryClinic
    @Mapping(target = "methodPaymentName", source = "metodoCliente.name") // Asumiendo un campo 'name' en MethodPaymentCustomer
    @Mapping(target = "details", source = "billDetails")
    BillReadDTO toReadDto(Bill entity);
    
    List<BillReadDTO> toReadDtoList(List<Bill> entities);

    // 2. Mapeo de DTO de Solicitud a Entidad (para CREAR)
    // Los campos complejos (dates, totals, DIAN data) se manejan en el servicio.
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "veterinaryClinic", ignore = true) 
    @Mapping(target = "metodoCliente", ignore = true)
    @Mapping(target = "billDetails", ignore = true) // Se crean y asocian en el servicio
    @Mapping(target = "billTaxes", ignore = true)
    @Mapping(target = "billTransactions", ignore = true)
    @Mapping(target = "empleado", ignore = true)
    @Mapping(target = "resolution", ignore = true)
    @Mapping(target = "prefix", ignore = true)
    @Mapping(target = "consecutive", ignore = true)
    @Mapping(target = "cufe", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "dateValidationDian", ignore = true)
    @Mapping(target = "subtotal", ignore = true)
    @Mapping(target = "taxes", ignore = true)
    @Mapping(target = "totalBill", ignore = true)
    @Mapping(target = "dianState", ignore = true)
    Bill toEntity(BillNewDTO dto);
}

@Mapper
interface BillDetailMapper {

    // Mapeo de Entidad a DTO de Lectura para los detalles
    @Mapping(target = "productName", source = "product.name") // Asumiendo un campo 'name' en Product
    BillDetailReadDTO toReadDto(BillDetail entity);
    
    // 2. Mapeo de DTO de Solicitud a Entidad (se realiza principalmente en el servicio)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "bill", ignore = true)
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "taxes", ignore = true)
    @Mapping(target = "unitPrice", ignore = true)
    @Mapping(target = "subtotalLine", ignore = true)
    BillDetail toEntity(BillDetailNewDTO dto);
}