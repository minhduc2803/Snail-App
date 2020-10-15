
import React from 'react';
import { Modal, Button } from 'antd';
import Transfer from '../TransferCard';

class ModalDemo extends React.Component {
  state = { visible: false };

  showModal = () => {
    this.setState({
      visible: true,
    });
  };

  handleOk = e => {
    console.log(e);
    this.setState({
      visible: false,
    });
  };

  handleCancel = e => {
    console.log(e);
    this.setState({
      visible: false,
    });
  };

  render() {
    return (
      <>
        <Button type="primary" onClick={this.showModal}>
          Chuyển tiền
        </Button>
        <Modal
          title="Chuyển tiền"
          visible={this.state.visible}
          onCancel={this.handleCancel}
          footer={[
            <Button key="back" onClick={this.handleCancel}>
            Hủy bỏ
          </Button>
          ]}
        >
          <Transfer />
        </Modal>
      </>
    );
  }
}

export default ModalDemo;