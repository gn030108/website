import Footer from '../component/Footer'
import NavBar from '../component/NavBar'
import SideBar from '../component/SideBar'
import SideMenu from '../component/SideMenu'
import Card from '../component/Card'
import Paging from '../component/Paging'
import { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux'
import 김민주 from '../image/김민주.png'
import styles from '../styles/pageStyle/myPage.module.scss'
import InfoModal from '../component/InfoModal'
import PasswordModal from '../component/PasswordModal'
import ReviewModal from '../component/ReviewModal'
import { modalActions } from '../redux/reducer/componentReducer/modalReducer'

const MyPage = () => {

  const dispatch = useDispatch()

  const [page,setPage] = useState(1)
  const [infoModal, setInfoModal] = useState(false)
  const [passwordModal, setPasswordModal] = useState(false)
  const [reviewModal, setReviewModal] = useState(false)
  
  const orderInfo = useSelector((state)=>state.myPage.orderInfo)
  const reviewInfo = useSelector((state)=>state.modal.reviewInfo)

  const openReviewModal = ({item})=>{
    dispatch(modalActions.getReviewInfo(item))
    setReviewModal(!reviewModal)
  }

  return (
    <>
      {infoModal && 
        <InfoModal
          modal = {infoModal}
          setModal = {setInfoModal}
        />
      }{/* modal이 참이 되면 열리는 modal컴포넌트 */}
      {passwordModal && 
        <PasswordModal
          modal = {passwordModal}
          setModal = {setPasswordModal}
        />
      }{/* modal이 참이 되면 열리는 modal컴포넌트 */}
      {reviewModal && 
        <ReviewModal
          modal = {reviewModal}
          setModal = {setReviewModal}
        />
      }{/* modal이 참이 되면 열리는 modal컴포넌트 */}
      <div>
        <div style={{height:'100px'}}><NavBar/></div>
        <SideMenu/>
        <SideBar/>
        <div className={styles.container}>
          <div className={styles.title_box}>
            <div>
              <p className={styles.title}>MyPage</p>
              <div className={styles.modal_box}>
                <p onClick={()=>{setPasswordModal(!passwordModal)}}>비밀번호변경</p>{/* onclick 달기 */}
                <p onClick={()=>{setInfoModal(!infoModal)}}>회원정보변경</p>{/* onclick 달기 */}
              </div>
            </div>
          </div>

          <div className={styles.orderHistory_box}>
            <div className={styles.orderHistory_title}>
              <p>주문내역조회</p>
              <p>입금/결제</p>
              <p>배송중</p>
              <p>배송완료</p>
              <p>구매확정</p>
              <p>교환완료</p>
              <p>환불완료</p>
            </div>

            <div className={styles.category}>   
              <p>상품정보</p>
              <p>주문일자</p>
              <p>주문번호</p>
              <p>주문금액 (수량)</p>
              <p>주문상태</p>
            </div>
            {/* 주문정보 */}
            {orderInfo.map((item,index)=>(
              <div className={styles.orderInfo} key={index}>
              <div className={styles.item}>
                <div>
                  <img src={item.img} alt='이미지'/>
                </div>
                <div>
                  <p>{item.brand}</p>
                  <p>{item.name}</p>
                  <p>옵션 : {item.option}</p>
                </div>
              </div>
              <div className={styles.info_box}>
                <p>{item.date}</p>
              </div>
              <div className={styles.info_box}>
                <p>{item.number}</p>
              </div>
              <div className={styles.info_box}>
                <p>{item.price}원</p>
                <p style={{color:'#BBBBBB'}}>{item.count}개</p>
              </div>
              <div className={styles.info_box_last}>
                <p>{item.delivery}</p>
                <button onClick={()=>{openReviewModal({item})}}>후기작성</button>
              </div>
            </div>
            ))}
          </div>
            {/* 페이징 */}
          <div className={styles.paging_box}> 
            <Paging //받아야할 변수 = totalElements
            totalElements={100}//전체 상품 갯수
            limit={10}//한페이지에 보여줄 아이템 갯수
            displayPage={5}// 보여줄 페이지 갯수
            page={page}//현재 페이지 번호
            setPage={setPage}
            />
          </div>
        </div>
        <Footer/>
      </div>
    </>
  )
}

export default MyPage