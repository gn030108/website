import { createSlice } from '@reduxjs/toolkit'
import 김민주 from '../../../image/김민주.png'
import 김민주1 from '../../../image/김민주1.png'

let initialState = {
    reviewList:[
        {
            name:'고립하구만',
            date:'2024.04.04',
            gender:'여성',
            height:'165',
            weight:'49',
            itemImage:김민주,//상품이미지
            itemName:'김민주사진',
            itemOption:'M',
            stars:'5',
            size:'보통이에요',
            bright:'보통이에요',
            color:'보통이에요',
            thickness:'보통이에요',
            delivery:'빨라요',
            packaging:'꼼꼼해요',
            image:[김민주,김민주],//리뷰 이미지
        },
        {
            name:'고립하구만',
            date:'2024.04.04',
            gender:'여성',
            height:'165',
            weight:'49',
            itemImage:김민주1,
            itemName:'김민주사진',
            itemOption:'M',
            stars:'5',
            size:'보통이에요',
            bright:'보통이에요',
            color:'보통이에요',
            thickness:'보통이에요',
            delivery:'빨라요',
            packaging:'꼼꼼해요',
            image:[김민주1,김민주1],
        },
    ]
    
};
const reviewSlice = createSlice({
    name:"review",
    initialState,
    reducers:{
    }
})

export const reviewActions = reviewSlice.actions
export default reviewSlice.reducer