import NavBar from "../component/NavBar";
import SideBar from "../component/SideBar";
import Card from "../component/Card";
import { useDispatch } from "react-redux";
import styles from "../styles/pageStyle/home.module.scss";
import Footer from "../component/Footer";
import { useEffect } from "react";
import useAxiosInstance from "../api/axiosInstance";
import { homeActions } from "../redux/reducer/pageReducer/homeReducer";

const Home = () => {
  const axiosInstance = useAxiosInstance();
  const dispatch = useDispatch();

  useEffect(() => {
    axiosInstance
      .get("/item/get/home")
      .then((response) => {
        dispatch(homeActions.getItemList(response.data));
        console.log(response);
        console.log("성공!");
      })
      .catch((error) => {
        console.log(error);
        console.log("실패");
      });
  });

  return (
    <div>
      <div className={styles.navbar}>
        <NavBar />
      </div>
      <SideBar />
      <div className={styles.container}>
        <div style={{ width: "fit-content" }}>
          <Card />
        </div>
      </div>
      <Footer />
    </div>
  );
};

export default Home;
