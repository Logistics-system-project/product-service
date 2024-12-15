package application.service;

import domain.entity.Product;
import domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import presentation.dto.ProductCreateRequestDto;
import presentation.dto.ProductResponseDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    // 상품 생성
    public ProductResponseDto createProduct(ProductCreateRequestDto requestDto) {
        Product product = new Product(
                requestDto.name(),
                requestDto.price(),
                requestDto.quantity(),
                requestDto.description()
        );
        productRepository.save(product);
        return ProductResponseDto.from(product);
    }

    // 모든 상품 조회
    public List<ProductResponseDto> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(ProductResponseDto::from)
                .collect(Collectors.toList());
    }

    // 특정 상품 조회
    public ProductResponseDto getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found: " + id));
        return ProductResponseDto.from(product);
    }

    // 상품 수정
    public ProductResponseDto updateProduct(Long id, ProductCreateRequestDto requestDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found: " + id));
        product.update(
                requestDto.name(),
                requestDto.price(),
                requestDto.quantity(),
                requestDto.description()
        );
        return ProductResponseDto.from(product);
    }

    // 상품 삭제
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found: " + id));
        productRepository.delete(product);
    }
}
