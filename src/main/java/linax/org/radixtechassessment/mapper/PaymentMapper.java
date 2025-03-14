package linax.org.radixtechassessment.mapper;

import linax.org.radixtechassessment.dto.PaymentDto;
import linax.org.radixtechassessment.model.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PaymentMapper {
    PaymentDto toDto(Payment entity);
    Payment toEntity(PaymentDto dto);
    List<PaymentDto> toDtoList(List<Payment> entities);
}
