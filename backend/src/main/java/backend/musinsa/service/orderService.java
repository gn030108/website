package backend.musinsa.service;


import backend.musinsa.domain.cart.CartItem;
import backend.musinsa.repository.CartItemRepository;
import backend.musinsa.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class orderService {

    private final OrderRepository orderRepository;
    private final CartItemRepository cartItemRepository;


    //장바구니에 담겨있는 상품 주문기능.

    public void orderItem(){
        //1. 해당 회원 조회한뒤 회원의 장바구니 조회.
        //2. 장바구니 조회후 상품 구매 절차.
        //3. 구매 과정이 없는데 나는..? 회원정보에도 딱히 돈관련정보는없는데 ㅋㅋ

        

    }

}
