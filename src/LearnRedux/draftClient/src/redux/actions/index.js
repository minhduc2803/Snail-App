import axios from 'axios'


export function alreadyLogin(user) {
    return {
        type: 'ALREADY_LOGIN',
        user: user
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
            if(result.status !== 200)
                alert(result.data.message)
            else{
                alert(result.data.data.length);
                dispatch({type: "LOAD_USERS", listUsers: result.data.data})
                
            }
            
        }).catch(error => {
            //alert(error.data.message)
        })
    }
}

