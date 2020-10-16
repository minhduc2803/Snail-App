import React from 'react';
import { useSelector } from 'react-redux';
import Avatar from 'react-avatar';
import { List, message, Spin } from 'antd';
import '../../Frame/PageStructure/antd.css';
import WindowScroller from 'react-virtualized/dist/commonjs/WindowScroller';

export default function() {
	const transferHistory = useSelector((state) => state.transferHistory);
  
  const renderItem = transferHistory.map((history, index) => {
		return (
			<List.Item key={history.id} >
				<List.Item.Meta
					avatar={<Avatar name={history.fullName} size="35" round={true} />}
					title={<div>{`${history.transferType == 1 ? "Chuyển tiền tới":"Nhận tiền từ"} ${history.fullName}`}</div>}
					description={history.transferTime}
				/>
				<div>{`${history.transferType == 1 ? "-":"+"} ${history.amount} VND`}</div>
			</List.Item>
		);
	});
  
	return <List>{renderItem}</List>;
}
