import React from 'react';
import './Compose.css';
import { useSelector } from 'react-redux';
import Modal from '../../Money/Modal';
import { SendOutlined } from '@ant-design/icons';
import { Button } from 'antd';

export default function Compose(props) {
	const ws = useSelector((state) => state.websocket);
	let content = useSelector((state) => state.listUsers[state.chosenIndex]);
	const user = useSelector((state) => state.user);
	const message = React.useRef(null);

	function handleEnter(event) {
		console.log('yup enter chat');

		if (event.key !== 'Enter') return;

		handleSubmit();
	}

	function handleSubmit() {
		if (message.current.value === null || message.current.value === '') return;
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
		<div className="compose-container">
			<input 
				type="text"
				ref={message}
				className="compose-input"
				placeholder="Type a message, @name"
				onKeyPress={(event) => {
					handleEnter(event);
				}}
			/>

			<Button onClick={() => handleSubmit()} type="primary" shape="circle" icon={<SendOutlined />} />
			<div className="rightItem">
				<Modal />
			</div>
		</div>
	);
}
