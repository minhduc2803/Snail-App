import React from 'react';

import { Layout, Menu, Popconfirm, Button } from 'antd';
import { MessageOutlined, DollarCircleOutlined, ContactsOutlined, LogoutOutlined } from '@ant-design/icons';
import './Demo.css';
import Avatar from 'react-avatar';
import { fixed } from 'global-prefix';
import Messenger from '../Messenger';

const { Header, Content, Footer, Sider } = Layout;

const Demo = () => {
	return (
		<Layout>
			<Sider
				style={{
					overflow: 'auto',
					height: '100vh',
					position: 'fixed',
					right: 0
				}}
			>
				<Menu theme="dark" mode="inline" defaultSelectedKeys={[ '2' ]} style={{ marginTop: 200, fontSize: 16 }}>
					<Menu.Item key="1" icon={<DollarCircleOutlined />}>
						Wallet
					</Menu.Item>
					<Menu.Item key="2" icon={<MessageOutlined />}>
						Messenger
					</Menu.Item>
					<Menu.Item key="3" icon={<ContactsOutlined />}>
						People
					</Menu.Item>
				</Menu>

				<div className="logo" style={{ position: 'fixed', bottom: 0, textAlign: 'center', width: 200 }}>
					<Menu theme="dark" mode="inline" selectable={false} style={{ textAlign: 'center' }}>
						<Menu.Item icon={<Avatar name={'Minh Đức'} size="35" round={true} />} />
						<Menu.Item style={{ color: '#feffe6', fontSize: 17 }}>Minh Đức</Menu.Item>

						<Menu.Item icon={<LogoutOutlined />}>
							<Popconfirm title="Are you sure？" okText="Yes" cancelText="No">
								Logout
							</Popconfirm>
						</Menu.Item>
					</Menu>
				</div>
			</Sider>
			<Layout className="site-layout" style={{ marginRight: 200 }}>
				<Content style={{ }}>
					<Messenger />
				</Content>
				
			</Layout>
		</Layout>
	);
};

export default Demo;
