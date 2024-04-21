import style from '../styles/componentStyle/paging.module.scss'
import { useEffect, useState } from 'react';

const Paging = ({page, totalElements, limit, displayPage, setPage}) => {

    const numPages = Math.ceil(totalElements/limit)//전체 페이지 갯수
    const [group, setGroup] = useState(1)//현제 보여주는 페이지 그룹
    const noPrev = group === 1; // 이전 페이지가 없는 경우
    const noNext = group + displayPage - 1 >= numPages; // 다음 페이지가 없는 경우
    
    const selectPage=(index)=>{
        setPage(index)
    }
    const preGroup=()=>{
        setPage(group-displayPage)
    }
    const nextGroup=()=>{
        setPage(group+displayPage)
    }
    useEffect(() => {
        if (page === group + displayPage) setGroup((prev) => prev + displayPage);
        if (page < group) setGroup((prev) => prev - displayPage);
    }, [page, displayPage, group]);



    return (
        <div className={style.layout}>
            <ul className={style.ul}>
                <li className={`${style.arrow} ${noPrev && style.invisible}`}>
                    <div onClick={()=>{preGroup()}}>이전</div>
                </li>
                {/*한번에 보여줄 페이지 갯수만큼 어레이 생성*/}
                {Array(displayPage).fill().map((_ , index) => (
                    <div key={index}>
                        {(group + index) <= numPages && (
                            <li className={`${style.li} ${page === (group+index) && style.active}`} onClick={()=>{selectPage(group+index)}} key={group+index}>{group+index}</li>
                        )}
                    </div>
                ))}
                <li className={`${style.arrow} ${noNext && style.invisible}`}>
                    <div onClick={()=>{nextGroup()}}>다음</div>
                </li>
            </ul>
        </div>
    )
}

export default Paging

//   <div></div>
//   {/**/}
//   className={}