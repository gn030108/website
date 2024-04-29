import { useDispatch, useSelector } from 'react-redux'
import { modalActions } from '../redux/reducer/componentReducer/modalReducer'
import styles from '../styles/componentStyle/InfoModal.module.scss'
import useAxiosInstance from '../api/axiosInstance'

const InfoModal = ({modal,setModal}) => {
  
  const dispatch = useDispatch()
  const axiosInstance=useAxiosInstance()

  const memberId=useSelector((state)=>state.login.memberId)
  const address = useSelector((state)=>state.modal.address)
  const phoneNumber = useSelector((state)=>state.modal.phoneNumber)
  const email = useSelector((state)=>state.modal.email)
  const age = useSelector((state)=>state.modal.age)


  const handleOpen = ()=>{
    setModal(!modal)
    dispatch(modalActions.reset())
  }

  const handleSubmit = (e) =>{
    e.preventDefault();


    axiosInstance.post('/member/change-info',{
      memberId,phoneNumber,email,age,address
    })
    .then(response =>{
      dispatch(modalActions.reset())
      setModal(!modal)
    })
    .catch(error=>{
      console.log(error)
    })
  }



  return (
    <div className={styles.container}>
      <div className={styles.modal_box}>
        <button className={styles.closeBtn} onClick={()=>{handleOpen()}}>
          <i className="ri-close-line"></i>
        </button>
        <h2>회원정보수정</h2>
        <div className={styles.input_box}>
          <label >주소</label>
          <input value={address} onChange={(e)=>{dispatch(modalActions.getAddress(e.target.value))}}/>
        </div>
        <div className={styles.input_box}>
          <label>휴대번호</label>
          <input value={phoneNumber} onChange={(e)=>{dispatch(modalActions.getPhoneNumber(e.target.value))}}/>
        </div>
        <div className={styles.input_box}>
          <label>이메일</label>
          <input value={email} onChange={(e)=>{dispatch(modalActions.getEmail(e.target.value))}}/>
        </div>
        <div className={styles.input_box}>
          <label>나이</label>
          <input value={age} onChange={(e)=>{dispatch(modalActions.getAge(e.target.value))}}/>
        </div>
        <button className={styles.submitBtn} onClick={(e)=>{handleSubmit(e)}}>변경</button>
      </div>
    </div>
  )
}

export default InfoModal