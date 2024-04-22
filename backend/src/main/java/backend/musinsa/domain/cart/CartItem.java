package backend.musinsa.domain.cart;

import backend.musinsa.domain.member.Member;
import backend.musinsa.domain.order.MemberOrder;
import backend.musinsa.domain.order.OrderItem;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class CartItem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer count;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private List<OrderItem> orderItemList = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    private MemberOrder memberOrder;

    @Builder
    public CartItem(Integer count, Member member, OrderItem orderItemList, MemberOrder memberOrder) {
        this.count = count;
        this.member = member;
        this.orderItemList.add(orderItemList);
        this.memberOrder = memberOrder;
    }
}
