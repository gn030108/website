import 상의 from "../image/상의.jpg";
import 바지 from "../image/바지.jpg";
import 신발 from "../image/신발.jpg";
import { useNavigate } from "react-router-dom";

import styles from "../styles/componentStyle/card.module.scss";
import { useEffect, useState } from "react";
import useAxiosInstance from "../api/axiosInstance";

// 리스트를 한 줄에 표시할 수 있는 그룹으로 나누는 함수
const groupItems = (data, itemsPerRow) => {
  const groups = [];
  let group = [];
  for (let i = 0; i < data.length; i++) {
    group.push(data[i]);
    if (group.length === itemsPerRow || i === data.length - 1) {
      groups.push(group);
      group = [];
    }
  }
  return groups;
};

const Card = () => {
  // 사진, 브랜드 , 상품이름, 가격, 평점, 좋아요
  const list = [
    {
      image: 상의,
      brand: "수아레",
      name: "반팔 니트",
      price: "000000",
      star: "5",
      heart: "88",
    },
    {
      image: 바지,
      brand: "디미트리블랙",
      name: "버뮤다 팬츠",
      price: "000000",
      star: "5",
      heart: "128",
    },
    {
      image: 신발,
      brand: "반스",
      name: "스니커즈",
      price: "000000",
      star: "5",
      heart: "568",
    },
    {
      image: 상의,
      brand: "수아레",
      name: "반팔 니트",
      price: "000000",
      star: "5",
      heart: "88",
    },
    {
      image: 바지,
      brand: "디미트리블랙",
      name: "버뮤다 팬츠",
      price: "000000",
      star: "5",
      heart: "128",
    },
    {
      image: 신발,
      brand: "반스",
      name: "스니커즈",
      price: "000000",
      star: "5",
      heart: "568",
    },
    {
      image: 상의,
      brand: "수아레",
      name: "반팔 니트",
      price: "000000",
      star: "5",
      heart: "88",
    },
    {
      image: 바지,
      brand: "디미트리블랙",
      name: "버뮤다 팬츠",
      price: "000000",
      star: "5",
      heart: "128",
    },
    {
      image: 신발,
      brand: "반스",
      name: "스니커즈",
      price: "000000",
      star: "5",
      heart: "568",
    },
    {
      image: 상의,
      brand: "수아레",
      name: "반팔 니트",
      price: "000000",
      star: "5",
      heart: "88",
    },
    {
      image: 바지,
      brand: "디미트리블랙",
      name: "버뮤다 팬츠",
      price: "000000",
      star: "5",
      heart: "128",
    },
    {
      image: 신발,
      brand: "반스",
      name: "스니커즈",
      price: "000000",
      star: "5",
      heart: "568",
    },
  ];

  const navigate = useNavigate();
  const axiosInstance = useAxiosInstance();

  //해당 아이템 정보 페이지로 이동
  const goItem = (index, groupIndex) => {
    const id = 1;

    axiosInstance
      .get(`/item/get/${id}`)
      .then((response) => {
        console.log(response);
        console.log("성공!");
      })
      .catch((error) => {
        console.log(error);
        console.log("실패");
      });

    navigate("/Item");
  };

  //홈 화면에서 표시되는 화면 크기에 따른 갯수(가로)
  useEffect(() => {
    const handleItemPreRow = () => {
      let width = document.documentElement.clientWidth;

      if (width <= 550) {
        setItemsPerRow(3);
        console.log("3");
      } else if (width > 550 && width <= 768) {
        setItemsPerRow(4);
        console.log("4");
      } else if (width > 768) {
        setItemsPerRow(6);
        console.log("6");
      }
    };

    handleItemPreRow();

    window.addEventListener("resize", handleItemPreRow);

    return () => {
      window.removeEventListener("resize", handleItemPreRow);
    };
  }, []);

  const [itemsPerRow, setItemsPerRow] = useState(6); // 기본 카드 갯수 설정
  const groupedItems = groupItems(list, itemsPerRow);

  return (
    <div>
      {groupedItems.map((group, index) => (
        <div className={styles.layout} key={index}>
          {group.map((item, groupIndex) => (
            <div
              className={styles.card}
              key={groupIndex}
              onClick={() => goItem(index, groupIndex)}
            >
              <div className={styles.imgArea}>
                <div className={styles.img_box}>
                  <img src={item.image} alt="이미지" />
                </div>
              </div>

              <div className={styles.infoArea}>
                {" "}
                {/* 브랜드, 상품이름, 가격 */}
                <div>{item.brand}</div>
                <div>{item.name}</div>
                <div>{item.price}원</div>
                <div className={styles.stars}>
                  <div>
                    {/* 평점 */}
                    <i
                      className="ri-star-fill"
                      style={{ color: "#FF933B" }}
                    ></i>
                    <p>{item.star}</p>
                  </div>
                  <div>
                    {/* 좋아요 */}
                    <i
                      className="ri-heart-fill"
                      style={{ color: "#FF3333" }}
                    ></i>
                    <p>{item.heart}</p>
                  </div>
                </div>
              </div>
            </div>
          ))}
        </div>
      ))}
    </div>
  );
};

export default Card;
