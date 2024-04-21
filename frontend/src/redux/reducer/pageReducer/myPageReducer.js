import { createSlice } from '@reduxjs/toolkit'
import 김민주 from '../../../image/김민주.png'
import 김민주1 from '../../../image/김민주1.png'
import 김민주2 from '../../../image/김민주2.png'


let initialState = {
    orderInfo:[
        {img:김민주,
            brand:'무신사스탠다드',
            name:'옥스포드셔츠',
            option:33,
            date:'2024.04.13',
            number: '202404111521000003',
            price:'30000',
            count:'2',
            delivery:'배송완료',
        },
        {img:김민주1,
            brand:'무신사스탠다드',
            name:'옥스포드셔츠',
            option:33,
            date:'2024.04.13',
            number: '202404111521000003',
            price:'30000',
            count:'2',
            delivery:'배송완료',
        },
        {img:김민주2,
            brand:'무신사스탠다드',
            name:'옥스포드셔츠',
            option:33,
            date:'2024.04.13',
            number: '202404111521000003',
            price:'30000',
            count:'2',
            delivery:'배송완료',
        }
    ]
};
const myPageSlice = createSlice({
    name:"myPage",
    initialState,
    reducers:{
    }
})

export const myPageActions = myPageSlice.actions
export default myPageSlice.reducer