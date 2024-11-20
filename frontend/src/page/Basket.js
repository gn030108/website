import Footer from "../component/Footer";
import NavBar from "../component/NavBar";
import SideBar from "../component/SideBar";
import { useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import styles from "../styles/pageStyle/basket.module.scss";
import 상의 from "../image/상의.jpg";
import PaymentModal from "../component/PaymentModal";
import { basketActions } from "../redux/reducer/pageReducer/basketReducer";

const Basket = () => {
  const dispatch = useDispatch();

  const [paymentModal, setPaymentModal] = useState(false);

  //체크리스트 데이터 가정
  const items = [
    {
      img: 상의,
      name: "반팔 니트",
      option1: "M",
      option2: "린넨",
      price: "10000",
      count: "2",
    },
    {
      img: 상의,
      name: "반팔 니트",
      option1: "M",
      option2: "린넨",
      price: "10000",
      count: "2",
    },
    {
      img: 상의,
      name: "반팔 니트",
      option1: "M",
      option2: "린넨",
      price: "10000",
      count: "2",
    },
    {
      img: 상의,
      name: "반팔 니트",
      option1: "M",
      option2: "린넨",
      price: "10000",
      count: "2",
    },
  ];

  const checkItems = useSelector((state) => state.basket.checkItems);

  // 체크박스 단일 선택
  const handleSingleCheck = (checked, id) => {
    if (checked) {
      // 단일 선택 시 체크된 아이템을 배열에 추가
      dispatch(basketActions.getCheck(id));
    } else {
      // 단일 선택 해제 시 체크된 아이템을 제외한 배열 (필터)
      dispatch(basketActions.getCheckOut(id));
    }
  };
  // 체크박스 전체 선택
  const handleAllCheck = (checked) => {
    if (checked) {
      // 전체 선택 클릭 시 데이터의 모든 아이템(id)를 담은 배열로 checkItems 상태 업데이트
      const idArray = items.map((item) => item.id);
      dispatch(basketActions.all(idArray));
    } else {
      // 전체 선택 해제 시 checkItems 를 빈 배열로 상태 업데이트
      dispatch(basketActions.all([]));
    }
  };

  return (
    <>
      {paymentModal && (
        <PaymentModal modal={paymentModal} setModal={setPaymentModal} />
      )}{" "}
      {/* modal이 참이 되면 열리는 modal컴포넌트 */}
      <div>
        <div className={styles.navbar}>
          <NavBar />
        </div>
        <SideBar />
        <div className={styles.container}>
          <div className={styles.title_box}>
            <p>장바구니</p>
          </div>
          {/* 장바구니 내역 */}
          <div className={styles.basket_box}>
            {/* 데스크탑 */}
            <div className={styles.header_box}>
              <div>전체 x개</div>
              <input
                type="checkbox"
                name="select-all"
                onChange={(e) => handleAllCheck(e.target.checked)}
                // 데이터 개수와 체크된 아이템의 개수가 다를 경우 선택 해제 (하나라도 해제 시 선택 해제)
                checked={checkItems.length === items.length ? true : false}
              />
              <div>상품명(옵션)</div>
              <div>판매가</div>
              <div>수량</div>
              <div>주문금액</div>
              <div>주문관리</div>
            </div>
            {/* 장바구니 아이탬 */}
            {items.map((item, index) => (
              <div className={styles.content_box} key={index}>
                <div>{index + 1}</div>
                <input
                  type="checkbox"
                  name={`select-${index + 1}`}
                  onChange={(e) =>
                    handleSingleCheck(e.target.checked, index + 1)
                  }
                  // 체크된 아이템 배열에 해당 아이템이 있을 경우 선택 활성화, 아닐 시 해제, 장바구니에서 미리 선택했던 상품에 대해 체크를 해두기 위함
                  checked={checkItems.includes(index + 1) ? true : false}
                />
                <div>
                  <div className={styles.item_imgBox}>
                    <img src={item.img} />
                  </div>
                  <div className={styles.item_info}>
                    <p>[무신사스텐다드]{item.name}</p>
                    <p>
                      옵션 : {item.option1} / {item.option2}
                    </p>
                  </div>
                </div>
                <div>{item.price}원</div>
                <div>{item.count}개</div>
                <div>{item.price * item.count}원</div>
                <div className={styles.delete_btn}>
                  <button>삭제</button>
                </div>
              </div>
            ))}
            {/* 모바일 화면  */}
            <div className={styles.header_box_mobile}>
              <div className={styles.header_div_mobile}>
                <input
                  type="checkbox"
                  name="select-all"
                  onChange={(e) => handleAllCheck(e.target.checked)}
                  // 데이터 개수와 체크된 아이템의 개수가 다를 경우 선택 해제 (하나라도 해제 시 선택 해제)
                  checked={checkItems.length === items.length ? true : false}
                />
                <div>전체 x개</div>
              </div>
              <div>선택 삭제</div>
            </div>

            {items.map((item, index) => (
              <div className={styles.content_box_mobile} key={index}>
                <div style={{ display: "flex", alignItems: "center" }}>
                  <input
                    type="checkbox"
                    name={`select-${index + 1}`}
                    onChange={(e) =>
                      handleSingleCheck(e.target.checked, index + 1)
                    }
                    // 체크된 아이템 배열에 해당 아이템이 있을 경우 선택 활성화, 아닐 시 해제, 장바구니에서 미리 선택했던 상품에 대해 체크를 해두기 위함
                    checked={checkItems.includes(index + 1) ? true : false}
                  />
                  <img src={item.img} />
                  <div className={styles.content_info_mobile}>
                    <p>무신사스탠다드</p>
                    <p>홀리데이 시그니처 볼캡</p>
                    <div>
                      <p>수량 : {item.count}</p>
                      <p>Free</p>
                    </div>
                  </div>
                </div>

                <div className={styles.content_price_mobile}>
                  {item.price * item.count}원
                </div>
              </div>
            ))}
          </div>
          {/* 주문하기버튼 */}
          <div className={styles.button_box}>
            <button
              onClick={() => {
                setPaymentModal(!paymentModal);
              }}
            >
              주문하기
            </button>
          </div>
        </div>
        <Footer />
      </div>
    </>
  );
};

export default Basket;
