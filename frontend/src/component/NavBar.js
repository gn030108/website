import { useDispatch, useSelector } from 'react-redux'
import logo from '../image/로고.png'
import {useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { loginActions } from '../redux/reducer/pageReducer/loginReducer'
import styles from '../styles/componentStyle/navbar.module.scss'

const NavBar = () => {

  const dispatch = useDispatch()
  const navigate = useNavigate()

  const isLogin = useSelector((state)=>state.login.isLogin)
  const loginType = useSelector((state)=>state.login.loginType)

  //스크롤 감지 변수
  const [up,setUp] = useState(true);
  const [preScroll,setPreScroll] = useState(0)


  //스크롤 감지 함수
  useEffect(()=>{
    //스크롤을 감지해 position 값을 변경
    const onScroll= () => {
      const nowScroll = window.scrollY;
      //스크롤 위쪽
      if (nowScroll<preScroll){
        setUp(true)
      }
      //스크롤 아래쪽 
      else if (nowScroll>preScroll){
        setUp(false)
      }
      setPreScroll(nowScroll)
    }
    window.addEventListener('scroll',onScroll);

    return () =>{
      window.removeEventListener('scroll',onScroll);
    }
  });

  const goHome = ()=>{
    navigate('/')
  }
  const goLoginSelect =()=>{
    navigate('/LoginSelect')
  }
  const goSignIn =()=>{
    navigate('/SignIn')
  }
  const goLike =()=>{
    navigate('/Like')
  }
  const goBasket =()=>{
    navigate('/Basket')
  }
  const goMyPage=()=>{
    navigate('/MyPage')
  }
  const goAddGoods=()=>{
    navigate('/AddGoods')
  }
  const logout = ()=>{
    dispatch(loginActions.LogOut())
  }

  return (
    <div className={`${up ? styles.body : styles.body_down}`}>
        {isLogin?(
          <header>
            {loginType=='public' &&(
              <span className={styles.header_button} onClick={()=>{goMyPage()}}>마이페이지</span>
            )}
            {loginType=='admin' &&(
              <span className={styles.header_button} onClick={()=>{goAddGoods()}}>상품등록</span>
            )}
            <span style={{width:'auto',cursor:'default'}}>ㅣ</span>
            <span className={styles.header_button} onClick={()=>{logout()}}>로그아웃</span>
          </header>
        ):(
          <header>
            <span className={styles.header_button} onClick={()=>{goSignIn()}}>회원가입</span>
            <span style={{width:'auto',cursor:'default'}}>ㅣ</span>
            <span className={styles.header_button} onClick={()=>{goLoginSelect()}}>로그인</span>
          </header>
        )}

      <main>
        <div className={styles.left}>
          <span>
            <img src={logo} onClick={()=>{goHome()}} className={styles.logo} alt='로고'/>
          </span>
        </div>

        <div className={styles.info}>
          <span className={styles.search}>
            <input className={styles.search_input} placeholder='검색'/>
            <i className={`ri-search-line ${styles.search_icon}`}></i>
          </span>
          <span className={styles.icon} onClick={()=>(goBasket())}>
            <i className="ri-shopping-bag-line"></i>
          </span>
          <span className={styles.icon} onClick={()=>(goLike())}>
            <i className="ri-heart-line"></i>
          </span>
        </div>
      </main>
    </div>
  )
}

export default NavBar