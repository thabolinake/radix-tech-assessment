package linax.org.radixtechassessment.mapper;

import linax.org.radixtechassessment.dto.LoanDto;
import linax.org.radixtechassessment.model.Loan;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface LoanMapper {
    LoanDto toDto(Loan loan);
    Loan toEntity(LoanDto loanDto);
    List<LoanDto> toListDto(List<Loan> loans);
}
