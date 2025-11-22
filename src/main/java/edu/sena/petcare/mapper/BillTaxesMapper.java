package edu.sena.petcare.mapper;

import org.springframework.stereotype.Component;

import edu.sena.petcare.dto.billtaxes.BillTaxesNewUpdateDTO;
import edu.sena.petcare.dto.billtaxes.BillTaxesReadDTO;
import edu.sena.petcare.models.BillTaxes;

@Component
public class BillTaxesMapper {

    public BillTaxesReadDTO toDto(BillTaxes taxes) {
        if (taxes == null) {
            return null;
        }
        BillTaxesReadDTO dto = new BillTaxesReadDTO();
        dto.setId(taxes.getId());
        dto.setTaxCode(taxes.getTaxCode());
        dto.setNameTax(taxes.getNameTax());
        dto.setTaxBase(taxes.getTaxBase());
        dto.setPercentage(taxes.getPercentage());
        dto.setTaxValue(taxes.getTaxValue());
        return dto;
    }

    public BillTaxes toEntity(BillTaxesNewUpdateDTO dto) {
        if (dto == null) {
            return null;
        }
        BillTaxes taxes = new BillTaxes();
        taxes.setTaxCode(dto.getTaxCode());
        taxes.setNameTax(dto.getNameTax());
        taxes.setTaxBase(dto.getTaxBase());
        taxes.setPercentage(dto.getPercentage());
        taxes.setTaxValue(dto.getTaxValue());
        return taxes;
    }

    public void updateEntity(BillTaxesNewUpdateDTO dto, BillTaxes taxes) {
        if (dto == null || taxes == null) {
            return;
        }
        taxes.setTaxCode(dto.getTaxCode());
        taxes.setNameTax(dto.getNameTax());
        taxes.setTaxBase(dto.getTaxBase());
        taxes.setPercentage(dto.getPercentage());
        taxes.setTaxValue(dto.getTaxValue());
    }
}
