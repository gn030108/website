import { useNavigate } from "react-router-dom";
import styles from "../styles/componentStyle/sideMenu.module.scss";
import useDispatch from "react-redux";

const SideMenu = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const goList = () => {
    navigate("/List");
  };

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
          <a href="#!">Outer</a>
          <ul>
            <li onClick={() => goList()}>
              <a href="#!">아우터 전체</a>
            </li>
            <li onClick={() => goList()}>
              <a href="#!">슈트/블레이저</a>
            </li>
            <li onClick={() => goList()}>
              <a href="#!">코트</a>
            </li>
            <li onClick={() => goList()}>
              <a href="#!">패딩</a>
            </li>
            <li onClick={() => goList()}>
              <a href="#!">가디건</a>
            </li>
            <li onClick={() => goList()}>
              <a href="#!">블루종/MA-1</a>
            </li>
            <li onClick={() => goList()}>
              <a href="#!">후드집업</a>
            </li>
            <li onClick={() => goList()}>
              <a href="#!">더보기</a>
            </li>
          </ul>
        </li>
        <li>
          <a href="#!">Top</a>
          <ul>
            <li onClick={() => goList()}>
              <a href="#!">상의 전체</a>
            </li>
            <li onClick={() => goList()}>
              <a href="#!">맨투맨/스웨트셔츠</a>
            </li>
            <li onClick={() => goList()}>
              <a href="#!">긴소메 티셔츠</a>
            </li>
            <li onClick={() => goList()}>
              <a href="#!">반소메 티셔츠</a>
            </li>
            <li onClick={() => goList()}>
              <a href="#!">셔츠/블라우스</a>
            </li>
            <li onClick={() => goList()}>
              <a href="#!">후드 티셔츠</a>
            </li>
            <li onClick={() => goList()}>
              <a href="#!">피케/카라 티셔츠</a>
            </li>
            <li onClick={() => goList()}>
              <a href="#!">더보기</a>
            </li>
          </ul>
        </li>
        <li>
          <a href="#!">Pants</a>
          <ul>
            <li onClick={() => goList()}>
              <a href="#!">하의 전체</a>
            </li>
            <li onClick={() => goList()}>
              <a href="#!">데님 팬츠</a>
            </li>
            <li onClick={() => goList()}>
              <a href="#!">코튼 팬츠</a>
            </li>
            <li onClick={() => goList()}>
              <a href="#!">슈트 팬츠/슬랙스</a>
            </li>
            <li onClick={() => goList()}>
              <a href="#!">트레이닝/조거팬츠</a>
            </li>
            <li onClick={() => goList()}>
              <a href="#!">숏 팬츠</a>
            </li>
            <li onClick={() => goList()}>
              <a href="#!">레깅스</a>
            </li>
            <li onClick={() => goList()}>
              <a href="#!">더보기</a>
            </li>
          </ul>
        </li>
      </ul>
    </div>
  );
};

export default SideMenu;
