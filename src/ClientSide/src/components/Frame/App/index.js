import React from 'react';
import { useSelector, useDispatch } from 'react-redux';
import Login from '../Login';
import Register from '../Register';
import Demo from '../PageStructure';
import './App.css';
import { asyncLoadUsers, asyncLoadChat } from '../../../redux/actions';
import { getBalance, getHistory } from '../../../redux/actions';

export default function App() {
	const loginState = useSelector((state) => state.loginState);
	const dispatch = useDispatch();
	let child = <Register />;
	if (loginState === 'LOGIN') child = <Login />;
	else if (loginState === 'ALREADY_LOGIN') {
		child = <Demo />;
		dispatch(asyncLoadUsers()).then((result) => {
			for (let i = 0; i < result.length; i++) {
				dispatch(asyncLoadChat(i, result[i]));
			}
		});
		dispatch(getBalance());
		dispatch(getHistory());
	}

	return <div className="App">{child}</div>;
}
