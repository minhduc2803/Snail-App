import React from 'react';
import './Compose.css';
import { useSelector } from 'react-redux';
import fintechService from '../../grpc/fintechService';

export default function Compose(props) {
  const ws = useSelector(state => state.websocket);
  let content = useSelector(state => state.listUsers[state.chosenIndex]);
  const user = useSelector(state => state.user);
  const message = React.useRef(null);
  const token = useSelector(state => state.user.token);
  
  function handleSubmit(event){
    if(token !== null){
      const metadata = {"Authorization": "Bearer "+token};
      fintechService.getBalance(metadata, (err, response) => {
        console.log("GRPC: ");
        console.log(response);
      });
    }
    console.log("yup enter chat");
      if (event.key !== 'Enter')
          return;
      
      const chat = {
          chatType: 1,
          senderId: user.userId,
          receiverId: content.userId,
          content: message.current.value
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