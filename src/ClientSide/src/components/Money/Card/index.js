import React from 'react';
import Avatar from 'react-avatar';
import { Card, Button } from 'antd';
import { useSelector, useDispatch } from 'react-redux';

import '../../Frame/PageStructure/antd.css';
import Modal from '../Modal';

const { Meta } = Card;

export default function() {
	const user = useSelector((state) => state.user);
  const balance = useSelector((state) => state.balance);
  
  const dispatch = useDispatch();

	const balanceNumber = balance.balance.toLocaleString();
	const lastTimeUpdate = new Date(balance.lastTimeUpdate * 1000).toLocaleString();

	return (
		<Card
			style={{ width: 400, marginTop: 40 }}
			className="card"
			actions={[
				<Modal />
			]}
		>
			<Meta
				avatar={<Avatar name={`${user.fullName}`} size="60" round={true} />}
				title={user.fullName}
				description={`@${user.username}`}
			/>
			<div style={{ display: 'flex', marginTop: '15px', position: 'relative' }}>
				<div style={{}}>Số dư: </div>
				<div style={{ right: 0, position: 'absolute' }}>{balanceNumber} VND</div>
			</div>
			<div style={{ display: 'flex', position: 'relative' }}>
				<div>Thay đổi lần cuối: </div>
				<div style={{ right: 0, position: 'absolute' }}>{lastTimeUpdate}</div>
			</div>
		</Card>
	);
}
