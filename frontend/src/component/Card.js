import 김민주 from '../image/김민주.png'
import 김민주1 from '../image/김민주1.png'
import 김민주2 from '../image/김민주2.png'
import 김민주3 from '../image/김민주3.png'
import { useNavigate } from 'react-router-dom';

import styles from '../styles/componentStyle/card.module.scss'
import { useEffect, useState } from 'react';

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
}

const Card = () => {

  //사진, 브랜드 , 상품이름, 가격, 평점, 좋아요
  const list = [
    {image : 김민주,  brand : '인스타',  name : '김민주'  ,price:'000000', star:'5', heart:'588'},
    {image : 김민주1, brand : '트위터',  name : '김민주1' ,price:'000000', star:'5', heart:'88'},
    {image : 김민주2, brand : '아이즈원',name : '김민주2' ,price:'000000', star:'5', heart:'128'},
    {image : 김민주3, brand : '카톡',    name : '김민주3' ,price:'000000', star:'5', heart:'568'},
    {image : 김민주,  brand : '인스타',  name : '김민주'  ,price:'000000', star:'5', heart:'588'},
    {image : 김민주1, brand : '트위터',  name : '김민주1' ,price:'000000', star:'5', heart:'88'},
    {image : 김민주2, brand : '아이즈원',name : '김민주2' ,price:'000000', star:'5', heart:'128'},
    {image : 김민주3, brand : '카톡',    name : '김민주3' ,price:'000000', star:'5', heart:'568'},
    {image : 김민주,  brand : '인스타',  name : '김민주'  ,price:'000000', star:'5', heart:'588'},
    {image : 김민주1, brand : '트위터',  name : '김민주1' ,price:'000000', star:'5', heart:'88'},
    {image : 김민주2, brand : '아이즈원',name : '김민주2' ,price:'000000', star:'5', heart:'128'},
    {image : 김민주3, brand : '카톡',    name : '김민주3' ,price:'000000', star:'5', heart:'568'},
  ]
  
  const navigate = useNavigate()

  const goItem = ()=>{
    navigate("/Item")
  }

  useEffect(()=>{
    const handleItemPreRow =()=>{
      
      if (window.innerWidth <= 480){
        setItemsPerRow(3);
      }
      else if (480 < window.innerWidth <= 768){
        setItemsPerRow(4);
      }
      else if (768 < window.innerWidth <= 1024){
        setItemsPerRow(6);
      }
    }
    handleItemPreRow()

    window.addEventListener("resize", handleItemPreRow);

    return ()=>{
      window.removeEventListener("resize", handleItemPreRow);
    }
  },[])

  const [itemsPerRow,setItemsPerRow] = useState(6)
  // let itemsPerRow = 6; // 한 줄에 표시할 아이템의 수
  const groupedItems = groupItems(list, itemsPerRow);


  return (
    <div>
      {groupedItems.map((group, index)=>(
        <div className={styles.layout} key={index}>
          {group.map((item, groupIndex)=>(
            <div className={styles.card} key={groupIndex} onClick={()=>(goItem())}>
              <div className={styles.imgArea}>
                <div className={styles.img_box}>
                  <img src={item.image} alt='이미지'/>
                </div>
              </div>

              <div className={styles.infoArea}> {/* 브랜드, 상품이름, 가격 */}
                <div>{item.brand}</div>
                <div>{item.name}</div>
                <div>{item.price}원</div>
                <div className={styles.stars}>
                  <div>{/* 평점 */}
                    <i className="ri-star-fill" style={{color:'#FF933B'}}></i>
                    <p>{item.star}</p>
                  </div>
                  <div>{/* 좋아요 */}
                    <i className="ri-heart-fill" style={{color:'#FF3333'}}></i>
                    <p>{item.heart}</p>
                  </div>
                </div>
              </div>
            </div>
          ))}
        </div>
      ))}
    </div>
  )
}

export default Card