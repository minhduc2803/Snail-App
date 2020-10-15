import React from 'react';
import './TransferCard.css';
import { Form, Select, InputNumber, Button, Input } from 'antd';
import { UploadOutlined, InboxOutlined } from '@ant-design/icons';
const { Option } = Select;
const formItemLayout = {
	labelCol: {
		span: 6
	},
	wrapperCol: {
		span: 14
	}
};
const tailLayout = {
  wrapperCol: { offset: 4, span: 16 },
};

const Demo = () => {
	const onFinish = (values) => {
		console.log('Received values of form: ', values);
	};
	const [ form ] = Form.useForm();
	return (
		<Form
			name="control-hooks"
			form={form}
			{...formItemLayout}
			onFinish={onFinish}
			initialValues={{
				['input-number']: 3,
				['checkbox-group']: [ 'A', 'B' ],
				rate: 3.5
			}}
		>
			<Form.Item
				name="select-multiple"
				label="Người nhận"
				rules={[
					{
						required: true,
						message: 'Bạn chưa chọn người nhận',
						type: 'array'
					}
				]}
			>
				<Select mode="multiple" placeholder="Nhấp và chọn người nhận">
					<Option value="red">Red</Option>
					<Option value="green">Green</Option>
					<Option value="blue">Blue</Option>
				</Select>
			</Form.Item>

			<Form.Item
				label="Số tiền"
				rules={[
          {
            required: true,
          }
        ]}
			>
				<Form.Item
          name="Amount-of-money"
					noStyle
					rules={[
            {
              required: true,
              message: 'Bạn chưa nhập số tiền',
            }
          ]}
				>
					<InputNumber placeholder="Nhập số tiền cần chuyển" style={{width:220}}
      formatter={value => `${value}`.replace(/\B(?=(\d{3})+(?!\d))/g, ',')}
      parser={value => value.replace(/\$\s?|(,*)/g, '')}
      
    />
				</Form.Item>
				<span className="ant-form-text"> VND</span>
			</Form.Item>
      <Form.Item
        label="Mật khẩu"
        name="password"
        rules={[{ required: true, message: 'Bạn chưa nhập mật khẩu!' }]}
      >
        <Input.Password />
      </Form.Item>
      <Form.Item {...tailLayout} style={{textAlign: 'center'}}>
        <Button type="primary" htmlType="submit" className="login-form-button">
          Giao dịch
        </Button>
        
      </Form.Item>
		</Form>
	);
};
export default Demo;
