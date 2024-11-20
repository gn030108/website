import { useState } from "react";
import useDispatch from "react-redux";
import styles from "../styles/componentStyle/selectBox.module.scss";
import { signInActions } from "../redux/reducer/pageReducer/signInReducer";
import { selectBoxActions } from "../redux/reducer/componentReducer/selectBoxReducer";
import { modalActions } from "../redux/reducer/componentReducer/modalReducer";

const SelectBox = ({ optionData, type, currentValue }) => {
  const dispatch = useDispatch();
  const [showOptions, setShowOptions] = useState(false);

  const handleSelectValue = (e) => {
    const { innerText } = e.target;
    if (type === "gender") {
      dispatch(signInActions.getGender(innerText));
    } else if (type === "age") {
      dispatch(signInActions.getAge(innerText));
    } else if (type === "option1") {
      dispatch(selectBoxActions.getOption1(innerText));
    } else if (type === "option2") {
      dispatch(selectBoxActions.getOption2(innerText));
    } else if (type === "age-select") {
      dispatch(modalActions.getAge(innerText));
    }
  };
  return (
    <div
      className={styles.select}
      style={{ margin: "0px" }}
      onClick={() => setShowOptions((prev) => !prev)}
    >
      <div className={styles.label}>{currentValue}</div>
      <div className={showOptions ? styles.selectOptions : styles.none}>
        {optionData.map((option, index) => (
          <div
            className={styles.Option}
            key={index}
            value={option}
            onClick={(e) => {
              handleSelectValue(e);
            }}
          >
            {option}
          </div>
        ))}
      </div>
    </div>
  );
};

export default SelectBox;
