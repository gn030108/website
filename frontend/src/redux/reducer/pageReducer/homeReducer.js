import { createSlice } from "@reduxjs/toolkit";

let initialState = {
  itemList: [],
};

const homeSlice = createSlice({
  name: "home",
  initialState,
  reducers: {
    getItemList(state, action) {
      state.itemList = action.payload;
    },
  },
});

export const homeActions = homeSlice.actions;
export default homeSlice.reducer;
