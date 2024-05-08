import NavBar from '../component/NavBar'
import SideBar from '../component/SideBar'
import Card from '../component/Card'
import { useEffect, useState } from 'react';
import styles from '../styles/pageStyle/list.module.scss'
import Paging from '../component/Paging';
const List = () => {

  const [page,setPage] = useState(1)

  return (
    <div>
      <div className={styles.navbar}><NavBar/></div>
      <SideBar/>
      <div className={styles.container}>
            <div className={styles.category}>

              <div className={styles.title}>Top</div>

              <dl className={styles.contents_box}>
                <dt className={styles.subheader}>분류</dt>
                <dd className={styles.contents}>
                  <ul>
                    <li><a href='#'>맨투맨/스웨트셔츠</a></li>
                    <li><a href='#'>니트/스웨터</a></li>
                    <li><a href='#'>긴소메 티셔츠</a></li>
                    <li><a href='#'>반소메 티셔츠</a></li>
                    <li><a href='#'>셔츠/블라우스</a></li>
                    <li><a href='#'>후드 티셔츠</a></li>
                    <li><a href='#'>피케/카라 티셔츠</a> </li>
                    <li><a href='#'>기타 상의</a></li>
                    <li><a href='#'>민소메 티셔츠</a></li>
                    <li><a href='#'>스포츠 상의</a></li>
                  </ul>
                </dd>
              </dl>

              <dl className={styles.contents_box}>
                <dt className={styles.subheader}>브랜드</dt>
                <dd className={styles.contents}>
                  <ul>
                    <li><a href='#'>무신사 스탠다드</a></li>
                    <li><a href='#'>예일</a></li>
                    <li><a href='#'>커버낫</a></li>
                    <li><a href='#'>닉앤니콜</a></li>
                    <li><a href='#'>디스이즈네버댓</a></li>
                    <li><a href='#'>와릿이즌</a></li>
                    <li><a href='#'>오아이오아이컬렉션</a></li>
                    <li><a href='#'>꼼파뇨</a></li>
                    <li><a href='#'>리</a></li>
                    <li><a href='#'>디스커버리 익스페디션</a></li>
                    <li><a href='#'>폴로 랄프 로렌</a></li>
                    <li><a href='#'>어반드레스</a></li>
                  </ul>
                </dd>
              </dl>

              <dl className={styles.contents_box}>
                <dt className={styles.subheader}>가격</dt>
                <dd className={styles.contents}>
                  <ul>
                    <li><a href='#'>전체보기</a></li>
                    <li><a href='#'>~ 50,000원</a></li>
                    <li><a href='#'>50,000원 ~ 100,000원</a></li>
                    <li><a href='#'>100,000원 ~ 200,000원</a></li>
                    <li><a href='#'>200,000원 ~ 300,000원</a></li>
                    <li><a href='#'> 300,000원 ~ </a></li>
                  </ul>
                </dd>
              </dl>
              <dl className={styles.search_box}>
                <dt className={styles.subheader}>검색</dt>
                <dd>
                  <input/>
                  <button>검색</button>
                </dd>
              </dl>
            </div>
            <Card/>
            <Paging //받아야할 변수 = totalElements
              totalElements={100}//전체 상품 갯수
              limit={10}//한페이지에 보여줄 아이템 갯수
              displayPage={5}// 보여줄 페이지 갯수
              page={page}//현재 페이지 번호
              setPage={setPage}
            />
        </div>
    </div>
  )
}

export default List