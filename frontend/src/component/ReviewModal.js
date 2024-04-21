import { useState } from 'react'
import styles from '../styles/componentStyle/reviewModal.module.scss'
import { useDispatch, useSelector } from 'react-redux'
import { modalActions } from '../redux/reducer/componentReducer/modalReducer'
import ReviewSelect from './ReviewSelect'

const ReviewModal = ({modal,setModal}) => {

  const dispatch = useDispatch()
  
  const starArray = [0,1,2,3,4] //별 개수확인을 위한 변수
  const [score, setScore] = useState([false, false, false, false, false]);//별 색 변경 변수
  
  const [reviewImg, setReviewImg] = useState([])//보낼 리뷰 사진
  const [preViewImg,setPreviewImg] = useState([])//미리보기 사진

  const reviewInfo = useSelector((state)=>state.modal.reviewInfo)
  const reviewSubmit = useSelector((state)=>state.modal.reviewSubmit)

  const size = ['커요', '보통이에요','작아요']
  const bright = ['밝아요','보통이에요','어두워요',]
  const color = ['선명해요','보통이에요','흐려요',]
  const thick = ['두꺼워요','보통이에요','얇아요',]
  const delivery = ['빨라요','아쉬워요',]
  const packaging = ['꼼꼼해요','아쉬워요',]

  const handleOpen = ()=>{
    setModal(!modal)
    dispatch(modalActions.reset())
  }

  const handleSubmit = () =>{
    dispatch(modalActions.reset())
    setModal(!modal)
  }

  const savePreViewFiles=(e)=>{
    const file = e.target.files;
    setReviewImg(file)
    let fileUrl = [];
    for(let i=0; i<file.length;i++){
      const render = new FileReader()
      render.readAsDataURL(file[i])
      render.onload = () =>{
        fileUrl[i] =render.result
        setPreviewImg([...fileUrl])
      }
    }
    console.log(reviewImg,': 보낼이미지')
    console.log(preViewImg,':미리보기이미지')
  }

  const starScore = (index) => {
    let star = [...score];
    for (let i = 0; i < 5; i++) {
      star[i] = i <= index ? true : false;
    }
    setScore(star);
  };



  return (
    <div className={styles.container}>
      <div className={styles.modal_box}>
        <button className={styles.closeBtn} onClick={()=>{handleOpen()}}>
          <i className="ri-close-line"></i>
        </button>
        <h2>후기작성</h2>
        <div className={styles.reviewItem_box}>
          <div className={styles.review_img}>
            <img src={reviewInfo.img}/>
          </div>

          <div className={styles.reviewInfo}>
            <p>{reviewInfo.brand}</p>
            <p>{reviewInfo.name}</p>
            <p>옵션: {reviewInfo.option}</p>
          </div>
        </div>

        <div className={styles.stars}>
          {starArray.map((index)=>(
            <i className="ri-star-fill" style={score[index] ? {color:'#FF933B'}:{color:'#cdcdcd'}} onMouseOver={()=>{starScore(index)}} key={index} ></i>
          ))}
        </div>

        <div className={styles.select_box}>
          <ReviewSelect
            List = {size}
            type = '사이즈'
            text = {reviewSubmit.size}
          />
        </div>
        <div className={styles.select_box}>
          <ReviewSelect
            List = {bright}
            type = '밝기'
            text = {reviewSubmit.bright}
          />
        </div>
        <div className={styles.select_box}>
          <ReviewSelect
            List = {color}
            type = '색'
            text = {reviewSubmit.color}
          />
        </div>
        <div className={styles.select_box}>
          <ReviewSelect
            List = {thick}
            type = '두깨'
            text = {reviewSubmit.thick}
          />
        </div>
        <div className={styles.select_box}>
          <ReviewSelect
            List = {delivery}
            type = '배송'
            text = {reviewSubmit.delivery}
          />
        </div>
        <div className={styles.select_box}>
          <ReviewSelect
            List = {packaging}
            type = '포장'
            text = {reviewSubmit.packaging}
          />
        </div>


        <div className={styles.uploadImg_btn}>
          <div className={styles.Img_box}>
            {preViewImg.map((item,index)=>(
              <div>
                <img src={item} key={index}/>
              </div>
            ))}
          </div>
          <label htmlFor='uploadImg'>업로드</label>
          <input id='uploadImg' onChange={savePreViewFiles} type='file' accept='image/jpeg, image/jpg, image/png' style={{display:"none"}} multiple required/>
        </div>

        <div className={styles.review_text_box}>
          <textarea/>
        </div>

        <button className={styles.submitBtn} onClick={()=>{handleSubmit()}}>변경</button>
      </div>
    </div>
  )
}

export default ReviewModal
