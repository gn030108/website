import { useDispatch, useSelector } from 'react-redux'
import NavBar from '../component/NavBar'
import SideBar from '../component/SideBar'
import SideMenu from '../component/SideMenu'
import styles from '../styles/pageStyle/login.module.scss'
import { loginActions } from '../redux/reducer/pageReducer/loginReducer'
import { useEffect } from 'react'
import { useNavigate } from 'react-router-dom';

const AdminLogin = () => {

  const dispatch = useDispatch()
  const navigate = useNavigate()

  const id = useSelector((state)=>state.login.id)
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

  const Login = ()=>{
    dispatch(loginActions.LogIn('admin'))
    navigate('/')
  }
  

  return (
    <div>
      <div style={{height:'100px'}}><NavBar/></div>
      <div className={styles.SideMenu}><SideMenu/></div>
      <SideBar/>
      <div className={styles.layout}>
        <form>
          <input  placeholder='아이디' name='id' value={id} onChange={handleInputChange} autoComplete='off'/>
          <input  placeholder='비밀번호' name='pw' value={password} type='password' onChange={handleInputChange} autoComplete='off'/>
          <button onClick={()=>{Login()}}>로그인</button>
        </form>
      </div>
    </div>
  )
}

export default AdminLogin