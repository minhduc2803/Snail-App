import React from 'react';
import Avatar from 'react-avatar';
import { useSelector, useDispatch } from 'react-redux';
import '../../Frame/PageStructure/antd.css';
import { Form, Select, InputNumber, Button, Input } from 'antd';
import { transfer } from '../../../redux/actions';

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
	wrapperCol: { offset: 4, span: 16 }
};

const Demo = () => {
	const users = useSelector((state) => state.listUsers);
	const transferLoading = useSelector((state) => state.transfer.transferLoding);
	const user = useSelector(state => state.listUsers[state.chosenIndex]);
	const dispatch = useDispatch();
	const listUsers = users.map((u) => (
		<Option key={u.userId} value={`${u.userId} @${u.username} ${u.fullName}`}>
			@{u.username}
			<br />
			<Avatar name={`${u.fullName}`} size="30" round={true} />
			{u.fullName}
		</Option>
	));

	const onFinish = (values) => {
		console.log('Received values of form: ', values);
		const transerInfo = {
			receiver_id: parseInt(values.receivers.split(" ")[0]),
			amount: values.amount,
			message: values.message,
			password: values.password
		};
		dispatch(transfer(transerInfo));
	};
	const [ form ] = Form.useForm();
	return (
		<Form name="control-hooks" form={form} {...formItemLayout} onFinish={onFinish}>
			<Form.Item
				name="receivers"
				label="Người nhận"
				rules={[
					{
						required: true,
						message: 'Bạn chưa chọn người nhận',
						
					}
				]}
			>
				<Select
					showSearch
					defaultValue={`@${user.username}`}
					placeholder="Select a person"
					
				>
					{listUsers}
				</Select>
			</Form.Item>

			<Form.Item
				label="Số tiền"
				rules={[
					{
						required: true
					}
				]}
			>
				<Form.Item
					name="amount"
					noStyle
					rules={[
						{
							required: true,
							message: 'Bạn cần nhập vào số tiền là một số nguyên',
							type: 'integer'
						}
					]}
				>
					<InputNumber
						placeholder="Nhập số tiền cần chuyển"
						style={{ width: 220 }}
						formatter={(value) => `${value}`.replace(/\B(?=(\d{3})+(?!\d))/g, ',')}
						parser={(value) => value.replace(/\$\s?|(,*)/g, '')}
					/>
				</Form.Item>
				<span className="ant-form-text"> VND</span>
			</Form.Item>
			<Form.Item label="Lời nhắn" name="message">
				<Input />
			</Form.Item>
			<Form.Item
				label="Mật khẩu"
				name="password"
				rules={[ { required: true, message: 'Bạn chưa nhập mật khẩu!' } ]}
			>
				<Input.Password />
			</Form.Item>
			<Form.Item {...tailLayout} style={{ textAlign: 'center' }}>
				<Button loading={transferLoading} type="primary" htmlType="submit" className="login-form-button">
					Giao dịch
				</Button>
			</Form.Item>
		</Form>
	);
};
export default Demo;
