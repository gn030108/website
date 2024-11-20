import NavBar from "../component/NavBar";
import SideBar from "../component/SideBar";
import styles from "../styles/pageStyle/loginSelect.module.scss";
import Footer from "../component/Footer";
import { useNavigate } from "react-router-dom";

const LoginSelect = () => {
  const navigate = useNavigate();

  const goLogin = () => {
    navigate("/Login");
  };

  const goAdminLogin = () => {
    navigate("/AdminLogin");
  };
  return (
    <div>
      <div className={styles.navbar}>
        <NavBar />
      </div>
      <SideBar />
      <div className={styles.layout}>
        <div
          onClick={() => {
            goLogin();
          }}
        >
          <p>일반회원</p>
        </div>
        <div
          onClick={() => {
            goAdminLogin();
          }}
        >
          <p>판매자</p>
        </div>
      </div>
      <Footer />
    </div>
  );
};

export default LoginSelect;
