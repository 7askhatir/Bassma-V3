package org.basma.store.shared.dto;

import org.basma.store.entities.Cart;
import org.basma.store.entities.UserEntity;

import com.sun.istack.NotNull;

public class AddToCartDto {
	private long id;
    private UserEntity userId;
    private @NotNull long productId;
    private @NotNull int quantity;

    public AddToCartDto() {
    }

    public AddToCartDto(long id, @NotNull UserEntity userId, @NotNull long productId, @NotNull int quantity) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public AddToCartDto(Cart cart) {
        this.setId(cart.getId());
        this.setProductId(cart.getProductId());
        this.setUserId(cart.getUserId());
        this.setQuantity(cart.getQuantity());
    }

    @Override
    public String toString() {
        return "CartDto{" +
                "id=" + id +
                ", userId=" + userId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ",";
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

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
