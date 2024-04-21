import { createSlice } from '@reduxjs/toolkit'


let initialState = {
    goodsImg:[]
};

const AddGoodsSlice = createSlice({
    name:"AddGoods",
    initialState,
    reducers:{
        getGoodsImg(state,action){
            state.goodsImg=action.payload
        }
    }
})

export const AddGoodsActions = AddGoodsSlice.actions
export default AddGoodsSlice.reducer