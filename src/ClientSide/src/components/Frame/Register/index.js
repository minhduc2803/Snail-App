import React from 'react';
import { useDispatch } from 'react-redux';
import { asyncRegister } from '../../../redux/actions';
import './Register.css';

export default function Register(props) {
    const usernameInput = React.useRef(null);
    const fullNameInput = React.useRef(null);
    const passwordInput = React.useRef(null);
    const dispatch = useDispatch();

    function handleSubmit(event){
        event.preventDefault();
        const username = usernameInput.current.value;
        const fullName = fullNameInput.current.value;
        const password = passwordInput.current.value
        dispatch(asyncRegister(username,fullName,password));
    }
   
    return (
        <div className="container">
    <section id="content">
        <form  onSubmit={(event) => { handleSubmit(event) }}>
        <h1>Snail Project</h1>
            <div>
                <input type="text" ref={usernameInput} placeholder="Tên tài khoản" required="" id="username" />
            </div>
            <div>
                <input type="text" ref={fullNameInput} placeholder="Tên đầy đủ" required="" id="fullname" />
            </div>
            <div>
                <input type="password" ref={passwordInput} placeholder="Mật khẩu" required="" id="password" />
            </div>
            <div>
                <input type="submit" value="Đăng ký" />
                <button className="example_a" onClick={() => dispatch({type: "LOGIN"})}>Đăng nhập</button>
            
            </div>
        </form>
        
    </section>
</div>
    );
}