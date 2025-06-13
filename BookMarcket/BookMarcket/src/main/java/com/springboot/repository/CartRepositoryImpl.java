package com.springboot.repository;

import com.springboot.domain.Cart;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CartRepositoryImpl implements CartRepository {

    private Map<String,Cart> listOfCarts;

    public CartRepositoryImpl(){
        listOfCarts = new HashMap<String,Cart>();
    }


    @Override
    public Cart create(Cart cart) {
        if(listOfCarts.keySet().contains(cart.getCartId())){
            throw new IllegalArgumentException(String.format("장바구니를 생성할 수 없습니다. 장바구니 id(%s)가 존재합니다", cart.getCartId()));
        }
        listOfCarts.put(cart.getCartId(),cart);
        return cart;
    }

    @Override
    public Cart read(String cartId) {
        return listOfCarts.get(cartId);
    }

    @Override
    public void update(String cartId, Cart cart) {
        if(!listOfCarts.keySet().contains(cartId)){
            throw new IllegalArgumentException(String.format("장바구니를 갱신할 수 없습니다. 장바구니 id(%s)가 존재하지않습니다", cartId));
        }
        listOfCarts.put(cartId,cart);
        System.out.println("장바구니  "+cart);
    }

    @Override
    public void delete(String cartId) {
        if(!listOfCarts.keySet().contains(cartId)){
            throw new IllegalArgumentException(String.format("장바구니를 삭제할 수 없습니다. 장바구니 id(%s)가 존재하지않습니다", cartId));
        }
        listOfCarts.remove(cartId);
    }


}
