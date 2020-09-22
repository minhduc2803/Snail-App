import React from 'react';
import { useDispatch } from 'react-redux';
import './Register.css';

export default function Register(props) {
    const dispatch = useDispatch();
    return (
        <div className="container">
    <section id="content">
        <form action="">
        <h1>Snail Project</h1>
            <div>
                <input type="text" placeholder="Username" required="" id="username" />
            </div>
            <div>
                <input type="text" placeholder="Fullname" required="" id="fullname" />
            </div>
            <div>
                <input type="password" placeholder="Password" required="" id="password" />
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