import { bindActionCreators } from "redux"

const initialState = {
    loginState: "LOGIN",
    user: {},
    listUsers: [],
    chosenIndex: 0
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

        case "LOAD_CHAT":
            return {
                ...state,
                listUsers: state.listUsers.map((content, i) => {
                    return i === action.index ? {...content, chat: action.chat}
                                        : content
                })
            }
        default:
            return state;
    }
}

export default rootReducer;