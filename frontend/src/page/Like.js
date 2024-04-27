import Footer from '../component/Footer'
import NavBar from '../component/NavBar'
import SideBar from '../component/SideBar'
import SideMenu from '../component/SideMenu'
import Card from '../component/Card'
import Paging from '../component/Paging'
import { useEffect, useState } from 'react';
import styles from '../styles/pageStyle/like.module.scss'

const Like = () => {

  const [page,setPage] = useState(1)

  return (
    <div>
      <div className={styles.navbar}><NavBar/></div>
      <SideMenu/>
      <SideBar/>
      <div className={styles.container}>
        <div className={styles.title_box}>
          <p>좋아요</p>
        </div>
          
        <div className={styles.card_box}>
          <Card/>
        </div>
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
  )
}

export default Like