package org.softoHiest.eCommerce.service;

import lombok.RequiredArgsConstructor;
import org.softoHiest.eCommerce.dto.responseDto.ProductResponseDto;
import org.softoHiest.eCommerce.dto.requestDto.PrductRequestDto;
import org.softoHiest.eCommerce.model.Product;
import org.softoHiest.eCommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductResponseDto createProduct(PrductRequestDto prductRequestDto) {
        Product product = new Product();
        updateProductFromRequest(product,prductRequestDto);
        Product saveProduct = productRepository.save(product);
        return mapToProductResponse(saveProduct);
    }

    private ProductResponseDto mapToProductResponse(Product saveProduct) {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(saveProduct.getId());
        productResponseDto.setName(saveProduct.getName());
        productResponseDto.setActive(saveProduct.getActive());
        productResponseDto.setCategory(saveProduct.getCategory());
        productResponseDto.setDescription(saveProduct.getDescription());
        productResponseDto.setPrice(saveProduct.getPrice());
        productResponseDto.setImageUrl(saveProduct.getImageUrl());
        productResponseDto.setStockQuantity(saveProduct.getStockQuantity());
        return productResponseDto;
    }

    private void updateProductFromRequest(Product product, PrductRequestDto prductRequestDto) {
        product.setName(prductRequestDto.getName());
        product.setCategory(prductRequestDto.getCategory());
        product.setDescription(prductRequestDto.getDescription());
        product.setPrice(prductRequestDto.getPrice());
        product.setImageUrl(prductRequestDto.getImageUrl());
        product.setStockQuantity(prductRequestDto.getStockQuantity());
    }

    public Optional<ProductResponseDto> updateProduct(Long id, PrductRequestDto prductRequestDto) {
        return productRepository.findById(id)
                .map(existingProduct ->{
                    updateProductFromRequest(existingProduct,prductRequestDto);
                    Product saveProduct = productRepository.save(existingProduct);
                    return mapToProductResponse(saveProduct);
                });
    }

    public List<ProductResponseDto> getAllProducts() {
        return productRepository.findByActiveTrue()
                .stream()
                .map(this::mapToProductResponse)
                .toList();
    }

    public Optional<ProductResponseDto> fetchProductById(Long id) {
        return productRepository.findById(id)
                .map(this::mapToProductResponse);
    }

    public void deleteProduct(Long id) {
        Product product =  productRepository.findById(id)
                .orElseThrow(()-> new RuntimeException(("Product not found.")));
                product.setActive(false);
                productRepository.save(product);

    }

    public List<ProductResponseDto> searchProducts(String keyword) {
        return productRepository.searchProduct(keyword)
                .stream()
                .map(this::mapToProductResponse)
                .collect(Collectors.toList());
    }
}
