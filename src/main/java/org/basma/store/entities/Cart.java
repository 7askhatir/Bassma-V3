package org.basma.store.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.basma.store.shared.dto.CartDto;
import org.hibernate.annotations.CreationTimestamp;

import com.sun.istack.NotNull;

@Entity
@Table(name="cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name="userId" ,referencedColumnName = "userId")
    private @NotNull UserEntity userId;
   
    @Column(name = "product_id")
    private @NotBlank long productId;

    @Column(name = "created_date")
    @CreationTimestamp
    private Date createdDate;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "idProduct", insertable = false, updatable = false)
    private ProductEntity product;

    

    private int quantity;

    public Cart() {
    }


    public Cart(CartDto cartDto, ProductEntity product,UserEntity userId){
        this.userId = userId;
        this.productId = cartDto.getProduct().getIdProduct();
        this.quantity = cartDto.getQuantity();
        this.product = product;
    }

    public Cart(@NotBlank UserEntity userId, @NotBlank Long productId, int quantity) {
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public Cart(CartDto cartDto, ProductEntity product) {
        this.productId = cartDto.getProduct().getIdProduct();
        this.quantity = cartDto.getQuantity();
        this.product = product;
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
