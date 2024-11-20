import { createSlice } from "@reduxjs/toolkit";

let initialState = {};
const itemSlice = createSlice({
  name: "item",
  initialState,
  reducers: {},
});

export const itemActions = itemSlice.actions;
export default itemSlice.reducer;
