import { createSlice } from '@reduxjs/toolkit'


let initialState = {
    option1:'',
    optionList1:[
        {option:''}
    ],
    option2:'',
    optionList2:[
        {option:''}
    ],
    
};

const AddGoodsSlice = createSlice({
    name:"AddGoods",
    initialState,
    reducers:{
        getOption1(state,action){
            state.option1 = action.payload
        },
        addSubOption1(state,action){
            state.optionList1=[...state.optionList1,{option:''}]
        },
        setOptionList1(state,action){
            state.optionList1=action.payload
        },
        getOption2(state,action){
            state.option2 = action.payload
        },
        addSubOption2(state,action){
            state.optionList2=[...state.optionList2,{option:''}]
        },
        setOptionList2(state,action){
            state.optionList2=action.payload
        },
    }
})

export const AddGoodsActions = AddGoodsSlice.actions
export default AddGoodsSlice.reducer