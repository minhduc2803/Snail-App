import store from '../redux/store';
import {alreadyLogin} from '../redux/actions';
import axios from 'axios';


export function asyncLogin(username, password) {
    axios({
        method: 'post',
        url: 'http://localhost:8055/api/public/login',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
        Username: username,
        Password: password
        }),
        validateStatus: () => true
    }).then(result => {
        console.log(result.data.message);

        store.dispatch(alreadyLogin(result.data.data));
        
    }).catch((error) => {
        
    })
}