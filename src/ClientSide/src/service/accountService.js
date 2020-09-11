import config from 'config';
import { authHeader }  from '../helpers';

export default accountService = {
    login,
    logout,
    register
}

function login(username, password){
    const requestOptions = {
        method: 'POST',
        header: { 'Content-Type': 'application/json'},
        body: JSON.stringify({username, password})
    };

    return fetch('http://localhost:8055/public/login', requestOptions)
        .then(handleResponse)
        .then(user => {
            localStorage.setItem('user', JSON.stringify(user));
            return user;
        })
}

function logout(){
    localStorage.removeItem('user');
}

function register(user){
    const requestOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json'},
        body: JSON.stringify(user)
    };

    return fetch('http://localhost:8055/public/register', requestOptions)
        .then(handleResponse);
}

function handleResponse(response){

    return ;
}
