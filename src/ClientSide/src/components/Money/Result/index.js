import React from 'react';
import { useDispatch, useSelector } from 'react-redux';
import '../../Frame/PageStructure/antd.css'

import { Result, Button } from 'antd';
import { CheckCircleOutlined, ExclamationCircleOutlined } from '@ant-design/icons';

export default function() {
    const transferSuccess = useSelector(state => state.transfer.transferSuccess)
    const dispatch = useDispatch();

    let child  = <CheckCircleOutlined />;
    let notification = "Giao dịch thành công";
    if(!transferSuccess){
        child  = <ExclamationCircleOutlined style={{color:"#f5222d"}}/>;
        notification = "Giao dịch thất bại";
    }

	return (
		<Result
			icon={child}
            title={notification}
			extra={<Button type="primary" onClick={() => dispatch({type: "POP_DOWN_TRANSFER"})}>OK</Button>}
		/>
	);
}
