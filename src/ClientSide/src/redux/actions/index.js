import axios from 'axios'


export function alreadyLogin(user) {
    return {
        type: 'ALREADY_LOGIN',
        user: user
    }
}

export function asyncSetupWebSocket(user){
    return (dispatch) => {
        const url = `ws://localhost:9009/chat?token=${user.token}`;
        var ws = new WebSocket(url);
        ws.onopen = function(){
            console.log("WebSocket is connected");
        }

        ws.onmessage = function(evt){
            console.log("Receive a mess");
            console.log(JSON.parse(evt.data));
            dispatch({type: "CHAT", chat: JSON.parse(evt.data)})
        }

        ws.onclose = function() {
            console.log("webSocket is closed");
        }
        
        dispatch({type: "SETUP_WEBSOCKET",websocket: ws})

    }
}

export function asyncLogin(username, password) {
    return (dispatch, getState) => {
        dispatch({type: "START_LOGIN", username: username})
        let data = {
            Username: username,
            Password: password
        }
        data = JSON.stringify(data)
    
        return axios({
            method: 'post',
            url: 'http://localhost:8055/api/public/login',
            headers: {
                'Content-Type': 'application/json'
            },
            data: data,
            validateStatus: () => true
        }).then(result => {
            if(result.status !== 200)
                alert(result.data.message)
            else{
                
                let user = {
                    userID: result.data.data.UserID,
                    username: getState().user.username,
                    fullname: result.data.data.Fullname,
                    token: result.data.data.token
                }
                console.log(result.data)
                dispatch(alreadyLogin(user));
                dispatch(asyncSetupWebSocket(user));
            }
            
        }).catch(error => {
            //alert(error.data.message)
        })
    }
}

export function asyncRegister(username, fullname, password) {
    return (dispatch) => {
        let data = {
            Username: username,
            Fullname: fullname,
            Password: password
        }
        data = JSON.stringify(data)
    
        return axios({
            method: 'post',
            url: 'http://localhost:8055/api/public/register',
            headers: {
                'Content-Type': 'application/json'
            },
            data: data,
            validateStatus: () => true
        }).then(result => {
            if(result.status !== 200)
                alert(result.data.message)
            else{
                alert("Register successfully")
                dispatch({type: "LOGIN"});

            }
            
        }).catch(error => {
            //alert(error.data.message)
        })
    }
}

export function asyncLoadUsers() {
    return (dispatch, getState) => {
        
        return axios({
            method: 'get',
            url: 'http://localhost:8055/api/protected/list-user',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${getState().user.token}`,
            },
            validateStatus: () => true
        }).then(result => {
            if(result.status !== 200) {
                alert(result.data.message)

                return []
            }
            else{
                
                dispatch({type: "LOAD_USERS", listUsers: result.data.data})
                return result.data.data;
            }
            
        })
    }
}

export function asyncLoadChat(chosenIndex, content){
    return (dispatch, getState) => {
        let data = {
            UserReceiveID: content.UserID,
        }
        data = JSON.stringify(data)
        console.log(data)
        return axios({
            method: 'post',
            url: 'http://localhost:8055/api/protected/list-chat',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${getState().user.token}`,
            },
            data: data,
            validateStatus: () => true
        }).then(result => {
            if(result.status !== 200)
                alert(result.data.message)
            else{
               
                dispatch({type: "LOAD_CHAT", chat: result.data.data, index: chosenIndex})
                
            }
            
        }).catch(error => {
            //alert(error.data.message)
        })
    }
}

