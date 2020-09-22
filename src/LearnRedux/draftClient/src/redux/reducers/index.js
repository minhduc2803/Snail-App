import { bindActionCreators } from "redux"

const initialState = {
    loginState: "LOGIN",
    user: {},
    listUsers: [],
    chats: []
}
const rootReducer = (state = initialState, action) => {
    switch(action.type){
        case "ALREADY_LOGIN":
            return {
                ...state,
                loginState: "ALREADY_LOGIN",
                user: action.user
            }
        case "LOGIN":
            return {
                ...state,
                loginState: "LOGIN"
            }

        case "REGISTER":
            return {
                ...state,
                loginState: "REGISTER"
            }
        
        case "START_LOGIN":
            return {
                ...state,
                user : {
                    username: action.username
                }
            }
        
        case "LOAD_USERS":
            return {
                ...state,
                listUsers: action.listUsers
            }
        default:
            return state;
    }
}

export default rootReducer;