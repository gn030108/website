import { useDispatch, useSelector } from 'react-redux'
import NavBar from '../component/NavBar'
import SideBar from '../component/SideBar'
import SideMenu from '../component/SideMenu'
import SelectBox from '../component/SelectBox'
import styles from '../styles/pageStyle/login.module.scss'
import { signInActions } from '../redux/reducer/pageReducer/signInReducer'
import { useEffect } from 'react'
import { useNavigate } from 'react-router-dom'
import useAxiosInstance from '../api/axiosInstance'

const SignIn = () => {

  const dispatch = useDispatch()
  const navigate = useNavigate()
  const axiosInstance= useAxiosInstance()

  const memberId = useSelector((state)=>state.signIn.memberId)
  const password = useSelector((state)=>state.signIn.password)
  const name = useSelector((state)=>state.signIn.name)
  const email = useSelector((state)=>state.signIn.email)
  const address = useSelector((state)=>state.signIn.address)
  const phoneNumber = useSelector((state)=>state.signIn.phoneNumber)
  const ageData = useSelector((state)=>state.signIn.age)
  const gender = useSelector((state)=>state.signIn.gender)
  

  const genderOption = [
    '남자','여자'
  ]
  const ageOption = [
    '10대','20대','30대','40대',
  ]

  const handleInputChange = (e) => {
    const { name, value } = e.target;

    if (name==='id'){
        dispatch(signInActions.getId(value))
    }
    else if (name==='pw'){
        dispatch(signInActions.getPw(value))
    }
    else if (name==='name'){
      dispatch(signInActions.getName(value))
    }
    else if (name==='email'){
      dispatch(signInActions.getEmail(value))
    }
    else if (name==='address'){
      dispatch(signInActions.getAddress(value))
    }
    else if (name==='phoneNumber'){
      dispatch(signInActions.getPhoneNumber(value))
    }
  }
  const handleSignIn = (e)=>{

    e.preventDefault();

    const age = parseInt(ageData)

    axiosInstance.post('/member/register',{
      memberId,
      password,
      name,
      age,
      email,
      phoneNumber,
      gender,
      address
    })
    .then(response =>{
      console.log(response)
      navigate('/login')
    })
    .catch(error=>{
      console.log(error)
    })
  }
  

  return (
    <div>
      <div style={{height:'100px'}}><NavBar/></div>
      <div className={styles.SideMenu}><SideMenu/></div>
      <SideBar/>
      <div className={styles.layout}>
        <form>
          <input placeholder='아이디' name='id' value={memberId} type='text' onChange={handleInputChange}/>
          <input placeholder='비밀번호' name='pw' value={password} type='password'   onChange={handleInputChange}/> {/*autoComplete="off"*/}
          <input placeholder='닉네임' name='name'value={name} type='text' onChange={handleInputChange}/>
          <input placeholder='이메일' name='email'value={email} type='email' onChange={handleInputChange}/>
          <input placeholder='주소' name='address'value={address} type='address' onChange={handleInputChange}/>
          <input placeholder='휴대폰 번호' name='phoneNumber'value={phoneNumber} type='phoneNumber' onChange={handleInputChange}/>
          <div className={styles.selectBox}>
            <SelectBox
            optionData={ageOption}
            currentValue={ageData}
            type={'age'}
            />
          </div>
          <div className={styles.selectBox}>
            <SelectBox
            optionData={genderOption}
            currentValue={gender}
            type={'gender'}
            />
          </div>
          <button onClick={(e)=>{handleSignIn(e)}}>회원가입</button>
        </form>
      </div>
    </div>
  )
}

export default SignIn