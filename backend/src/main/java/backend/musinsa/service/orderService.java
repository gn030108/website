package backend.musinsa.service;

import backend.musinsa.config.SecurityUtil;
import backend.musinsa.domain.cart.CartDto;
import backend.musinsa.domain.cart.CartItem;
import backend.musinsa.domain.coupon.Coupon;
import backend.musinsa.domain.coupon.CouponDto;
import backend.musinsa.domain.member.Member;
import backend.musinsa.domain.order.MemberOrder;
import backend.musinsa.domain.order.OrderItem;
import backend.musinsa.domain.order.OrderItemDto;
import backend.musinsa.domain.order.OrderState;
import backend.musinsa.repository.CartItemRepository;
import backend.musinsa.repository.MemberRepository;
import backend.musinsa.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class orderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;

    //장바구니에 담겨있는 상품 주문기능.
    public void orderItem(OrderItemDto orderItemDto){

        Member member = memberRepository.findByMemberId(SecurityUtil.getCurrentUsername()).orElseThrow();

        CartItem cart = member.getCart();

        List<Long> orderItemIdList = orderItemDto.getOrderItemIdList();

        List<OrderItem> orderItemList = member.getCart().getOrderItemList();

        List<OrderItem> memberCheckdOrderItemList = new ArrayList<>();

        for (Long itemId : orderItemIdList) {
            for (OrderItem orderItem : orderItemList) {
                if(orderItem.getId().equals(itemId)){
                    memberCheckdOrderItemList.add(orderItem);
                    orderItemList.remove(orderItem);
                }
            }
        }
        Coupon memberCheckCoupon = null;
        //회원의 체크박스에 체크한 상품들을 모아서 새로운 목록을 만듬. 이게 MemberOrder.
        List<Coupon> couponList = member.getCouponList();
        for (Coupon coupon : couponList) {
            if(coupon.getId().equals(Long.parseLong(orderItemDto.getCouponId()))){
                memberCheckCoupon = coupon;
                break;
            }
        }
        int payment = memberCheckdOrderItemList.stream().mapToInt(OrderItem::getPrice).sum();
        if(memberCheckCoupon != null){
            MemberOrder memberOrder = MemberOrder.builder()
                    .quantity(memberCheckdOrderItemList.size())
                    .deliveryAddress(member.getMemberInfo().getAddress())
                    .savedAmount(memberCheckCoupon.getCouponInfo().getDiscountValue())
                    .paymentAmount(payment)
                    .totalAmount(payment - memberCheckCoupon.getCouponInfo().getDiscountValue())
                    .member(member)
                    .cartItem(cart)
                    .order_state(OrderState.ORDER)
                    .build();
            orderRepository.save(memberOrder);
        }else{
            MemberOrder memberOrder = MemberOrder.builder()
                    .quantity(memberCheckdOrderItemList.size())
                    .deliveryAddress(member.getMemberInfo().getAddress())
                    .paymentAmount(payment)
                    .totalAmount(payment)
                    .member(member)
                    .cartItem(cart)
                    .order_state(OrderState.ORDER)
                    .build();
            orderRepository.save(memberOrder);
        }
    }

    public void cancelMemberOrder(Long id){
        String username = SecurityUtil.getCurrentUsername();
        Member member = memberRepository.findByMemberId(username).orElseThrow();
        MemberOrder memberOrder = member.getMemberOrderList().stream()
                .filter(order -> order.getId().equals(id))
                .findFirst()
                .orElseThrow();
        orderRepository.delete(memberOrder);
    }


    public List<CouponDto> getCouponList(){
        Member member = memberRepository.findByMemberId(SecurityUtil.getCurrentUsername()).orElseThrow();

        return member.getCouponList().stream()
                .filter(Coupon::getUsable)
                .map(CouponDto::toDto)
                .toList();

    }


}
