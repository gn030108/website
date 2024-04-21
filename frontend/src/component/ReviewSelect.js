import { useDispatch } from 'react-redux'
import styles from '../styles/componentStyle/reviewSelect.module.scss'
import { modalActions } from '../redux/reducer/componentReducer/modalReducer'



const ReviewSelect = ({List,type,text}) => {

  const dispatch=useDispatch()

  

  const hover = (item)=>{
    if (type=='사이즈'){
      dispatch(modalActions.getSize(item))
    }
    else if (type=='밝기'){
      dispatch(modalActions.getBright(item))
    }
    else if (type=='색'){
      dispatch(modalActions.getColor(item))
    }
    else if (type=='두깨'){
      dispatch(modalActions.getThick(item))
    }
    else if (type=='배송'){
      dispatch(modalActions.getDelivery(item))
    }
    else if (type=='포장'){
      dispatch(modalActions.getPackaging(item))
    }
  }

  return (
    <div className={styles.layout}>
      {List.map((item, index) => (
        <>
          <label htmlFor={'input_button'+index} onClick={()=>{hover(item)}} style={{width: `calc(100% / ${List.length})`}} key={index}>{item}</label>
          <input id={'input_button'+index} style={{display:'none'}}></input>
        </>
            ))}
    </div>
  )
}

export default ReviewSelect