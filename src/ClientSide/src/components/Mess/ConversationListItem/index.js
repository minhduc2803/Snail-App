import React, {useEffect} from 'react';
import Shave from 'react-shave';
import Avatar from 'react-avatar';
import { useSelector, useDispatch} from 'react-redux';

import './ConversationListItem.css';


export default function ConversationListItem(props) {
  
    const chosenIndex = useSelector(state => state.chosenIndex);
    const dispatch = useDispatch();
    
    const { index, name, text } = props.data;
    
    const style = "conversation-list-item "+((index===chosenIndex) ? "chosen-conversation":"");
    return (
      <div className={`${style}`} onClick={() => dispatch({type: "CHOOSE_CONVERSATION", chosenIndex: index})}>
        <Avatar name={`${name}`} size="40" round={true}/>
        <div className="conversation-info">
          <h1 className="conversation-title">{ name }</h1>
          <Shave maxHeight={100}>{text}</Shave>
        </div>
      </div>
    );
}