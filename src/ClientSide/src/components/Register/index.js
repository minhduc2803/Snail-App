import React from 'react';
import { useDispatch } from 'react-redux';
import { asyncRegister } from '../../redux/actions';
import './Register.css';

export default function Register(props) {
    const usernameInput = React.useRef(null);
    const fullnameInput = React.useRef(null);
    const passwordInput = React.useRef(null);
    const dispatch = useDispatch();

    function handleSubmit(event){
        event.preventDefault();
        const username = usernameInput.current.value;
        const fullname = fullnameInput.current.value;
        const password = passwordInput.current.value
        dispatch(asyncRegister(username,fullname,password));
    }
   
    return (
        <div className="container">
    <section id="content">
        <form  onSubmit={(event) => { handleSubmit(event) }}>
        <h1>Snail Project</h1>
            <div>
                <input type="text" ref={usernameInput} placeholder="Username" required="" id="username" />
            </div>
            <div>
                <input type="text" ref={fullnameInput} placeholder="Fullname" required="" id="fullname" />
            </div>
            <div>
                <input type="password" ref={passwordInput} placeholder="Password" required="" id="password" />
            </div>
            <div>
                <input type="submit" value="Register" />
                <button className="example_a" onClick={() => dispatch({type: "LOGIN"})}>Login</button>
            
            </div>
        </form>
        
    </section>
</div>
    );
}