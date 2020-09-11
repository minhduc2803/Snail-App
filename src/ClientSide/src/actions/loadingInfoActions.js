const loadUSers = (user) => {
    return {
        type: "LOAD_USERS",
        payload: user
    }
}

const loadMessages = (usersList) => {
    return {
        type: "LOAD_MESSAGES",
        payload: usersList
    }
}

export default {
    loadUSers,
    loadMessages
}