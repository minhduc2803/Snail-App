import account from './account';
import alert from './alert';
import loadingInfo from './loadingInfo';
import { combineReducers } from 'redux'

const rootReducer = combineReducers({
    account,
    alert,
    loadingInfo
})

export default rootReducer;