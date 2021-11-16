package org.basma.store.shared.dto;

import org.basma.store.entities.Cart;
import org.basma.store.entities.ProductEntity;
import org.basma.store.entities.UserEntity;

import com.sun.istack.NotNull;

public class CartDto {
    private long id;
    private @NotNull UserEntity userId;
    private @NotNull int quantity;
    private @NotNull ProductEntity product;

    public CartDto() {
    }

    public CartDto(Cart cart) {
        this.setId(cart.getId());
        this.setUserId(cart.getUserId());
        this.setQuantity(cart.getQuantity());
        this.setProduct(cart.getProduct());
    }

    @Override
    public String toString() {
        return "CartDto{" +
                "id=" + id +
                ", userId=" + userId +
                ", quantity=" + quantity +
                ", productName=" + product.getTitleProduct() +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserEntity getUserId() {
        return userId;
    }

    public void setUserId(UserEntity userId) {
        this.userId = userId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

}
