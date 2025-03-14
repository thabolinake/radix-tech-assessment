package linax.org.radixtechassessment.mapper;

import linax.org.radixtechassessment.dto.PaymentDto;
import linax.org.radixtechassessment.model.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PaymentMapper {
    PaymentDto toDto(Payment entity);
}
