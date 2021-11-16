package org.basma.store.repositories;

import java.util.List;

import org.basma.store.entities.Cart;
import org.basma.store.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findAllByUserIdOrderByCreatedDateDesc(Integer userId);
    boolean existsByUserIdAndProductId(UserEntity userId, long productId);
    Cart findByUserIdAndProductId(UserEntity userId, long productId);
    Cart findByid(long id);
    boolean existsById(long id);
    List<Cart> findAllByUserIdOrderByCreatedDateDesc(String id);
}
