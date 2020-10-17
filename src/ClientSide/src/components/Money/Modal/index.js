
import React from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { Modal, Button } from 'antd';
import Transfer from '../TransferCard';
import Result from '../Result';
import '../../Frame/PageStructure/antd.css'
import { WalletOutlined } from '@ant-design/icons';

export default function() {
  const transferPopUp = useSelector(state => state.transfer.transferPopUp);
  const transferComplete = useSelector(state => state.transfer.transferComplete);
  const dispatch = useDispatch();

    let child = <Transfer />;
    if(transferComplete){
      child = <Result />;
    }
    return (
      <>
        <Button type="primary" icon={<WalletOutlined />} onClick={() => dispatch({type:"POP_UP_TRANSFER"})}>
          Chuyển tiền
        </Button>
        <Modal
          title="Chuyển tiền"
          visible={transferPopUp}
          onCancel={((e) => dispatch({type: "POP_DOWN_TRANSFER"}))}
          footer={[]}
        >
          {child}
        </Modal>
      </>
    );
}
