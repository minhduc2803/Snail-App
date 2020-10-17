import React, { useEffect } from 'react';
import shave from 'shave';
import Avatar from 'react-avatar';
import { useSelector, useDispatch } from 'react-redux';

import './ConversationListItem.css';

export default function ConversationListItem(props) {
  useEffect(() => {
    shave('.conversation-snippet', 20);
  })

	const chosenIndex = useSelector((state) => state.chosenIndex);
	const infoUser = useSelector((state) => state.listUsers[props.index]);
	const infoChat = useSelector((state) => state.listUsers[props.index].chat);
	const dispatch = useDispatch();
	let text =
		infoChat !== undefined && infoChat[infoChat.length - 1] !== undefined
			? `${infoChat[infoChat.length - 1].content}`
			: `Gửi lời chào tới ${infoUser.fullName}`;
	
	
	const style = 'conversation-list-item ' + (props.index === chosenIndex ? 'chosen-conversation' : '');
	return (
		<div className={`${style}`} onClick={() => dispatch({ type: 'CHOOSE_CONVERSATION', chosenIndex: props.index })}>
			<Avatar name={`${infoUser.fullName}`} size="40" round={true} />
			<div className="conversation-info">
				<h1 className="conversation-title">{infoUser.fullName}</h1>
				<p className="snippet">{ text }</p>
			</div>
		</div>
	);
}
