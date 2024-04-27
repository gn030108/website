import { useDispatch, useSelector } from 'react-redux'
import NavBar from '../component/NavBar'
import SideBar from '../component/SideBar'
import SideMenu from '../component/SideMenu'
import styles from '../styles/pageStyle/login.module.scss'
import { loginActions } from '../redux/reducer/pageReducer/loginReducer'
import { useEffect } from 'react'
import { useNavigate } from 'react-router-dom';
import useAxiosInstance from '../api/axiosInstance'

const Login = () => {

  const dispatch = useDispatch()
  const navigate = useNavigate()
  const axiosInstance = useAxiosInstance()

  const memberId = useSelector((state)=>state.login.memberId)
  const password = useSelector((state)=>state.login.password)

  const handleInputChange = (e) => {
    const { name, value } = e.target;

    if (name==='id'){
        dispatch(loginActions.getId(value))
    }
    else if (name==='pw'){
        dispatch(loginActions.getPw(value))
    }
}

  const Login = (e)=>{
    e.preventDefault();

    axiosInstance.post('/member/login',{
      memberId,password
    })
    .then(response =>{
      //accessToken 저장 (리덕스)
      dispatch(loginActions.getAccessToken(response.data.accessToken))
      //refreshToken 저장 (세션스토리지)
      window.sessionStorage.setItem('refreshToken',response.data.refreshToken)
      dispatch(loginActions.LogIn('public'))
      navigate('/')
    })
    .catch(error=>{
      console.log('아이디 비번 :',memberId,password)
      console.log(error)
    })
  }
  

  return (
    <div>
      <div className={styles.navbar}><NavBar/></div>
      <SideMenu/>
      <SideBar/>
      <div className={styles.layout}>
        <form>
          <input  placeholder='아이디' name='id' value={memberId} onChange={handleInputChange} autoComplete='off'/>
          <input  placeholder='비밀번호' name='pw' value={password} type='password' onChange={handleInputChange} autoComplete='off'/>
          <button onClick={(e)=>{Login(e)}}>로그인</button>
        </form>
      </div>
    </div>
  )
}

export default Login