import React from "react";
import styles from "../styles/componentStyle/review.module.scss";
import Comment from "./Comment";
import { useSelector } from "react-redux";

const Review = () => {
  const reviewList = useSelector((state) => state.review.reviewList);

  return (
    <div style={{ width: "100%" }}>
      {reviewList.map((review, index) => (
        // 구매자 리뷰
        <div key={index}>
          {/* 구매자 이름 */}
          <div className={styles.user}>
            <div>
              <p>{review.name}</p>
              <p>{review.date}</p>
            </div>
            <div>
              <p>{review.gender}</p>
              <p>{review.height}cm</p>
              <p>{review.weight}kg</p>
            </div>
          </div>
          {/* 상품 사진 및 이름 사이즈 별점 */}
          <div>
            {/* 상품사진 및 이름 사이즈 */}
            <div className={styles.itemImgName}>
              <img src={review.itemImage} alt="이미지" />
              <div className={styles.itemName}>
                <p>{review.itemName}</p>
                <p> {review.itemOption}구매</p>
              </div>
            </div>
            {/* 별점 */}
            <div className={styles.stars}>
              {[...Array(parseInt(review.stars))].map((_, index) => (
                <i
                  className="ri-star-fill"
                  key={index}
                  style={{ color: "#FF933B" }}
                ></i>
              ))}
            </div>
            {/* 상품 태그 (사이즈, 밝기, 색감, 두깨감, 배송, 포장) */}
            <div className={styles.tags_box}>
              <div>
                <p>사이즈</p>
                <p>{review.size}</p>
              </div>
              <div>
                <p>밝기</p>
                <p>{review.bright}</p>
              </div>
              <div>
                <p>색감</p>
                <p>{review.color}</p>
              </div>
              <div>
                <p>두깨감</p>
                <p>{review.thickness}</p>
              </div>
              <div>
                <p>배송</p>
                <p>{review.delivery}</p>
              </div>
              <div>
                <p>포장</p>
                <p>{review.packaging}</p>
              </div>
            </div>
          </div>
          {/* 리뷰 글 및 사진 */}
          <div className={styles.review_box}>
            <div className={styles.review_text}>
              <p>정말 좋습니다 재구매 의사있습니다.</p>
            </div>
            <div className={styles.review_img}>
              {review.image.map((image, index) => (
                <img src={image} key={index} alt="이미지" />
              ))}
            </div>
          </div>
          <div className={styles.comment_layout}>
            <div className={styles.comment_box}>
              <input />
              <button>작성</button>
            </div>
            <Comment />
          </div>
        </div>
      ))}
    </div>
  );
};

export default Review;
