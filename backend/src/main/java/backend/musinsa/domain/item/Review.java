package backend.musinsa.domain.item;


import backend.musinsa.config.SecurityUtil;
import backend.musinsa.domain.BaseTimeEntity;
import backend.musinsa.domain.comment.Comment;
import backend.musinsa.domain.member.Member;
import backend.musinsa.domain.order.MemberOrder;
import backend.musinsa.domain.order.OrderItem;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Review extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String gender;
    private Integer height;
    private Integer weight;
    private String itemUrl;
    private String itemName;
    private String itemColorOption;
    private String itemSizeOption;
    private String grade;
    private String evalSize;
    private String evalBright;
    private String evalColor;
    private String evalThickness;
    private String evalDelivery;
    private String evalPackaging;
    private String text;

    private List<String> reviewImageUrl;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "review",cascade = CascadeType.REMOVE)
    private List<Comment> commentList;
    @Builder
    public Review(String name, String gender, Integer height, Integer weight, String itemUrl, String itemName, String itemColorOption, String itemSizeOption, String grade, String evalSize,
                  String evalBright, String evalColor, String evalThickness, String evalDelivery, String evalPackaging, String text, List<String> reviewImageUrl,Item item) {
        this.name = name;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.itemUrl = itemUrl;
        this.itemName = itemName;
        this.itemColorOption = itemColorOption;
        this.itemSizeOption = itemSizeOption;
        this.grade = grade;
        this.evalSize = evalSize;
        this.evalBright = evalBright;
        this.evalColor = evalColor;
        this.evalThickness = evalThickness;
        this.evalDelivery = evalDelivery;
        this.evalPackaging = evalPackaging;
        this.text = text;
        this.reviewImageUrl = reviewImageUrl;
        this.item = item;
    }
    public static Review toEntity(ReviewDto reviewDto, OrderItem orderItem){
        return Review.builder()
                .name(SecurityUtil.getCurrentUsername())  //토큰에서 사용자닉네임 추출
                .gender(reviewDto.getGender())
                .height(reviewDto.getHeight())
                .weight(reviewDto.getWeight())
                .itemUrl(orderItem.getThumbnail())
                .itemName(orderItem.getItemName())
                .itemColorOption(orderItem.getItemColorOption())
                .itemSizeOption(orderItem.getItemSizeOption())
                .grade(reviewDto.getGrade())
                .evalSize(reviewDto.getEvalSize())
                .evalBright(reviewDto.getEvalBright())
                .evalColor(reviewDto.getEvalColor())
                .evalThickness(reviewDto.getEvalThickness())
                .evalDelivery(reviewDto.getEvalDelivery())
                .evalPackaging(reviewDto.getEvalPackaging())
                .text(reviewDto.getText())
                .reviewImageUrl(reviewDto.getItemUrl())
                .item(orderItem.getItem())
                .build();
    }
}
