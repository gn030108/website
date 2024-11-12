import styles from "../styles/componentStyle/paymentModal.module.scss";
import { useDispatch, useSelector } from "react-redux";
import { modalActions } from "../redux/reducer/componentReducer/modalReducer";
import SelectBox from "./SelectBox";

const PaymentModal = ({ modal, setModal }) => {
  const dispatch = useDispatch();

  const handleOpen = () => {
    setModal(!modal);
    dispatch(modalActions.reset());
  };

  const handleSubmit = () => {
    dispatch(modalActions.reset());
    setModal(!modal);
  };
  //모달 정보 변수
  const payment = useSelector((state) => state.modal.payment);

  //선택박스 변수
  const discountData = useSelector((state) => state.selectBox.optionData1);
  const discount = useSelector((state) => state.selectBox.option1);

  return (
    <div className={styles.container}>
      <div className={styles.modal_box}>
        <button
          className={styles.closeBtn}
          onClick={() => {
            handleOpen();
          }}
        >
          <i className="ri-close-line"></i>
        </button>
        <h2>주문서</h2>
        <div className={styles.item_box}>
          {payment.map((item, index) => (
            <div className={styles.item_content} key={index}>
              <div style={{ display: "flex" }}>
                <div className={styles.item_content_img}>
                  <img src={item.img} />
                </div>
                <div className={styles.item_content_info}>
                  <p>{item.itemName}</p>
                  <p>옵션 : {item.option}</p>
                  <p>수량 : {item.count}</p>
                </div>
              </div>

              <div className={styles.item_content_price}>
                <p>{item.price}원</p>
              </div>
            </div>
          ))}
        </div>

        <div className={styles.selectBox}>
          <SelectBox
            optionData={discountData}
            currentValue={discount}
            type={"option1"}
          />
        </div>

        <div className={styles.price_box}>
          <div>
            <p>전체 상품금액</p>
            <p>1231230원</p>
          </div>
          <div>
            <p>할인금액</p>
            <p>-12300원</p>
          </div>
          <div>
            <p>총 금액</p>
            <p>123123원</p>
          </div>
        </div>
        <button
          className={styles.submitBtn}
          onClick={() => {
            handleSubmit();
          }}
        >
          결제
        </button>
      </div>
    </div>
  );
};

export default PaymentModal;
