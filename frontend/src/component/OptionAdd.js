import { useDispatch } from "react-redux";
import { AddGoodsActions } from "../redux/reducer/pageReducer/addGoodsReducer";
import styles from "../styles/componentStyle/OptionAdd.module.scss";

const OptionAdd = ({ title, optionList, type }) => {
  const dispatch = useDispatch();

  //옵션 선택
  const handleTitle = (e) => {
    const value = e.target.value;
    if (type === "option1") {
      dispatch(AddGoodsActions.getOption1(value));
    } else if (type === "option2") {
      dispatch(AddGoodsActions.getOption2(value));
    }
  };

  //옵션추가헨들러
  const handleProductAdd = () => {
    if (type === "option1") {
      dispatch(AddGoodsActions.addSubOption1());
    } else if (type === "option2") {
      dispatch(AddGoodsActions.addSubOption2());
    }
  };
  // 각 옵션 삭제버튼의 핸들러
  const handleProductRemove = (index) => {
    const filteredList = [...optionList];
    filteredList.splice(index, 1);

    if (type === "option1") {
      dispatch(AddGoodsActions.setOptionList1(filteredList));
    } else if (type === "option2") {
      dispatch(AddGoodsActions.setOptionList2(filteredList));
    }
  };

  // 각 input의 값들을 다루는 핸들러
  const handleChangeOption = (index, e) => {
    const updatedList = optionList.map((item, idx) => {
      if (idx === index) {
        return { ...item, option: e.target.value }; // 해당 객체를   복사하고 option 속성만 변경
      }
      return item; // 변경할 필요가 없는 다른 객체는 그대로 반환
    });
    if (type === "option1") {
      dispatch(AddGoodsActions.setOptionList1(updatedList));
    } else if (type === "option2") {
      dispatch(AddGoodsActions.setOptionList2(updatedList));
    }
  };
  return (
    <div className={styles.layout}>
      <div className={styles.title_Box}>
        <input
          placeholder="옵션"
          value={title}
          onChange={(e) => {
            handleTitle(e);
          }}
        />

        <button
          className={styles.plusBtn}
          onClick={() => {
            handleProductAdd();
          }}
        >
          <i className="ri-chat-new-line"></i>
        </button>
      </div>
      {optionList.map((item, index) => (
        <div className={styles.option_Box} key={index}>
          <input
            className={styles.subOption}
            value={optionList[index].option}
            onChange={(e) => handleChangeOption(index, e)}
            placeholder="하위옵션"
          />
          {optionList.length !== 1 && (
            <button
              className={styles.plusBtn}
              onClick={() => {
                handleProductRemove(index);
              }}
            >
              <i className="ri-chat-delete-line"></i>
            </button>
          )}
        </div>
      ))}
    </div>
  );
};

export default OptionAdd;
