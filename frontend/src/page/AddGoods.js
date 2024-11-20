import Footer from "../component/Footer";
import NavBar from "../component/NavBar";
import SideBar from "../component/SideBar";
import { useSelector } from "react-redux";
import styles from "../styles/pageStyle/AddGoods.module.scss";
import 기타색상 from "../image/기타색상.jpg";
import { useState } from "react";
import OptionAdd from "../component/OptionAdd";

const AddGoods = () => {
  const handleHover = (img, index) => {
    setMain(img);
    setHover(index);
  };

  const [main, setMain] = useState();
  const [hover, setHover] = useState(1);

  const [itemNumber, setItemNumber] = useState(""); //품번
  const [itemName, setItemName] = useState(""); // 상품이름
  const [price, setPrice] = useState(""); // 상품가격

  const [goodsImg, setGoodsImg] = useState([]); //상품사진 리스트
  const [preViewGoodsImg, setPreViewGoodsImg] = useState([]); //상품사진 미리보기 리스트
  const [description, setDescription] = useState([]); //상품설명사진 리스트
  const [preViewDescription, setPreViewDescription] = useState([]); //상품설명사진 미리보기 리스트

  const [colorTag, setColorTag] = useState(""); //색상태그

  const option1 = useSelector((state) => state.AddGoods.option1); //옵션1
  const option2 = useSelector((state) => state.AddGoods.option2); //옵션2
  const optionList1 = useSelector((state) => state.AddGoods.optionList1); //옵션1하위옵션
  const optionList2 = useSelector((state) => state.AddGoods.optionList2); // 옵션2하위옵션

  //상품 사진 업로드 기능
  const savePreViewFiles = (e) => {
    const file = e.target.files;
    setGoodsImg(file);
    let fileUrl = [];
    for (let i = 0; i < file.length; i++) {
      const render = new FileReader();
      render.readAsDataURL(file[i]);
      render.onload = () => {
        fileUrl[i] = render.result;
        setPreViewGoodsImg([...fileUrl]);
        setMain(fileUrl[0]);
      };
    }
    console.log(goodsImg, ": 보낼이미지");
    console.log(preViewGoodsImg, ":미리보기이미지");
  };
  //상품 설명 사진 업로드 기능
  const savePreViewImg = (e) => {
    const file = e.target.files;
    setDescription(file);
    let fileUrl = [];
    for (let i = 0; i < file.length; i++) {
      const render = new FileReader();
      render.readAsDataURL(file[i]);
      render.onload = () => {
        fileUrl[i] = render.result;
        setPreViewDescription([...fileUrl]);
      };
    }
    console.log(description, ": 보낼 설명이미지");
    console.log(preViewDescription, ":미리보기 설명이미지");
  };

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
                {preViewGoodsImg.length < 1 && <p>상품이미지</p>}
                {preViewGoodsImg.length > 0 && (
                  <img src={main} alt="상품 이미지" />
                )}
              </div>
              <ul className={styles.imgList}>
                {preViewGoodsImg.map((item, index) => (
                  <li
                    className={hover == index ? styles.hoverImg : ""}
                    key={index}
                  >
                    <img
                      src={preViewGoodsImg[index]}
                      alt="이미지"
                      onMouseOver={() => {
                        handleHover(item, index);
                      }}
                    />
                    <button
                      className={
                        hover == index ? styles.hoverBtn : styles.hoverBtn_none
                      }
                    >
                      삭제
                    </button>
                  </li>
                ))}
              </ul>
              {goodsImg.length < 7 && (
                <div className={styles.uploadGoodsImg}>
                  <label htmlFor="uploadGoodsImg">업로드</label>
                  <input
                    id="uploadGoodsImg"
                    onChange={savePreViewFiles}
                    type="file"
                    accept="image/jpeg, image/jpg, image/png"
                    style={{ display: "none" }}
                    multiple
                    required
                  />
                </div>
              )}
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
                <ul>
                  <li className={styles.info}>
                    <div className={`${styles.infoTitle} ${styles.infoBrand}`}>
                      <p>브렌드</p>
                      <p>품번</p>
                    </div>
                    <div
                      className={`${styles.infoContent} ${styles.infoBrand}`}
                    >
                      <p>EMIS</p>
                      <input
                        value={itemNumber}
                        onChange={(e) => {
                          setItemNumber(e.target.value);
                        }}
                      />
                    </div>
                  </li>
                  <li className={styles.info}>
                    <div className={styles.infoTitle}>
                      <p>상품이름</p>
                    </div>
                    <div className={styles.infoContent}>
                      <input
                        value={itemName}
                        onChange={(e) => {
                          setItemName(e.target.value);
                        }}
                      />
                    </div>
                  </li>
                  <li className={styles.info}>
                    <div className={styles.infoTitle}>
                      <p>판매가</p>
                    </div>
                    <div className={styles.infoContent}>
                      <input
                        value={price}
                        onChange={(e) => {
                          setPrice(e.target.value);
                        }}
                      />
                    </div>
                  </li>
                </ul>
              </div>

              {/* 가격정보 */}
              <div className={styles.Info_box}>
                <div className={styles.Info_top}>
                  <h3>Tag</h3>
                  <p>태그선택</p>
                </div>
                <div className={styles.info}>
                  <div className={styles.infoTitle}>
                    <p>색상 : </p>
                    <p style={{ marginLeft: "20px" }}>{colorTag}</p>
                  </div>
                  <div className={styles.colors}>
                    <li>
                      <a
                        style={{ backgroundColor: "black" }}
                        onClick={(e) => {
                          setColorTag(e.target.dataset.color);
                        }}
                        data-color="블랙"
                      ></a>
                    </li>
                    <li>
                      <a
                        style={{ backgroundColor: "white" }}
                        onClick={(e) => {
                          setColorTag(e.target.dataset.color);
                        }}
                        data-color="화이트"
                      ></a>
                    </li>
                    <li>
                      <a
                        style={{ backgroundColor: "navy" }}
                        onClick={(e) => {
                          setColorTag(e.target.dataset.color);
                        }}
                        data-color="네이비"
                      ></a>
                    </li>
                    <li>
                      <a
                        style={{ backgroundColor: "gray" }}
                        onClick={(e) => {
                          setColorTag(e.target.dataset.color);
                        }}
                        data-color="그레이"
                      ></a>
                    </li>
                    <li>
                      <a
                        style={{ backgroundColor: "#BACFE1" }}
                        onClick={(e) => {
                          setColorTag(e.target.dataset.color);
                        }}
                        data-color="연청"
                      ></a>
                    </li>
                    <li>
                      <a
                        style={{ backgroundColor: "#97AAC3" }}
                        onClick={(e) => {
                          setColorTag(e.target.dataset.color);
                        }}
                        data-color="중청"
                      ></a>
                    </li>
                    <li>
                      <a
                        style={{ backgroundColor: "#232C41" }}
                        onClick={(e) => {
                          setColorTag(e.target.dataset.color);
                        }}
                        data-color="진청"
                      ></a>
                    </li>
                    <li>
                      <a
                        style={{ backgroundColor: "#222B40" }}
                        onClick={(e) => {
                          setColorTag(e.target.dataset.color);
                        }}
                        data-color="흑청"
                      ></a>
                    </li>
                    <li>
                      <a
                        style={{
                          backgroundImage: `url(${기타색상})`,
                          backgroundSize: "contain",
                        }}
                        onClick={(e) => {
                          setColorTag(e.target.dataset.color);
                        }}
                        data-color="기타색상"
                      ></a>
                    </li>

                    {/* <li>
                      <a style={{backgroundColor:''}}></a>
                    </li> */}
                  </div>
                </div>
              </div>

              {/* 옵션 선택 및 구매 버튼 */}
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
                  <OptionAdd
                    title={option1}
                    optionList={optionList1}
                    type="option1"
                  />
                </div>
                <div className={styles.selectBox}>
                  <OptionAdd
                    title={option2}
                    optionList={optionList2}
                    type="option2"
                  />
                </div>
                {/* 구매 버튼 */}
                <div className={styles.buyBtn}>
                  <button>등록</button>
                </div>
              </div>
            </div>
          </div>
          {/* 상품 설명 이미지 */}
          <div className={styles.itemBot_box}>
            <div className={styles.descriptionImg}>
              {preViewDescription.map((item, index) => (
                <img src={item} key={index} alt="이미지" />
              ))}
            </div>
            <div className={styles.description_Btn}>
              <label htmlFor="descriptionImg">업로드</label>
              <input
                id="descriptionImg"
                onChange={savePreViewImg}
                type="file"
                accept="image/jpeg, image/jpg, image/png"
                style={{ display: "none" }}
                multiple
                required
              />
            </div>
          </div>
        </div>
      </div>
      <Footer />
    </div>
  );
};

export default AddGoods;
