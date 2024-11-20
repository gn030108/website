import styles from "../styles/componentStyle/sidebar.module.scss";

const SideBar = () => {
  const MoveToTop = () => {
    // top:0 >> 맨위로  behavior:smooth >> 부드럽게 이동할수 있게 설정하는 속성
    window.scrollTo({ top: 0, behavior: "smooth" });
  };

  const MoveToBot = () => {
    // 스크롤의 최하단으로 이동
    window.scrollTo({
      top: document.documentElement.scrollHeight,
      behavior: "smooth",
    });
  };

  return (
    <div className={styles.sidebar}>
      <div className={styles.button}>
        <i
          className={`ri-arrow-up-s-line ${styles.icon}`}
          onClick={() => {
            MoveToTop();
          }}
        ></i>
      </div>

      <div className={styles.button}>
        <i
          className={`ri-arrow-down-s-line ${styles.icon}`}
          onClick={() => {
            MoveToBot();
          }}
        ></i>
      </div>
    </div>
  );
};

export default SideBar;
