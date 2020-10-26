import React from 'react';
import { useSelector } from 'react-redux';
import Login from '../Login';
import Register from '../Register';
import Demo from '../PageStructure';
import './App.css';

export default function App() {
	const loginState = useSelector((state) => state.loginState);
	
	let child = <Register />;
	if (loginState === 'LOGIN') child = <Login />;
	else if (loginState === 'ALREADY_LOGIN') {
		child = <Demo />;
		
	}

	return <div className="App">{child}</div>;
}
