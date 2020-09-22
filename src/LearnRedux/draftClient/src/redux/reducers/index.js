

const rootReducer = (state = {loginState: "LOGIN"}, action) => {
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

        default:
            return state;
    }
}

export default rootReducer;