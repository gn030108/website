import { createSlice } from '@reduxjs/toolkit'


let initialState = {

};

const homeSlice = createSlice({
    name:"home",
    initialState,
    reducers:{

    }
})

export const homeActions = homeSlice.actions
export default homeSlice.reducer