import React from 'react';
import ConversationList from '../ConversationList';
import MessageList from '../MessageList';
import './Messenger.css';
import Compose from '../Compose';




export default function Messenger(props) {
 
 
    return (
      <div className="messenger">

        <div className="scrollable sidebar">
          <ConversationList />
        </div>

        <div className="scrollable content">
          <MessageList />
          <div className="compose">
					<Compose />
				</div>
        </div>
        
      </div>
    );
}