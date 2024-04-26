import Footer from '../component/Footer'
import NavBar from '../component/NavBar'
import SideBar from '../component/SideBar'
import SideMenu from '../component/SideMenu'
import { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux'
import styles from '../styles/pageStyle/basket.module.scss'
import 김민주 from '../image/김민주.png'
import PaymentModal from '../component/PaymentModal'
import { basketActions } from '../redux/reducer/pageReducer/basketReducer'

const Basket = () => {

  const dispatch= useDispatch()

  const [paymentModal, setPaymentModal] = useState(false)

  //체크리스트 데이터 가정
  const items = [
    {id:1,img:김민주,name:'김민주사진',option1:'M',option2:'린넨', price :'10000', count:'2'},
    // {id:2,img:김민주,name:'김민주사진',option1:'M',option2:'린넨', price :'10000', count:'2'},
    // {id:3,img:김민주,name:'김민주사진',option1:'M',option2:'린넨', price :'10000', count:'2'},
    // {id:4,img:김민주,name:'김민주사진',option1:'M',option2:'린넨', price :'10000', count:'2'},
  ]

  const checkItems = useSelector((state)=>state.basket.checkItems)


    // 체크박스 단일 선택
    const handleSingleCheck = (checked, id) => {
      if (checked) {
        // 단일 선택 시 체크된 아이템을 배열에 추가
        dispatch(basketActions.getCheck(id))

      } 
      else {
        // 단일 선택 해제 시 체크된 아이템을 제외한 배열 (필터)
        dispatch(basketActions.getCheckOut(id))
      }
    }
    ;
    // 체크박스 전체 선택
    const handleAllCheck = (checked) => {
      if(checked) {
        // 전체 선택 클릭 시 데이터의 모든 아이템(id)를 담은 배열로 checkItems 상태 업데이트
        const idArray = items.map((item) => item.id);
        dispatch(basketActions.all(idArray));
      }
      else {
        // 전체 선택 해제 시 checkItems 를 빈 배열로 상태 업데이트
        dispatch(basketActions.all([]))
      }
    }

  return (
    <>
      {paymentModal && 
        <PaymentModal
          modal = {paymentModal}
          setModal = {setPaymentModal}
        />
      } {/* modal이 참이 되면 열리는 modal컴포넌트 */}

      <div>
        <div style={{height:'100px'}}><NavBar/></div>
        <SideMenu/>
        <SideBar/>
        <div className={styles.container}>
          <div className={styles.title_box}>
            <p>장바구니</p>
          </div>
          {/* 장바구니 내역 */}
          <div className={styles.basket_box}>
            {/* 내역 종류 */}
            <div className={styles.option_box}>
              {/* 전체x개 상품명(옵션) 판매가 수량 주문금액 주문관리(삭제버튼) */}
              <span>전체{items.length}개</span>
              <input type='checkbox' name='select-all'
                onChange={(e) => handleAllCheck(e.target.checked)}
                // 데이터 개수와 체크된 아이템의 개수가 다를 경우 선택 해제 (하나라도 해제 시 선택 해제)
                checked={checkItems.length === items.length ? true : false} />
              <span>상품명(옵션)</span>
              <span>판매가</span>
              <span>수량</span>
              <span>주문금액</span>
              <span>주문관리</span>
            </div>


            {/* const items = [
                          {itemImg:{김민주},itemName:'김민주사진',option:M, cost :'10000', count:'2', totalCost:cost*count,}
                            ] */}
            {/* 장바구니 내용 */}
            {items.map((item,index)=>(
              <div className={styles.content_box} key={index}>
                <span>{item.id}</span>
                <input 
                  type='checkbox' name={`select-${item.id}`}
                  onChange={(e) => handleSingleCheck(e.target.checked, item.id)}
                  // 체크된 아이템 배열에 해당 아이템이 있을 경우 선택 활성화, 아닐 시 해제, 장바구니에서 미리 선택했던 상품에 대해 체크를 해두기 위함
                  checked={checkItems.includes(item.id) ? true : false} 
                />
                <span className={styles.itemInfo}>
                  <img src={item.img} alt='이미지'/>
                  <div>
                    <p>{item.name}</p>
                    <p>옵션 : {item.option1} / {item.option2}</p>
                  </div>
                </span>
                <span>{item.price}원</span>
                <span>{item.count}</span>
                <span>{item.price * item.count}원</span>
                <span>
                  <button>삭제</button>
                </span>
            </div>
            ))}
            {/* {items.map((item, index)=>(
              <div className={styles.content_box} key={index}>
              <span>{item.id}</span>
              <input 
                type='checkbox' name={`select-${item.id}`}
                onChange={(e) => handleSingleCheck(e.target.checked, item.id)}
                // 체크된 아이템 배열에 해당 아이템이 있을 경우 선택 활성화, 아닐 시 해제, 장바구니에서 미리 선택했던 상품에 대해 체크를 해두기 위함
                checked={checkItems.includes(item.id) ? true : false} 
              />
              <img src={item.img} alt='이미지'/>
              <span className={styles.itemInfo}>
                
                <div>
                  <p>{item.name}</p>
                  <p>옵션 : {item.option1} / {item.option2}</p>
                </div>
                <div>
                  <div>{item.price}원</div>
                  <div>{item.count}개</div>
                </div>
                
                <div>{item.price * item.count}원</div>
                <div>
                  <button>삭제</button>
                </div>
              </span>
          </div>
            ))} */}
          </div>
          {/* 주문하기버튼 */}
          <div className={styles.button_box}>
            <button onClick={()=>{setPaymentModal(!paymentModal)}}>
              주문하기
            </button>
          </div>
        </div>
        <Footer/>
      </div>
    </>
    
  )
}

export default Basket