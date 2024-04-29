import { createSlice } from '@reduxjs/toolkit'


let initialState = {
    memberId:'',
    password:'',
    isLogin: false,
    loginType:'',
    accessToken:''
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
            state.password=''
        },
        LogOut(state,action){
            state.isLogin=false
            state.accessToken=''
            state.loginType=''
            state.memberId=''
        },
        getAccessToken(state,action){
            state.accessToken=action.payload
        },
        setLogin(state,action){
            state.isLogin=true
        }
    }
})

export const loginActions = loginSlice.actions
export default loginSlice.reducer