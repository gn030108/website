import { createSlice } from "@reduxjs/toolkit";

let initialState = {
  //selectBox
  option1: "옵션 선택",
  option2: "옵션 선택",
  optionData1: ["옵션1", "옵션2"],
  optionData2: ["선택1", "선택2"],
  //selectBox_add
  options: [],
};
const selectBoxSlice = createSlice({
  name: "selectBox",
  initialState,
  reducers: {
    //selectBox_add
    getOption1(state, action) {
      state.option1 = action.payload;
    },
    getOption2(state, action) {
      state.option2 = action.payload;
    },

    //selectBox_add
    getText(state, action) {
      state.options.append(action.payload);
    },
  },
});

export const selectBoxActions = selectBoxSlice.actions;
export default selectBoxSlice.reducer;
