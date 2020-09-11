export const alertActions {
    success, 
    error,
    clear
};

function success(message){
    return {type: "SUCCESS", message};
}

function error(message){
    return {type: "ERROR", message};
}

function clear(message){
    return {type: "CLEAR", message};
}