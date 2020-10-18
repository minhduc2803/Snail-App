import React from 'react';
import { useSelector, useDispatch } from 'react-redux';
import Login from '../Login';
import Register from '../Register';
import Demo from '../PageStructure';
import './App.css';
import { init } from '../../../redux/actions';

export default function App() {
	const loginState = useSelector((state) => state.loginState);
	const dispatch = useDispatch();
	dispatch(init());
	let child = <Register />;
	if (loginState === 'LOGIN') child = <Login />;
	else if (loginState === 'ALREADY_LOGIN') {
		child = <Demo />;
		
	}

	return <div className="App">{child}</div>;
}
