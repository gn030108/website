import { createSlice } from "@reduxjs/toolkit";

let initialState = {
  //댓글 리스트
  commentList: [
    {
      name: "박원우",
      date: "2024.04.04",
      text: "좋아요 생각했던것보다 루즈하네요 맘에 들어요",
    },
    {
      name: "박원우",
      date: "2024.04.04",
      text: "좋아요 생각했던것보다 루즈하네요 맘에 들어요",
    },
    {
      name: "박원우",
      date: "2024.04.04",
      text: "좋아요 생각했던것보다 루즈하네요 맘에 들어요",
    },
  ],
};
const commentSlice = createSlice({
  name: "comment",
  initialState,
  reducers: {},
});

export const commentActions = commentSlice.actions;
export default commentSlice.reducer;
