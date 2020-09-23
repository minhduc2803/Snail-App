import React, {useEffect} from 'react';
import shave from 'shave';
import Avatar from 'react-avatar';
import { useSelector, useDispatch} from 'react-redux';

import './ConversationListItem.css';


export default function ConversationListItem(props) {
  useEffect(() => {
    shave('.conversation-snippet', 20);
  })
    const chosenIndex = useSelector(state => state.chosenIndex);
    const dispatch = useDispatch();
    const { index, photo, name, text } = props.data;
    const style = "conversation-list-item "+((index===chosenIndex) ? "chosen-conversation":"");
    console.log(style)
    return (
      <div className={`${style}`} onClick={() => dispatch({type: "CHOOSE_CONVERSATION", chosenIndex: index})}>
        <Avatar name={`${name}`} size="40" round={true}/>
        <div className="conversation-info">
          <h1 className="conversation-title">{ name }</h1>
          <p className="conversation-snippet">{ text }</p>
        </div>
      </div>
    );
}