import Footer from "../component/Footer";
import NavBar from "../component/NavBar";
import SideBar from "../component/SideBar";
import SelectBox from "../component/SelectBox";
import Review from "../component/Review";
import { useSelector } from "react-redux";
import styles from "../styles/pageStyle/item.module.scss";
import 상의 from "../image/상의.jpg";
import 바지 from "../image/바지.jpg";
import 상품1 from "../image/상품 1.jpg";
import 상품2 from "../image/상품2.jpg";
import { useState } from "react";

const Item = () => {
  const itemImg = [
    { id: 1, img: 상의 },
    { id: 2, img: 바지 },
  ];
  const imgList = [{ img: 상품1 }, { img: 상품2 }];
  const handleHover = (img, index) => {
    setMain(img);
    setHover(index);
  };

  const [main, setMain] = useState(itemImg[0].img);
  const [hover, setHover] = useState(1);

  const optionData1 = useSelector((state) => state.selectBox.optionData1);
  const optionData2 = useSelector((state) => state.selectBox.optionData2);
  const option1 = useSelector((state) => state.selectBox.option1);
  const option2 = useSelector((state) => state.selectBox.option2);

  const loginType = useSelector((state) => state.login.loginType);

  const [isView, setIsView] = useState(false); //더보기 & 접기 상태 저장

  const handleViewImage = () => {
    setIsView(!isView);
  }; //더보기 & 접기 상태 변경함수

  return (
    <div>
      <div className={styles.navbar}>
        <NavBar />
      </div>
      <SideBar />
      <div className={styles.container}>
        <div style={{ width: "fit-content" }}>
          {/* 상품 맨 위 이미지 및 정보칸 */}
          <div className={styles.itemTop_box}>
            {/* 1.상품 사진 슬라이더? 왼쪽, 자동회전 x  */}
            <div>
              <div className={styles.top_image_box}>
                <img src={main} alt="이미지" />
              </div>
              <ul className={styles.imgList}>
                {itemImg.map((item, index) => (
                  <li
                    className={hover === index ? styles.hoverImg : ""}
                    key={index}
                  >
                    <img
                      src={item.img}
                      alt="이미지"
                      onMouseOver={() => {
                        handleHover(item.img, index);
                      }}
                    />
                  </li>
                ))}
              </ul>
            </div>

            {/* 2. 상품 정보 오른쪽 */}
            <div className={styles.top_info_box}>
              {/* 제품정보 */}
              <div
                className={styles.Info_box}
                style={{ borderTop: "1px solid #888" }}
              >
                <div className={styles.Info_top}>
                  <h3>Product Info</h3>
                  <p>제품정보</p>
                </div>
                <ul className={styles.Info_bot}>
                  <li className={styles.info}>
                    <div className={`${styles.infoTitle} ${styles.infoBrand}`}>
                      <p>브랜드</p>
                      <p>품번</p>
                    </div>
                    <div
                      className={`${styles.infoContent} ${styles.infoBrand}`}
                    >
                      <p>EMIS</p>
                      <p>P0000CJP</p>
                    </div>
                  </li>

                  <li className={styles.info}>
                    <div className={styles.infoTitle}>
                      <p>성별</p>
                    </div>
                    <div className={styles.infoContent}>
                      <p>여성</p>
                    </div>
                  </li>
                  <li className={styles.info}>
                    <div className={styles.infoTitle}>
                      <p>좋아요</p>
                    </div>
                    <div className={styles.infoContent}>
                      <i className="ri-heart-fill" style={{ color: "red" }}></i>
                      <p>86</p>
                    </div>
                  </li>
                </ul>
              </div>
              {/* 배송정보 */}
              <div className={styles.Info_box}>
                <div className={styles.Info_top}>
                  <h3>Delivery Info</h3>
                  <p>배송정보</p>
                </div>
                <ul className={styles.Info_bot}>
                  <li className={styles.info}>
                    <div className={styles.infoTitle}>
                      <p>출고 정보</p>
                    </div>
                    <div className={styles.infoContent}>
                      <p>결제 3일 이내 출고</p>
                    </div>
                  </li>
                  <li className={styles.info}>
                    <div className={styles.infoTitle}>
                      <p>배송 정보</p>
                    </div>
                    <div
                      className={`${styles.infoContent} ${styles.infoBrand}`}
                    >
                      <p>국내배송</p>
                      <p>입점사 배송</p>
                    </div>
                  </li>
                </ul>
              </div>
              {/* 가격정보 */}
              <div className={styles.Info_box}>
                <div className={styles.Info_top}>
                  <h3>Price Info</h3>
                  <p>가격정보</p>
                </div>
                <ul className={styles.Info_bot}>
                  <li className={styles.info}>
                    <div className={styles.infoTitle}>
                      <p>판매가</p>
                    </div>
                    <div className={styles.infoContent}>
                      <p>89,000원</p>
                    </div>
                  </li>
                </ul>
              </div>

              {/* 옵션 선택 및 구매 버튼 */}
              {loginType !== "admin" && (
                <div
                  className={styles.Info_box}
                  style={{
                    display: "flex",
                    justifyContent: "center",
                    alignItems: "center",
                    flexDirection: "column",
                  }}
                >
                  {/* 옵션 선택 */}
                  <div className={styles.selectBox}>
                    <SelectBox
                      optionData={optionData1}
                      currentValue={option1}
                      type={"option1"}
                    />
                  </div>
                  <div
                    className={styles.selectBox}
                    style={{ marginTop: "15px" }}
                  >
                    <SelectBox
                      optionData={optionData2}
                      currentValue={option2}
                      type={"option2"}
                    />
                  </div>
                  {/* 구매 버튼 */}
                  <div className={styles.buyBtn}>
                    <button>구매</button>
                  </div>
                </div>
              )}
              {/* 판매자 */}
              {loginType === "admin" && (
                <div
                  className={styles.Info_box}
                  style={{
                    display: "flex",
                    justifyContent: "center",
                    alignItems: "center",
                    flexDirection: "column",
                  }}
                >
                  {/* 옵션 선택 */}
                  <div className={styles.selectBox}>
                    <SelectBox
                      optionData={optionData1}
                      currentValue={option1}
                      type={"option1"}
                    />
                  </div>
                  {/* 버튼 */}
                  <div className={styles.buyBtn}>
                    <button>상태 수정</button>
                  </div>
                  <div className={styles.buyBtn}>
                    <button>삭제</button>
                  </div>
                </div>
              )}
            </div>
          </div>
          {/* 상품 설명 이미지 */}
          <div className={styles.itemBot_box}>
            <div className={!isView ? styles.beforeImg : styles.afterImg}>
              {imgList.map((item, index) => (
                <img src={item.img} key={index} alt="이미지" />
              ))}

              {/* 더보기 누르기 전 => 하양 흐림 효과 부여 */}
              {isView === false && (
                <div className={styles.whiteGradientOverlay}></div>
              )}
            </div>
            {isView ? (
              <button
                className={styles.view_close}
                onClick={() => handleViewImage()}
              >
                상품 정보 접기
              </button>
            ) : (
              <button
                className={styles.view_open}
                onClick={() => handleViewImage()}
              >
                상품 정보 더보기
              </button>
            )}
          </div>
          {loginType !== "admin" && (
            <div className={styles.review_box}>
              <Review />
            </div>
          )}
        </div>
      </div>
      <Footer />
    </div>
  );
};

export default Item;
