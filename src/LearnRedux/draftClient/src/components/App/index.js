import React from 'react';
import { useSelector } from 'react-redux'
import Messenger from '../Messenger';
import Login from '../Login'
import Register from '../Register'


export default function App() {
  const loginState = useSelector(state => state.loginState);
  let child = <Login />;
  if(loginState === "REGISTER")
    child = <Register />;
  else if(loginState === "ALREADY_LOGIN")
    child = <Messenger />;

    return (
      <div className="App">
        {child}
      </div>
    );
}