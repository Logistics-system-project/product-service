package presentation.dto;

import domain.entity.Product;

import java.time.LocalDateTime;

public record ProductResponseDto(
        Long id,
        String name,
        int price,
        int quantity,
        String description,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static ProductResponseDto from(Product product) {
        return new ProductResponseDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getQuantity(),
                product.getDescription(),
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }
}
