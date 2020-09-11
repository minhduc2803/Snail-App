export function alert(state = {}, action){
    switch(action.type){
        case "SUCCESS":
            return {
                type: 'alert-success',
                message: action.message
            };
        case "ERROR":
            return {
                type: 'alert-error',
                message: action.message
            };
        case "CLEAR":
            return {
                type: 'alert-clear',
                message: action.message
            };
        default:
            return state;
    }
}