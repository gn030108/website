package backend.musinsa.domain.order;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {

    private List<Long> orderItemIdList;
    private String couponId;

}
