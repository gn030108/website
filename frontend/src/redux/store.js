import { configureStore } from '@reduxjs/toolkit';
import homeReducer from './reducer/pageReducer/homeReducer';
import loginReducer from './reducer/pageReducer/loginReducer';
import itemReducer from './reducer/pageReducer/itemReducer';
import commentReducer from './reducer/componentReducer/commentReducer';
import reviewReducer from './reducer/componentReducer/reviewReducer';
import basketReducer from './reducer/pageReducer/basketReducer';
import myPageReducer from './reducer/pageReducer/myPageReducer';
import addGoodsReducer from './reducer/pageReducer/addGoodsReducer';
import signInReducer from './reducer/pageReducer/signInReducer';
import selectBoxReducer from './reducer/componentReducer/selectBoxReducer';
import modalReducer from './reducer/componentReducer/modalReducer';




const store = configureStore({
    reducer:{
        //page
        home:homeReducer,
        login:loginReducer,
        signIn:signInReducer,
        item:itemReducer,
        basket:basketReducer,
        myPage:myPageReducer,
        AddGoods:addGoodsReducer,
        
        //component
        comment:commentReducer,
        review:reviewReducer,
        selectBox:selectBoxReducer,
        modal:modalReducer,
    
    }
})

export default store