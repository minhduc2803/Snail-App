import React from 'react';
import { useSelector } from 'react-redux';
import Avatar from 'react-avatar';
import { List, message, Spin } from 'antd';
import '../../Frame/PageStructure/antd.css';

export default function() {
	const transferHistory = useSelector((state) => state.transferHistory);

	const renderItem = transferHistory.map((history, index) => {
		let dateTime = new Date(history.transferTime * 1000).toLocaleString();
		return (
			<List.Item key={index}>
				<List.Item.Meta
					avatar={<Avatar name={history.fullName} size="35" round={true} />}
					title={
						<div>{`${history.transferType == 1
							? 'Chuyển tiền tới'
							: 'Nhận tiền từ'} ${history.fullName}`}</div>
					}
					description={dateTime}
				/>
				<div>
					{history.message}
				</div>
				<div>{`${history.transferType == 1 ? '-' : '+'} ${`${history.amount.toLocaleString()}`} VND`}</div>
			</List.Item>
		);
	});

	return (
		<div className="containList">
			<div className="list-scrollable">
			<List >{renderItem}</List>
			</div>
			
		</div>
	);
}
