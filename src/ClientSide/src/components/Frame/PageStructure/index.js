import React from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { Layout, Menu, Popconfirm } from 'antd';
import { MessageOutlined, DollarCircleOutlined, ContactsOutlined, LogoutOutlined } from '@ant-design/icons';

import Avatar from 'react-avatar';
import Messenger from '../../Mess/Messenger';
import Wallet from '../../Money/Wallet';
import { logout } from '../../../redux/actions';


const { Header, Content, Footer, Sider } = Layout;

const Demo = () => {
	const pageState = useSelector( state => state.page);
	const user = useSelector(state => state.user);
	const dispatch = useDispatch();

	let child = <Messenger />;
  if(pageState === "WALLET"){
    child = <Wallet />;
  }

	return (
		<Layout>
			<Sider
				style={{
					overflow: 'auto',
					height: '100vh',
					position: 'fixed',
					left: 0
				}}
			>
				<Menu theme="dark" mode="inline" defaultSelectedKeys={[ '2' ]} style={{ marginTop: 200, fontSize: 16, textAlign: 'center' }}>
					<Menu.Item onClick={() => dispatch({type:"GOTO_WALLET"})} key="1" icon={<DollarCircleOutlined />}>
						Ví của tôi
					</Menu.Item>
					<Menu.Item onClick={() => dispatch({type:"GOTO_MESSENGER"})} key="2" icon={<MessageOutlined />}>
						Trò chuyện
					</Menu.Item>
					<Menu.Item key="3" icon={<ContactsOutlined />}>
						Mọi người
					</Menu.Item>
				</Menu>

				<div className="logo" style={{ position: 'fixed', bottom: 100, textAlign: 'center', width: 200 }}>
					<Menu theme="dark" mode="inline" selectable={false} style={{ textAlign: 'center' }}>
						<Menu.Item icon={<Avatar name={user.fullName} size="35" round={true} />} />
						<Menu.Item style={{ color: '#feffe6', fontSize: 17 }}>{user.fullName}</Menu.Item>

						<Menu.Item icon={<LogoutOutlined />}>
							<Popconfirm 
							title="Bạn có chắc chắn muốn đăng xuất？" okText="Có" cancelText="Không"
							onConfirm={() => dispatch(logout())}
							>
								Đăng xuất
							</Popconfirm>
						</Menu.Item>
					</Menu>
				</div>
			</Sider>
			<Layout className="site-layout" style={{  marginLeft: 200, margintop:100 }}>
				<Content style={{}} >
					
					{child}
				</Content>
				
			</Layout>
		</Layout>
	);
};

export default Demo;
