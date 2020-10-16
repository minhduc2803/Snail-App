import React from 'react';
import ConversationList from '../ConversationList';
import MessageList from '../MessageList';
import './Messenger.css';

import { useDispatch } from 'react-redux';
import { asyncLoadUsers, asyncLoadChat } from '../../../redux/actions';



export default function Messenger(props) {
 
  const dispatch = useDispatch();
  dispatch(asyncLoadUsers()).then(result => {
      for( let i=0;i<result.length;i++){
        dispatch(asyncLoadChat(i, result[i]));
      }
  });
    return (
      <div className="messenger">

        <div className="scrollable sidebar">
          <ConversationList />
        </div>

        <div className="scrollable content">
          <MessageList />
        </div>
      </div>
    );
}