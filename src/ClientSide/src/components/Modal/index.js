
import React, { useState } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { Modal, Button } from 'antd';
import Transfer from '../TransferCard';
import Result from '../Result';

export default function() {
  const [visible, setState] = useState(true);
  
  

  const showModal = () => {
    this.setState({
      visible: true,
    });
  };

  const handleOk = e => {
    console.log(e);
    this.setState({
      visible: false,
    });
  };

  const handleCancel = e => {
    console.log(e);
    this.setState({
      visible: false,
    });
  };

  
    const transferComplete = useSelector(state => state.transfer.transferComplete);
    let child = <Transfer />;
    if(transferComplete){
      child = <Result />;
    }
    return (
      <>
        <Button type="primary" onClick={(e) => showModal(e)}>
          Chuyển tiền
        </Button>
        <Modal
          title="Chuyển tiền"
          visible={visible}
          onCancel={(e) => handleCancel(e)}
          footer={[
            <Button key="back" onClick={(e) => handleCancel(e)}>
            Hủy bỏ
          </Button>
          ]}
        >
          {child}
        </Modal>
      </>
    );
}
