import React from 'react';
import ReactDOM from 'react-dom';
import './assets/index.css';
import App from './components/Frame/App';
import * as serviceWorker from './serviceWorker';
//import { asyncLogin } from './services/login.js';

import {Provider} from 'react-redux'
import store from './redux/store'
import { init } from './redux/actions'

store.dispatch(init())

ReactDOM.render(
    <Provider store={store}>
    <App />
    </Provider>, 
document.getElementById('root'));





// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: http://bit.ly/CRA-PWA
serviceWorker.unregister();
