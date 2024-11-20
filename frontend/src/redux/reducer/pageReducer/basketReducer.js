import { createSlice } from "@reduxjs/toolkit";

let initialState = {
  checkItems: [],
};

const basketSlice = createSlice({
  name: "basket",
  initialState,
  reducers: {
    getCheck(state, action) {
      const id = action.payload;
      state.checkItems = [...state.checkItems, id];
      console.log(state.checkItems);
    },
    getCheckOut(state, action) {
      const i = action.payload;
      const filteredCheckItems = state.checkItems.filter((id) => id !== i);
      state.checkItems = filteredCheckItems;
      console.log(state.checkItems);
    },
    all(state, action) {
      state.checkItems = action.payload;
      console.log(state.checkItems);
    },
  },
});

export const basketActions = basketSlice.actions;
export default basketSlice.reducer;
