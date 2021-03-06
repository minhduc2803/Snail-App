import React from 'react';
import { useDispatch } from 'react-redux';
import { asyncLogin } from '../../../redux/actions'

import './Login.css';

export default function Login(props) {
    const usernameInput = React.useRef(null);
    const passwordInput = React.useRef(null);
    const dispatch = useDispatch();

    function handleSubmit(event){
        event.preventDefault();
        const username = usernameInput.current.value;
        const password = passwordInput.current.value
        dispatch(asyncLogin(username,password));
    }

    return (
        <div className="container">
    <section id="content">
        <form  onSubmit={(event) => { handleSubmit(event) }}>
            <h1>Snail Project</h1>
            <div>
                <input type="text" ref={usernameInput} placeholder="Tên tài khoản" required={true} id="username"/>
            </div>
            <div>
                <input type="password" ref={passwordInput} placeholder="Mật khẩu" required={true} id="password" />
            </div>
            <div>
                <input type="submit" value="Đăng nhập" />
                
                <button className="example_a" onClick={() => dispatch({type: "REGISTER"})}>Đăng ký</button>
            
            </div>
        </form>
        
    </section>
</div>
    );
}