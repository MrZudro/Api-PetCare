package edu.sena.petcare.mapper;

import org.springframework.stereotype.Component;

import edu.sena.petcare.dto.billdetail.BillDetailNewUpdateDTO;
import edu.sena.petcare.dto.billdetail.BillDetailReadDTO;
import edu.sena.petcare.models.BillDetail;

@Component
public class BillDetailMapper {

    public BillDetailReadDTO toReadDTO(BillDetail detail) {
        if (detail == null) {
            return null;
        }
        BillDetailReadDTO dto = new BillDetailReadDTO();
        dto.setId(detail.getId());
        dto.setAmount(detail.getAmount());
        dto.setUnitPrice(detail.getUnitPrice());
        dto.setSubtotalLine(detail.getSubtotalLine());
        return dto;
    }

    public BillDetail toEntity(BillDetailNewUpdateDTO dto) {
        if (dto == null) {
            return null;
        }
        BillDetail detail = new BillDetail();
        detail.setAmount(dto.getAmount());
        detail.setUnitPrice(dto.getUnitPrice());
        detail.setSubtotalLine(dto.getSubtotalLine());
        return detail;
    }

    public void updateEntity(BillDetailNewUpdateDTO dto, BillDetail detail) {
        if (dto == null || detail == null) {
            return;
        }
        detail.setAmount(dto.getAmount());
        detail.setUnitPrice(dto.getUnitPrice());
        detail.setSubtotalLine(dto.getSubtotalLine());
    }
}
