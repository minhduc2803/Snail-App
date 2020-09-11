import { createStore, applyMiddleware } from 'redux';
import thunkMiddleware from 'redux-thunk';
import {createlogger } from 'redux logger'
import { allReducers } from '../reducers'

const loggerMiddleware = createlogger();

const loggerMiddleware = createStore(
    allReducers,
    applyMiddleware(
        thunkMiddleware,
        loggerMiddleware
    )
);