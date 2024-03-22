package backend.musinsa.domain.member;


import backend.musinsa.domain.item.Like;
import backend.musinsa.domain.item.Review;
import backend.musinsa.domain.cart.Cart;
import backend.musinsa.domain.coupon.Coupon;
import backend.musinsa.domain.order.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String memberId;
    private String password;
    private String name;

    @OneToOne
    @JoinColumn(name = "member_info_id")
    private MemberInfo memberInfo;

    @OneToMany(mappedBy = "member",fetch = FetchType.LAZY)
    private List<Review> reviewList;

    @OneToOne
    @JoinColumn(name = "cart_id")
    private List<Cart> cartList;

    @OneToMany(mappedBy = "member",fetch = FetchType.LAZY)
    private List<Coupon> couponList;

    @OneToMany(mappedBy = "member",fetch = FetchType.LAZY)
    private List<Order> orderList;

    @OneToMany(mappedBy = "member",fetch = FetchType.LAZY)
    private List<Like> likeList;

}
