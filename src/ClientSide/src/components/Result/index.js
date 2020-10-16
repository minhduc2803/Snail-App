import React from 'react';
import { useDispatch, useSelector } from 'react-redux';

import { Result, Button } from 'antd';
import { SmileOutlined } from '@ant-design/icons';

export default function() {
    //const transferComplete = useSelector(state => state.transfer.transferComplete)
    const dispatch = useDispatch();

	return (
		<Result
			icon={<SmileOutlined />}
			title="Great, we have done all the operations!"
			extra={<Button type="primary" onClick={() => dispatch({type: "POP_DOWN_TRANSFER_COMPLETE"})}>OK</Button>}
		/>
	);
}
