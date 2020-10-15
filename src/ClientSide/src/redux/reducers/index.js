
const initialState = {
    loginState: "LOGIN",
    page: "MESSENGER",
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

        case "GOTO_WALLET":
            return {
                ...state,
                page: "WALLET"
            }

        case "GOTO_MESSENGER":
            return {
                ...state,
                page: "MESSENGER"
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

        case "SETUP_WEBSOCKET":
            return {
                ...state,
                websocket: action.websocket
            }

        case "CHAT":
            return {
                ...state,
                listUsers: state.listUsers.map((content, i) => {
                    if (content.userId === action.chat.senderId || content.userId === action.chat.receiverId){
                        content.chat.push(action.chat)
                        return {...content, chat: content.chat}
                    }else
                        return content
                })
            }

        case "CHOOSE_CONVERSATION":
            return {
                ...state,
                chosenIndex: action.chosenIndex
            }
            
        default:

            return state;
    }
}

export default rootReducer;