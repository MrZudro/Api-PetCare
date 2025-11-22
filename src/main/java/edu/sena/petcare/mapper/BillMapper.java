package edu.sena.petcare.mapper;

import org.springframework.stereotype.Component;

import edu.sena.petcare.dto.bill.BillNewUpdateDTO;
import edu.sena.petcare.dto.bill.BillReadDTO;
import edu.sena.petcare.models.Bill;

@Component
public class BillMapper {

    public BillReadDTO toReadDTO(Bill bill) {
        if (bill == null) {
            return null;
        }
        BillReadDTO dto = new BillReadDTO();
        dto.setId(bill.getId());
        dto.setResolution(bill.getResolution());
        dto.setPrefix(bill.getPrefix());
        dto.setConsecutive(bill.getConsecutive());
        dto.setCufe(bill.getCufe());
        dto.setCreateDate(bill.getCreateDate());
        dto.setDateValidationDian(bill.getDateValidationDian());
        dto.setSubtotal(bill.getSubtotal());
        dto.setTaxes(bill.getTaxes());
        dto.setTotalBill(bill.getTotalBill());
        dto.setDianState(bill.getDianState());
        return dto;
    }

    public Bill toEntity(BillNewUpdateDTO dto) {
        if (dto == null) {
            return null;
        }
        Bill bill = new Bill();
        bill.setResolution(dto.getResolution());
        bill.setPrefix(dto.getPrefix());
        bill.setConsecutive(dto.getConsecutive());
        bill.setSubtotal(dto.getSubtotal());
        bill.setTaxes(dto.getTaxes());
        bill.setTotalBill(dto.getTotalBill());
        bill.setDianState(dto.getDianState());
        return bill;
    }

    public void updateEntity(BillNewUpdateDTO dto, Bill bill) {
        if (dto == null || bill == null) {
            return;
        }
        bill.setResolution(dto.getResolution());
        bill.setPrefix(dto.getPrefix());
        bill.setConsecutive(dto.getConsecutive());
        bill.setSubtotal(dto.getSubtotal());
        bill.setTaxes(dto.getTaxes());
        bill.setTotalBill(dto.getTotalBill());
        bill.setDianState(dto.getDianState());
    }
}
