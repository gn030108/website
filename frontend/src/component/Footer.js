import styles from '../styles/componentStyle/footer.module.scss'

const Footer = () => {
  return (
    <div className={styles.container}>
      <div className={styles.boxLayout}>
        <div className={styles.box_first}>
          <p>Cloop</p>
          <p>(45678) 서울특별시 xxx로 xx 1층</p>
        </div>
        <div className={styles.box_second}>
          <p>전화번호 : 010-1234-4567</p>
          <p>평일 오전9시 ~ 오후6시</p>
          <p>Fax : 1234-5678</p>
        </div>
      </div>
    </div>
  )
}

export default Footer