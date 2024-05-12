import './App.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Home from './page/Home';
import Login from './page/Login';
import SignIn from './page/SignIn';
import List from './page/List';
import Item from './page/Item'
import Like from './page/Like'
import Basket from './page/Basket'
import MyPage from './page/MyPage'
import AddGoods from './page/AddGoods'
import AdminLogin from './page/AdminLogin'
import LoginSelect from './page/LoginSelect'
import NavBar from './component/NavBar';


function App() {
  return (
    <BrowserRouter>
      <div className="App">
        <Routes>
          <Route path="/" element={<Home/>} />
          <Route path="/Login" element={<Login/>} />
          <Route path="/AdminLogin" element={<AdminLogin/>} />
          <Route path="/LoginSelect" element={<LoginSelect/>} />
          <Route path="/SignIn" element={<SignIn/>} />
          <Route path="/List" element={<List/>} />
          <Route path="/Item" element={<Item/>} />
          <Route path="/Like" element={<Like/>} />
          <Route path="/Basket" element={<Basket/>} />
          <Route path="/MyPage" element={<MyPage/>} />
          <Route path="/AddGoods" element={<AddGoods/>} />
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;
