import React from 'react';
import './Compose.css';
import { useSelector, useDispatch } from 'react-redux';
import Modal from '../../Money/Modal';

export default function Compose(props) {
	const ws = useSelector((state) => state.websocket);
	let content = useSelector((state) => state.listUsers[state.chosenIndex]);
	const user = useSelector((state) => state.user);
	const message = React.useRef(null);

	function handleSubmit(event) {
    console.log('yup enter chat');
    
		if (event.key !== 'Enter') return;

    
		const chat = {
			chatType: 1,
			senderId: user.userId,
			receiverId: content.userId,
			content: message.current.value
		};
		message.current.value = '';
		ws.send(JSON.stringify(chat));
	}
	return (
		<div className="compose">
			<input
				type="text"
				ref={message}
				className="compose-input"
				placeholder="&nbsp;Type a message, @name"
				onKeyPress={(event) => {
					handleSubmit(event);
				}}
			/>

			<div className="rightItem">
				<Modal />
			</div>
		</div>
	);
}
