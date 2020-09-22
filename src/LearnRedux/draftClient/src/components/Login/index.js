import React, { useState } from 'react';
import { useDispatch } from 'react-redux';
import { asyncLogin } from '../../services/login.js'
import './Login.css';

const useInput = initValue => {
    const [value, setValue] = useState(initValue);
    return {
        value,
        setValue,
        bind: {
            value,
            onChange: event => {
                setValue(event.target.value);
            }
        }
    }
}
export default function Login(props) {
    const {value:username, bind:bindUsername} = useInput('');
    const {value:password, bind:bindPassword} = useInput('');
    const dispatch = useDispatch();
    return (
        <div className="container">
    <section id="content">
        <form  onSubmit={(event) => {
            asyncLogin(username,password)
            dispatch({type: "ALREADY_LOGIN", user: {username, password}})
        }}>
            <h1>Snail Project</h1>
            <div>
                <input type="text"  placeholder="Username" required={true} id="username" {...bindUsername}/>
            </div>
            <div>
                <input type="password"  placeholder="Password" required={true} id="password" {...bindPassword} />
            </div>
            <div>
                <input type="submit" value="Log in" />
                
                <button className="example_a" onClick={() => dispatch({type: "REGISTER"})}>Register</button>
            
            </div>
        </form>
        
    </section>
</div>
    );
}