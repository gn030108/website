import { useSelector } from 'react-redux'
import styles from '../styles/componentStyle/comment.module.scss'

const Comment = () => {

  const commentList = useSelector((state)=>state.comment.commentList)



  return (
    <>
      {commentList.map((comment,index)=>(
      <div className={styles.comment_box} key={index}>
        <div className={styles.user}>
            <p>{comment.name}</p>
            <p>{comment.date}</p>
        </div>
        <div className={styles.comment_text}>
          <p>{comment.text}</p>
        </div>
      </div>
      ))}
    </>
  )
}

export default Comment