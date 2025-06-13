package com.springboot.controller;

import com.springboot.domain.Book;
import com.springboot.domain.Cart;
import com.springboot.domain.CartItem;
import com.springboot.exception.BookIdException;
import com.springboot.service.BookService;
import com.springboot.service.CartService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private BookService bookService;

    @GetMapping
    public String RequestCartId(HttpServletRequest request){
        String sessionid;
        sessionid = request.getSession(true).getId();
        // getSession(true) : 세션아이디가 없으면 자동 생성
        request.getSession(true).setAttribute("cartId",sessionid);
        System.out.println("aaaa  "+sessionid);
        return "redirect:/cart/" + sessionid;
        
        // request.getSession(true).setAttribute("cartId") : 장바구니 id를 가져온다
    }

    @PostMapping
    public @ResponseBody Cart create(@RequestBody Cart cart){
        System.out.println("bbb  ");
        return cartService.create(cart);
    }

    @GetMapping("{cartId}")
    public String requestCartList(@PathVariable(value = "cartId") String cartId, Model model){
        System.out.println("ccc  ");
        Cart cart = cartService.read(cartId);
        model.addAttribute("cart",cart);
        return "cart";
    }

    @PutMapping("/{cartId}")
    public @ResponseBody Cart read(@PathVariable(value = "cartId") String cartId){
        System.out.println("dddd ");
        return cartService.read(cartId);
    }

    @PutMapping("/book/{bookId}")
    @ResponseStatus(value= HttpStatus.NO_CONTENT)
    public void addCartByNewItem(@PathVariable("bookId") String bookId, HttpServletRequest request){
        String seeionId = request.getSession(true).getId();
        Cart cart = cartService.read(seeionId);
        if(cart == null) cart = cartService.create(new Cart(seeionId));

        Book book = bookService.getBookById(bookId);
        if(book == null) throw new IllegalArgumentException(new BookIdException(bookId));

        cart.addCartItem(new CartItem(book));
        cartService.update(seeionId,cart);
        }

    @DeleteMapping("/book/{bookId}")
    @ResponseStatus(value= HttpStatus.NO_CONTENT)
    public void removeCartByNewItem(@PathVariable("bookId") String bookId, HttpServletRequest request){
        String seeionId = request.getSession(true).getId();
        Cart cart = cartService.read(seeionId);
        if(cart == null) cart = cartService.create(new Cart(seeionId));

        Book book = bookService.getBookById(bookId);
        if(book == null) throw new IllegalArgumentException(new BookIdException(bookId));

        cart.removeCartItem(new CartItem(book));
        cartService.update(seeionId,cart);
    }

    @DeleteMapping("/{cartId}")
    @ResponseStatus(value= HttpStatus.NO_CONTENT)
    public void deleteCartList(@PathVariable("cartId") String cartId){
        cartService.delete(cartId);
    }

    }



