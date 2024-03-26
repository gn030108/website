package backend.musinsa.domain.member;

import backend.musinsa.domain.item.Like;
import backend.musinsa.domain.item.Review;
import backend.musinsa.domain.cart.Cart;
import backend.musinsa.domain.coupon.Coupon;
import backend.musinsa.domain.order.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
@AllArgsConstructor
@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String memberId;
    private String password;
    private String name;
    private Role role;

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

    @Builder
    public Member(String memberId, String password, String name, MemberInfo memberInfo) {
        this.memberId = memberId;
        this.password = password;
        this.name = name;
        this.memberInfo = memberInfo;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
