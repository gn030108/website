import { useNavigate } from 'react-router-dom';
import styles from '../styles/componentStyle/sideMenu.module.scss'
import { useDispatch, useSelector } from 'react-redux';

const SideMenu = () => {

  const dispatch = useDispatch()
  const navigate = useNavigate()

  const goList = ()=>{
    navigate("/List")
  }

  return (
    <div className={styles.sideMenu}>
      {/*사이드바 변화하는 아이콘 */}
      <div className={styles.iconBox}>
        <section>
          <div></div>
          <div></div>
          <div></div>
        </section>
      </div>
      <ul className={styles.firstUl}>
        <li>
          <a href='#!'>menu1</a>
          <ul>
            <li onClick={()=>(goList())}><a href='#!'>text1</a></li>
            <li onClick={()=>(goList())}><a href='#!'>text2</a></li>
            <li onClick={()=>(goList())}><a href='#!'>text3</a></li>
            <li onClick={()=>(goList())}><a href='#!'>text4</a></li>
          </ul>
        </li>
        <li>
          <a href='#!'>menu2</a>
          <ul>
            <li onClick={()=>(goList())}><a href='#!'>text1</a></li>
            <li onClick={()=>(goList())}><a href='#!'>text2</a></li>
            <li onClick={()=>(goList())}><a href='#!'>text3</a></li>
            <li onClick={()=>(goList())}><a href='#!'>text4</a></li>
          </ul>
        </li>
        <li>
          <a href='#!'>menu3</a>
          <ul>
          <li onClick={()=>(goList())}><a href='#!'>text1</a></li>
            <li onClick={()=>(goList())}><a href='#!'>text2</a></li>
            <li onClick={()=>(goList())}><a href='#!'>text3</a></li>
            <li onClick={()=>(goList())}><a href='#!'>text4</a></li>
          </ul>
        </li>
        
      </ul>
    </div>
  )
}

export default SideMenu