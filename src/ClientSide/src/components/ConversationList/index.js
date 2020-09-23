import React, {useState, useEffect} from 'react';
import ConversationSearch from '../ConversationSearch';
import ConversationListItem from '../ConversationListItem';
import Toolbar from '../Toolbar';
import ToolbarButton from '../ToolbarButton';

import { useSelector} from 'react-redux';

import './ConversationList.css';

export default function ConversationList(props) {

  const listUsers = useSelector(state => state.listUsers);
  
//   useEffect(() => {
//     dispatch(asyncLoadUsers());
//     getConversations()
//   },[])

  
    let conversations = listUsers.map((result, index) => {
      return {
        index: index,
        photo: "#",
        name: `${result.Fullname}`,
        text: result.chat !== undefined && result.chat[result.chat.length-1] !== undefined ? `${result.chat[result.chat.length-1].Content}` : `Say hello to ${result.Fullname}`,
      };
    });
    
    return (
      <div className="conversation-list">
        <Toolbar
          title="Messenger"
          leftItems={[
            <ToolbarButton key="cog" icon="ion-ios-cog" />
          ]}
          rightItems={[
            <ToolbarButton key="add" icon="ion-ios-add-circle-outline" />
          ]}
        />
        <ConversationSearch />
        {
          conversations.map(conversation =>
            <ConversationListItem
              key={conversation.index}
              data={conversation}
            />
          )
        }
      </div>
    );
}