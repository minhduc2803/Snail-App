import React, { useReducer } from 'react';
import './Compose.css';
import { useSelector } from 'react-redux';


export default function Compose(props) {
  const ws = useSelector(state => state.websocket);
  let content = useSelector(state => state.listUsers[state.chosenIndex]);
  const user = useSelector(state => state.user);
  const message = React.useRef(null);
  function handleSubmit(event){
    console.log("yup enter chat");
      if (event.key !== 'Enter')
          return;
      
      const chat = {
          Mode: 1,
          UserSendID: user.userID,
          UserReceiveID: content.UserID,
          Content: message.current.value
      }
      message.current.value = "";
      ws.send(JSON.stringify(chat));
  }
    return (
      <div className="compose">
       
        <input
          type="text"
          ref={message}
          className="compose-input"
          placeholder="Type a message, @name"
          onKeyPress={(event) => { handleSubmit(event) }}
        />
       
        {
          props.rightItems
        }
      </div>
    );
}