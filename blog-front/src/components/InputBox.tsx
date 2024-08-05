import { ChangeEvent, KeyboardEvent, forwardRef } from "react";
import "assets/css/inputBox/InputBox.css";

interface Props {
  label: string;
  type: "text" | "password";
  placeholder: string;
  value: string;
  onChange: (event: ChangeEvent<HTMLInputElement>) => void;
  error: boolean;
  icon?: "eye-light-off-icon" | "eye-light-on-icon" | "expand-right-light-icon";
  onButtonClick?: () => void;
  message?: string;
  onKeyDown?: (event: KeyboardEvent<HTMLInputElement>) => void;
}

// component: Input Box
const InputBox = forwardRef<HTMLInputElement, Props>((props: Props, ref) => {
  // properties
  const {
    label,
    type,
    placeholder,
    value,
    onChange,
    error,
    onButtonClick,
    icon,
    message,
    onKeyDown,
  } = props;

  // event handler: onKeyDown 이벤트
  const onKeyDownHandler = (event: KeyboardEvent<HTMLInputElement>) => {
    if (!onKeyDown) return;
    onKeyDown(event);
  }; // onKeyDownHandler

  // render: Input Box render
  return (
    <div className="input-area">
      <div className="input-lable">{label}</div>
      <div className={error ? "input-container-error" : "input-container"}>
        <input
          ref={ref}
          type={type}
          className="input"
          placeholder={placeholder}
          value={value}
          onChange={onChange}
          onKeyDown={onKeyDownHandler}
        />
        {onButtonClick !== undefined && (
          <div className="icon-button" onClick={onButtonClick}>
            {icon !== undefined && <div className={`icon ${icon}`}></div>}
          </div>
        )}
      </div>
      {message !== undefined && <div className="input-message">{message}</div>}
    </div> // input-area
  );
}); // fn InputBox

export default InputBox;
