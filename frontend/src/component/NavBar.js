import { useDispatch, useSelector } from 'react-redux'
import axios from 'axios';
import logo from '../image/로고.png'
import {useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { loginActions } from '../redux/reducer/pageReducer/loginReducer'
import styles from '../styles/componentStyle/navbar.module.scss'
import useAxiosInstance from '../api/axiosInstance';


const NavBar = () => {

  const dispatch = useDispatch()
  const navigate = useNavigate()
  const axiosInstance=useAxiosInstance()

  const memberId = useSelector((state)=>state.login.memberId)
  const isLogin = useSelector((state)=>state.login.isLogin)
  const loginType = useSelector((state)=>state.login.loginType)
  const accessToken = useSelector((state) => state.login.accessToken);

  //스크롤 감지 변수
  const [up,setUp] = useState(true);
  const [preScroll,setPreScroll] = useState(0)

  //top메뉴 작동함수
  const [handleMenu, setHandleMenu] = useState(false)

  //로그인 감지함수
  useEffect (()=>{
    // 세션 스토리지에서 리프레시 토큰 가져오기
    const refreshToken = sessionStorage.getItem('refreshToken');
    const type = sessionStorage.getItem('loginType')
  
    if (!refreshToken || refreshToken === '') {
      dispatch(loginActions.setLogOut());
    } 
    else {
      dispatch(loginActions.setLogin());
      dispatch(loginActions.setLoginType(type))
    }
  }, []);

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
  const  logout = (e)=>{

    e.preventDefault();

    const refreshToken = sessionStorage.getItem('refreshToken');

    axiosInstance.post('/member/logout', {
      memberId: memberId
    }, {
        headers: {
            Refresh: refreshToken
        }
    })
    .then(response =>{
      console.log(response)
      dispatch(loginActions.LogOut())
      sessionStorage.removeItem('refreshToken')
      navigate('/')
    })
    .catch(error=>{
      console.log(error)
    })
  }

  return (
    <div className={`${up ? styles.body : styles.body_down}`}>
      {isLogin?(
        <header>
          {loginType==='public' &&(
            <span className={styles.header_button} onClick={()=>{goMyPage()}}>마이페이지</span>
          )}
          {loginType==='admin' &&(
            <span className={styles.header_button} onClick={()=>{goAddGoods()}}>상품등록</span>
          )}
          <span style={{width:'auto',cursor:'default'}}>ㅣ</span>
          <span className={styles.header_button} onClick={(e)=>{logout(e)}}>로그아웃</span>
        </header>
      ):(
        <header>
          <span className={styles.header_button} onClick={()=>{goSignIn()}}>회원가입</span>
          <span style={{width:'auto',cursor:'default'}}>ㅣ</span>
          <span className={styles.header_button} onClick={()=>{goLoginSelect()}}>로그인</span>
        </header>
      )}
      {handleMenu && (
      <div className={styles.topMenu_layout}>
        <dl className={styles.topMenu_first}>
          <dt>
            outer
          </dt>
          <dd>
            <ul>
              <li><a href='#'>아우터 전체</a></li>
              <li><a href='#'>슈트/블레이저</a></li>
              <li><a href='#'>코트</a></li>
              <li><a href='#'>패딩</a></li>
              <li><a href='#'>가디건</a></li>
              <li><a href='#'>블루종/MA-1</a></li>
              <li><a href='#'>후드집업</a></li>
              <li><a href='#'>더보기</a></li>
            </ul>
          </dd>
        </dl>
        <div className={styles.topMenu_first}>top</div>
        <div className={styles.topMenu_first}>pants</div>
        <div className={styles.topMenu_first}>eto</div>
      </div>
        )}
      <main>
        <div className={styles.iconBox} onClick={()=>{setHandleMenu(!handleMenu)}}>
          <section>
            <div></div>
            <div></div>
            <div></div>
          </section>
        </div>
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