import { createSlice } from '@reduxjs/toolkit'


let initialState = {
    memberId:'',
    password:'',
    isLogin: false,
    loginType:'',
    accessToken:'',
    refreshToken:'',
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
            state.refreshToken=''
        },
        getAccessToken(state,action){
            state.accessToken=action.payload
        },
        getRefreshToken(state,action){
            state.refreshToken=action.payload
        },
        setLogin(state,action){
            state.isLogin=true
        },
        setLogOut(state,action){
            state.isLogin=false
        },
        setLoginType(state,action){
            state.loginType=action.payload
        }
    }
})

export const loginActions = loginSlice.actions
export default loginSlice.reducer