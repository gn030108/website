import { createSlice } from "@reduxjs/toolkit";
import 상의 from "../../../image/상의.jpg";
import 바지 from "../../../image/바지.jpg";
import 신발 from "../../../image/신발.jpg";

let initialState = {
  //비밀번호 변경 모달
  prePassword: "",
  newPassword: "",
  PasswordTest: "",
  //회원정보 변경 모달
  address: "",
  phoneNumber: "",
  email: "",
  age: "나이 선택",
  payment: [
    { img: 상의, itemName: "상의", option: "M", count: "1", price: "10000" },
    { img: 바지, itemName: "바지", option: "M", count: "1", price: "10000" },
    { img: 신발, itemName: "신발", option: "M", count: "1", price: "10000" },
  ],
  reviewInfo: [],
  reviewSubmit: {
    size: "",
    bright: "",
    color: "",
    thick: "",
    delivery: "",
    packaging: "",
  },
};
const modalSlice = createSlice({
  name: "modal",
  initialState,
  reducers: {
    //비밀번호 변경 모달
    getPrePassword(state, action) {
      state.prePassword = action.payload;
    },
    getNewPassword(state, action) {
      state.newPassword = action.payload;
    },
    getPasswordTest(state, action) {
      state.PasswordTest = action.payload;
    },
    //회원정보 변경 모달
    getAddress(state, action) {
      state.address = action.payload;
    },
    getPhoneNumber(state, action) {
      state.phoneNumber = action.payload;
    },
    getEmail(state, action) {
      state.email = action.payload;
    },
    getAge(state, action) {
      state.age = action.payload;
    },
    //후기작성 모달
    getReviewInfo(state, action) {
      state.reviewInfo = action.payload;
    },
    getSize(state, action) {
      state.reviewSubmit.size = action.payload;
    },
    getBright(state, action) {
      state.reviewSubmit.bright = action.payload;
    },
    getColor(state, action) {
      state.reviewSubmit.color = action.payload;
    },
    getThick(state, action) {
      state.reviewSubmit.thick = action.payload;
    },
    getDelivery(state, action) {
      state.reviewSubmit.delivery = action.payload;
    },
    getPackaging(state, action) {
      state.reviewSubmit.packaging = action.payload;
    },
    //리셋
    reset(state, action) {
      state.address = "";
      state.phoneNumber = "";
      state.email = "";
      state.age = "";
      state.prePassword = "";
      state.newPassword = "";
      state.PasswordTest = "";
    },
  },
});

export const modalActions = modalSlice.actions;
export default modalSlice.reducer;
