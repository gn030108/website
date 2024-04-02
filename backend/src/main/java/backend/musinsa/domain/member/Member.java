package backend.musinsa.domain.member;

import backend.musinsa.domain.item.Favorite;
import backend.musinsa.domain.item.Review;
import backend.musinsa.domain.cart.Cart;
import backend.musinsa.domain.coupon.Coupon;
import backend.musinsa.domain.order.MemberOrder;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Member implements UserDetails {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String memberId;
    private String password;
    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;

    @OneToOne
    @JoinColumn(name = "member_info_id")
    private MemberInfo memberInfo;

    @OneToMany(mappedBy = "member",fetch = FetchType.LAZY)
    private List<Review> reviewList;

    @OneToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @OneToMany(mappedBy = "member",fetch = FetchType.LAZY)
    private List<Coupon> couponList;

    @OneToMany(mappedBy = "member",fetch = FetchType.LAZY)
    private List<MemberOrder> memberOrderList;

    @OneToMany(mappedBy = "member",fetch = FetchType.LAZY)
    private List<Favorite> favoriteList;

    @Builder
    public Member(String memberId, String password, String name, MemberInfo memberInfo) {
        this.memberId = memberId;
        this.password = password;
        this.name = name;
        this.memberInfo = memberInfo;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

}
