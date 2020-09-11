const login = (userName, password) => {
    return {
        type: "LOGIN",
        payload:{
            userName: userName,
            password: password
        }
    }
}

const logout = () => {
    return {
        type: "LOGOUT"
    }
}

const register = (user) => {
    return {
        type: "REGISTER",
        payload: user
    }
}

export default {
    login,
    logout,
    register
}