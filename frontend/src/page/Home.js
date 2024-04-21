import NavBar from '../component/NavBar'
import SideBar from '../component/SideBar'
import SideMenu from '../component/SideMenu'
import Card from '../component/Card'
import { useSelector } from 'react-redux'
import styles from '../styles/pageStyle/home.module.scss'
import Footer from '../component/Footer'


const Home = () => {

  const loginType = useSelector((state)=>state.login.loginType)


  return (
    <div>
      <div style={{height:'100px'}}><NavBar/></div>
      {loginType != 'admin' && (
        <SideMenu/>
      )}
      <SideBar/>
      <div className={styles.container}>
        <div style={{width:'fit-content'}}>
            <Card/>
        </div>
      </div>
      <Footer/>
    </div>
  )
}

export default Home