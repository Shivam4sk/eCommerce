package org.softoHiest.eCommerce.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.softoHiest.eCommerce.dto.requestDto.CartItemRequest;
import org.softoHiest.eCommerce.model.CartItem;
import org.softoHiest.eCommerce.model.Product;
import org.softoHiest.eCommerce.model.User;
import org.softoHiest.eCommerce.repository.CartItemRepository;
import org.softoHiest.eCommerce.repository.ProductRepository;
import org.softoHiest.eCommerce.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CartItemService {

    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public boolean addToCart(String userId, CartItemRequest cartItemRequest) {
        // find product
        Optional<Product> productOpt = productRepository.findById(cartItemRequest.getProductId());
        if(productOpt.isEmpty())
            return false;

        Product product = productOpt.get();

        if(product.getStockQuantity() < cartItemRequest.getQuantity())
            return false;

        Optional<User> userOpt = userRepository.findById(Long.valueOf(userId));
        if(userOpt.isEmpty())
            return false;

        User user = userOpt.get();

        CartItem existingCartItem = cartItemRepository.findByUserAndProduct(user,product);
        if(existingCartItem != null){
            //update the quantity
            existingCartItem.setQuantity(existingCartItem.getQuantity() + cartItemRequest.getQuantity());
            existingCartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(existingCartItem.getQuantity())));
            cartItemRepository.save(existingCartItem);
        }else {
            //create new cart item
            CartItem cartItem = new CartItem();
            cartItem.setUser(user);
            cartItem.setProduct(product);
            cartItem.setQuantity(cartItemRequest.getQuantity());
            cartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(cartItemRequest.getQuantity())));
            cartItemRepository.save(cartItem);
        }
        return true;
    }

    public Boolean deleteItemFromCart(String userId, Long productId) {

        Optional<Product> productOpt = productRepository.findById(productId);
        Optional<User> userOpt = userRepository.findById(Long.valueOf(userId));

        if(productOpt.isPresent() && userOpt.isPresent()){
            cartItemRepository.deleteByUserAndProduct(userOpt.get(), productOpt.get());
            return true;
        }
        return false;
    }

    public List<CartItem> getCart(String userId) {
       return userRepository.findById(Long.valueOf(userId))
               .map(cartItemRepository::findByUser)
               .orElseGet(List::of);
    }
}
