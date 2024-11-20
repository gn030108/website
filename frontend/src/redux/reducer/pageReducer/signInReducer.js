import { createSlice } from "@reduxjs/toolkit";

let initialState = {
  memberId: "",
  password: "",
  name: "",
  email: "",
  address: "",
  phoneNumber: "",
  age: "나이",
  gender: "성별",
};

const signInSlice = createSlice({
  name: "signIn",
  initialState,
  reducers: {
    getId(state, action) {
      state.memberId = action.payload;
    },
    getPw(state, action) {
      state.password = action.payload;
    },
    getName(state, action) {
      state.name = action.payload;
    },
    getEmail(state, action) {
      state.email = action.payload;
    },
    getAddress(state, action) {
      state.address = action.payload;
    },
    getPhoneNumber(state, action) {
      state.phoneNumber = action.payload;
    },
    getAge(state, action) {
      state.age = action.payload;
    },
    getGender(state, action) {
      state.gender = action.payload;
    },
    reset(state, action) {
      state.memberId = "";
      state.password = "";
      state.name = "";
      state.email = "";
      state.address = "";
      state.phoneNumber = "";
      state.age = "";
      state.gender = "성별";
    },
  },
});

export const signInActions = signInSlice.actions;
export default signInSlice.reducer;
