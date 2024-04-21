import Footer from '../component/Footer'
import NavBar from '../component/NavBar'
import SideBar from '../component/SideBar'
import { useSelector } from 'react-redux'
import styles from '../styles/pageStyle/AddGoods.module.scss'
import 상품2 from '../image/상품2.jpg'
import { useState } from 'react'
import OptionAdd from '../component/OptionAdd'

const AddGoods = () => {

  const handleHover = (img,index)=>{
    setMain(img)
    setHover(index)
  }

  const [main, setMain] = useState()
  const [hover, setHover] = useState(1)

  const [goodsImg, setGoodsImg] = useState([])                     //상품사진 리스트
  const [preViewGoodsImg, setPreViewGoodsImg] = useState([])       //상품사진 미리보기 리스트
  const [description, setDescription] = useState([])                //상품설명사진 리스트
  const [preViewDescription, setPreViewDescription] = useState([])  //상품설명사진 미리보기 리스트

  const savePreViewFiles=(e)=>{
    const file = e.target.files;
    setGoodsImg(file)
    let fileUrl = [];
    for(let i=0; i<file.length;i++){
      const render = new FileReader()
      render.readAsDataURL(file[i])
      render.onload = () =>{
        fileUrl[i] =render.result
        setPreViewGoodsImg([...fileUrl])
        setMain(fileUrl[0])
      }
    }
    console.log(goodsImg,': 보낼이미지')
    console.log(preViewGoodsImg,':미리보기이미지')
  }
  const savePreViewImg=(e)=>{
    const file = e.target.files;
    setDescription(file)
    let fileUrl = [];
    for(let i=0; i<file.length;i++){
      const render = new FileReader()
      render.readAsDataURL(file[i])
      render.onload = () =>{
        fileUrl[i] =render.result
        setPreViewDescription([...fileUrl])
      }
    }
    console.log(description,': 보낼 설명이미지')
    console.log(preViewDescription,':미리보기 설명이미지')
  }

  return (
    <div>
      <div style={{height:'100px'}}><NavBar/></div>
      <SideBar/>
      <div className={styles.container}>
        <div style={{width:'fit-content'}}>
          {/* 상품 맨 위 이미지 및 정보칸 */}
          <div className={styles.itemTop_box} >
            {/* 1.상품 사진 슬라이더? 왼쪽, 자동회전 x  */}
            <div>
              <div className={styles.top_image_box}>
                <img src={main} alt='상품 이미지'/>
              </div>
              <ul className={styles.imgList}>
                {preViewGoodsImg.map((item,index)=>(
                  <li className={hover==index ? styles.hoverImg : ''} key={index}>
                    <img src={preViewGoodsImg[index]} alt='이미지' onMouseOver={()=>{handleHover(item,index)}}/>
                    <button className={hover==index ? styles.hoverBtn : styles.hoverBtn_none}>삭제</button>
                  </li>
                ))}
              </ul>
              {goodsImg.length < 7 && (
                  <div className={styles.uploadGoodsImg}>
                    <label htmlFor='uploadGoodsImg'>업로드</label>
                    <input id='uploadGoodsImg' onChange={savePreViewFiles} type='file' accept='image/jpeg, image/jpg, image/png' style={{display:"none"}} multiple required/>
                  </div>
                )}
            </div>
            
            {/* 2. 상품 정보 오른쪽 */}
            <div className={styles.top_info_box}>
              {/* 제품정보 */}
              <div className={styles.Info_box} style={{borderTop:'1px solid #888'}}>
                <div className={styles.Info_top}>
                  <h3>Product Info</h3>
                  <p>제품정보</p>
                </div>
                <ul className={styles.Info_bot}>
                  <li className={styles.info}>
                    <div className={`${styles.infoTitle} ${styles.infoBrand}`}>
                      <p>브렌드</p>
                      <p>품번</p>
                    </div>
                    <div className={`${styles.infoContent} ${styles.infoBrand}`}>
                      <p>EMIS</p>
                      <input/>
                    </div>
                  </li>
                  <li className={styles.info}>
                    <div className={styles.infoTitle}>
                      <p>상품이름</p>
                    </div>
                    <div className={styles.infoContent}>
                      <input/>
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
                      <input/>원
                    </div>
                  </li>
                </ul>
              </div>

              {/* 옵션 선택 및 구매 버튼 */}
              <div className={styles.Info_box} style={{display:'flex', justifyContent:'center', alignItems:'center', flexDirection:'column'}}>
                {/* 옵션 선택 */}
                <div className={styles.selectBox}> 
                  <OptionAdd/>
                </div>
                <div className={styles.selectBox}> 
                  <OptionAdd/>
                </div>
                {/* 구매 버튼 */}
                <div className={styles.buyBtn}>
                <button>
                  등록
                </button>
                </div>
              </div>
            </div>
          </div>
          {/* 상품 설명 이미지 */}  
          <div className={styles.itemBot_box}>
              <div className={styles.descriptionImg}>
                {preViewDescription.map((item,index)=>(
                  <img src={item} key={index} alt='이미지'/>
                ))}
              </div>
              <div className={styles.description_Btn}>
                <label htmlFor='descriptionImg'>업로드</label>
                <input id='descriptionImg' onChange={savePreViewImg} type='file' accept='image/jpeg, image/jpg, image/png' style={{display:"none"}} multiple required/>
              </div>
            </div>
        </div>
      </div>
      <Footer/>
    </div>
  )
}

export default AddGoods


{/* 배송정보 */}
              {/* <div className={styles.Info_box}>
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
                    <div className={`${styles.infoContent} ${styles.infoBrand}`}>
                      <p>국내배송</p>
                      <p>입점사 배송</p>
                    </div>
                  </li>
                </ul>
              </div> */}