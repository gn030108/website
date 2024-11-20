import styles from "../styles/componentStyle/passwordModal.module.scss";
import { useDispatch, useSelector } from "react-redux";
import { modalActions } from "../redux/reducer/componentReducer/modalReducer";

const PasswordModal = ({ modal, setModal }) => {
  const dispatch = useDispatch();

  const prePassword = useSelector((state) => state.modal.prePassword);
  const newPassword = useSelector((state) => state.modal.newPassword);
  const PasswordTest = useSelector((state) => state.modal.PasswordTest);

  const handleOpen = () => {
    setModal(!modal);
    dispatch(modalActions.reset());
  };

  const handleSubmit = () => {
    dispatch(modalActions.reset());
    setModal(!modal);
  };

  return (
    <div className={styles.container}>
      <div className={styles.modal_box}>
        <button
          className={styles.closeBtn}
          onClick={() => {
            handleOpen();
          }}
        >
          <i className="ri-close-line"></i>
        </button>
        <h2>비밀번호 변경</h2>
        <div className={styles.input_box}>
          <label>현재 비밀번호</label>
          <input
            value={prePassword}
            onChange={(e) => {
              dispatch(modalActions.getPrePassword(e.target.value));
            }}
          />
        </div>
        <div className={styles.input_box}>
          <label>신규 비밀번호</label>
          <input
            value={newPassword}
            onChange={(e) => {
              dispatch(modalActions.getNewPassword(e.target.value));
            }}
          />
        </div>
        <div className={styles.input_box}>
          <label>비밀번호 확인</label>
          <input
            value={PasswordTest}
            onChange={(e) => {
              dispatch(modalActions.getPasswordTest(e.target.value));
            }}
          />
          {newPassword != PasswordTest &&
            newPassword != "" &&
            PasswordTest != "" && (
              <p className={styles.redText}>비밀번호가 일치하지 않습니다</p>
            )}
        </div>
        <button
          className={styles.submitBtn}
          onClick={() => {
            handleSubmit();
          }}
        >
          변경
        </button>
      </div>
    </div>
  );
};

export default PasswordModal;
