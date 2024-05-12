import { createSlice } from '@reduxjs/toolkit'


let initialState = {
    type:''
};
const navbarSlice = createSlice({
    name:"navbar",
    initialState,
    reducers:{
        getType(state,action){
            state.type= action.payload
        }
    }
})

export const navbarActions = navbarSlice.actions
export default navbarSlice.reducer