import { createSlice } from '@reduxjs/toolkit'


let initialState = {
    memberId:'',
    password:'',
    isLogin: false,
    loginType:'',
};
const loginSlice = createSlice({
    name:"login",
    initialState,
    reducers:{
        getId(state,action){
            state.memberId = action.payload
        },
        getPw(state,action){
            state.password = action.payload
        },
        LogIn(state,action){
            state.isLogin=true
            state.loginType=action.payload
            state.memberId=''
            state.password=''
        },
        LogOut(state,action){
            state.isLogin=false
            state.loginType=''
        },
    }
})

export const loginActions = loginSlice.actions
export default loginSlice.reducer