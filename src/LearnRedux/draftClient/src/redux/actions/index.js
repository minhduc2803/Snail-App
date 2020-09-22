
export function alreadyLogin(user) {
    return {
        type: 'ALREADY_LOGIN',
        user: user
    }
}