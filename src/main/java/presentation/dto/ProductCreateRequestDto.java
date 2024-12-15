package presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductCreateRequestDto(
        @NotBlank String name,
        @NotNull @Positive int price,
        @NotNull @Positive int quantity,
        String description
) {}
