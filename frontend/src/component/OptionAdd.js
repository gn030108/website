import { useState } from "react";
import {useDispatch, useSelector } from "react-redux";
import styles from '../styles/componentStyle/OptionAdd.module.scss'
import { selectBoxActions } from "../redux/reducer/componentReducer/selectBoxReducer";

const OptionAdd = () => {

    const dispatch=useDispatch()
    
    const [title,setTitle] = useState('')
    const [optionList,setOptionList] = useState([
        {option:''}
    ])
    //옵션추가헨들러
    const handleProductAdd = () => {
        setOptionList([
            ...optionList,
            {option:''},
        ]);
    };
    // 각 옵션 삭제버튼의 핸들러
    const handleProductRemove = (index) => {
        const filteredList = [...optionList];
        filteredList.splice(index, 1);
        setOptionList(filteredList);
    };

    // 각 input의 값들을 다루는 핸들러
    const handleChangeOption = (index, e) => {
        const list = [...optionList];
        list[index].option = e.target.value;
        setOptionList(list);
    };
return (
    <div className={styles.layout}>
        <div className={styles.title_Box}>
            <input placeholder="옵션" value={title} onChange={(e)=>{setTitle(e.target.value)}}/>

            <button className={styles.plusBtn} onClick={()=>{handleProductAdd()}}>
                <i class="ri-chat-new-line"></i>
            </button>
        </div>  
        {optionList.map((item,index)=>(
            <div className={styles.option_Box} key={index}>
                <input className={styles.subOption} value={item.option} onChange={e => handleChangeOption(index, e)} placeholder="하위옵션"/>
                {optionList.length != 1 && (
                    <button className={styles.plusBtn} onClick={()=>{handleProductRemove(index)}}>
                        <i class="ri-chat-delete-line"></i>
                    </button>
                )}
            </div>
        ))}
    </div>
)
}

export default OptionAdd