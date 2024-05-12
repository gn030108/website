import NavBar from '../component/NavBar'
import SideBar from '../component/SideBar'
import Card from '../component/Card'
import { useEffect, useState } from 'react';
import styles from '../styles/pageStyle/list.module.scss'
import Paging from '../component/Paging';
import { useSelector } from 'react-redux';
const List = () => {

  const [page,setPage] = useState(1)
  const [title,setTitle] = useState('')
  const [subheader,setSubheader] = useState([])
  const [brand,setBrand] = useState([])
  const type = useSelector((state)=>state.navbar.type)

  useEffect(()=>{
    if (type === 'top') {
      setTitle('Top')
      setSubheader(['전체','맨투맨/스웨트셔츠', '니트/스웨터', '긴소매 티셔츠', '반소매 티셔츠', '셔츠/블라우스', '후드 티셔츠','기타 상의', '민소매 티셔츠', '스포츠 상의'])
      setBrand(['무신사 스탠다드', '예일', '커버낫', '닉앤니콜', '디스이스네버댓', '와릿이즌', '오아이오아이컬렉션','꼼파뇨','리', '디스커버리 익스페디션', '폴로 랄프 로렌', '어반드레스'])
    }
    else if (type ==='outer'){
      setTitle('Outer')
      setSubheader(['전체','슈트/블레이저', '코트', '패딩', '가디건', '블루종/MA-1', '후드집업', '베스트','기타 아우터', '트레이닝 재킷'])
      setBrand(['무신사 스탠다드', '예일', '커버낫', '닉앤니콜', '디스이스네버댓', '와릿이즌', '오아이오아이컬렉션','노스페이스','리', '굿라이프웍스', '코드그라피', '디스이즈네버댓'])
    }
    else if (type ==='pants'){
      setTitle('Pants')
      setSubheader(['전체','데님 팬츠', '코튼 팬츠', '슈트 팬츠/슬랙스', '트레이닝/조거팬츠', '숏 팬츠', '레깅스', '점프 슈트/오버올', '기타 바지', '스포츠 하의',])
      setBrand(['무신사 스탠다드', '노스페이스', '디스이즈네버댓', '아디다스', '커버낫', '오아이오아이컬렉션', '토피','리','굿라이프웍스', '에이치 덱스', '브라운브레스', '어반드레스'])
    }

  },[type])

  return (
    <div>
      <div className={styles.navbar}><NavBar/></div>
      <SideBar/>
      <div className={styles.container}>
            <div className={styles.category}>
              

              <div className={styles.title}>{title}</div>

              <dl className={styles.contents_box}>
                <dt className={styles.subheader}>분류</dt>
                <dd className={styles.contents}>
                  <ul>
                    {subheader.map((item,index)=>(
                      <li key={index}><a href='#'>{item}</a></li>
                    ))}
                  </ul>
                </dd>
              </dl>

              <dl className={styles.contents_box}>
                <dt className={styles.subheader}>브랜드</dt>
                <dd className={styles.contents}>
                  <ul>
                    {brand.map((item,index)=>(
                        <li key={index}><a href='#'>{item}</a></li>
                      ))}
                  </ul>
                </dd>
              </dl>

              <dl className={styles.contents_box}>
                <dt className={styles.subheader}>가격</dt>
                <dd className={styles.contents}>
                  <ul>
                    <li><a href='#'>전체보기</a></li>
                    <li><a href='#'>~ 50,000</a></li>
                    <li><a href='#'>50,000 ~ 100,000</a></li>
                    <li><a href='#'>100,000 ~ 200,000</a></li>
                    <li><a href='#'>200,000 ~ 300,000</a></li>
                    <li><a href='#'> 300,000 ~ </a></li>
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