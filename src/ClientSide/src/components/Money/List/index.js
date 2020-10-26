import React from 'react';
import { useSelector } from 'react-redux';
import Avatar from 'react-avatar';
import { List, Button } from 'antd';
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
						<div>{`${history.transferType === 0
							? 'Chuyển tiền tới'
							: 'Nhận tiền từ'} ${history.fullName}`}</div>
					}
					description={dateTime}
				/>
				<div className="historyContent">
					<div className="historyMessage">{history.message}</div>
					<div>{`${history.transferType === 0 ? '-' : '+'} ${`${history.amount.toLocaleString()}`} VND`}</div>
				</div>
			</List.Item>
		);
	});


	const loadMore = (
		<div
          style={{
            textAlign: 'center',
            marginTop: 12,
            height: 32,
            lineHeight: '32px',
          }}
        >
          <Button >loading more</Button>
        </div>
	)

	return (
		<div className="containList">
			<div className="list-scrollable">
				<List
				itemLayout="horizontal"
				loadMore={loadMore}
				>{renderItem}</List>
			</div>
		</div>
	);
}
