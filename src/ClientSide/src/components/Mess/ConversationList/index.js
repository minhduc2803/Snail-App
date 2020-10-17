import React from 'react';
import ConversationSearch from '../ConversationSearch';
import ConversationListItem from '../ConversationListItem';
import Toolbar from '../Toolbar';
import ToolbarButton from '../ToolbarButton';

import { useSelector} from 'react-redux';

import './ConversationList.css';

export default function ConversationList(props) {

  const usersKey = Array.from(Array(useSelector(state => state.listUsers.length)).keys());
  console.log(usersKey);
    
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
          usersKey.map(index =>
            <ConversationListItem
              key={index}
              index={index}
            />
          )
        }
      </div>
    );
}