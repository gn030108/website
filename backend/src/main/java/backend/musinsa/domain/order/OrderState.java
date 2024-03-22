package backend.musinsa.domain.order;


/**
 * order -> 주문완료
 * prepare_delivery -> 배송준비중
 * delivery -> 배송완료
 */
public enum OrderState {
    ORDER,
    PREPARE_DELIVERY,
    DELIVERY,
}
