import axios from 'axios';
import grpc from '../../grpc/fintechService';

export function alreadyLogin(user) {
	return {
		type: 'ALREADY_LOGIN',
		user: user
	};
}

export function init() {
	return (dispatch, getState) => {
		const ws = getState().websocket;
		if (ws !== undefined) {
			ws.close();
		}
		let user = window.localStorage.getItem('user');
		console.log('user');
		console.log(user);
		if (user !== null) {
			user = JSON.parse(user);
			dispatch(setup(user));
		}
	};
}

export function setup(user) {
	return (dispatch, getState) => {
		dispatch(alreadyLogin(user));
		dispatch(asyncSetupWebSocket(user));
		dispatch(asyncLoadUsers()).then((result) => {
			for (let i = 0; i < result.length; i++) {
				dispatch(asyncLoadChat(i, result[i]));
			}
		});
		dispatch(getBalance());
		dispatch(getHistory());
	};
}

export function asyncSetupWebSocket(user) {
	return (dispatch) => {
		const url = `ws://localhost:9009/chat?token=${user.token}`;
		var ws = new WebSocket(url);
		ws.onopen = function() {
			console.log('WebSocket is connected');
		};

		ws.onmessage = function(evt) {
			console.log('Receive a mess');
			const data = JSON.parse(evt.data);
			console.log();
			if (data.responseType == 1) dispatch({ type: 'CHAT', chat: data.data });
			else {
				const balance = {
					balance: data.data.balance,
					lastTimeUpdate: data.data.transferTime
				};
				const transferHistory = {
					partnerId: data.data.partnerId,
					transferType: data.data.transferType - 1,
					amount: data.data.amount,
					message: data.data.message,
					balance: data.data.balance,
					transferTime: data.data.transferTime,
					username: data.data.username,
					fullName: data.data.fullName
				};
				dispatch({ type: 'GET_BALANCE', payload: balance });
				dispatch({ type: 'ADD_TRANSFER_HISTORY', payload: transferHistory });
			}
		};

		ws.onclose = function() {
			console.log('webSocket is closed');
		};

		dispatch({ type: 'SETUP_WEBSOCKET', websocket: ws });
	};
}

export function asyncLogin(username, password) {
	return (dispatch, getState) => {
		dispatch({ type: 'START_LOGIN', username: username });
		let data = {
			username: username,
			password: password
		};
		data = JSON.stringify(data);

		return axios({
			method: 'post',
			url: 'http://localhost:8055/api/public/login',
			headers: {
				'Content-Type': 'application/json'
			},
			data: data,
			validateStatus: () => true
		})
			.then((result) => {
				if (result.status !== 200) {
					console.log(result);
					alert(result.data.message);
				} else {
					console.log('data login');
					console.log(result);
					let user = {
						userId: result.data.data.userId,
						username: getState().user.username,
						fullName: result.data.data.fullName,
						token: result.data.data.token
					};

					dispatch(setup(user));
					window.localStorage.setItem('user', JSON.stringify(getState().user));
				}
			})
			.catch((error) => {
				alert(error);
			});
	};
}

export function logout() {
	return (dispatch, getState) => {
		getState().websocket.close();
		window.localStorage.clear();
		dispatch({ type: 'LOGOUT' });
		dispatch({ type: 'LOGIN' });
	};
}

export function asyncRegister(username, fullname, password) {
	return (dispatch) => {
		let data = {
			username: username,
			fullName: fullname,
			password: password
		};
		data = JSON.stringify(data);

		return axios({
			method: 'post',
			url: 'http://localhost:8055/api/public/register',
			headers: {
				'Content-Type': 'application/json'
			},
			data: data,
			validateStatus: () => true
		})
			.then((result) => {
				if (result.status !== 200) alert(result.data.message);
				else {
					alert('Register successfully');
					dispatch({ type: 'LOGIN' });
				}
			})
			.catch((error) => {
				//alert(error.data.message)
			});
	};
}

export function asyncLoadUsers() {
	return (dispatch, getState) => {
		return axios({
			method: 'get',
			url: 'http://localhost:8055/api/protected/list-user',
			headers: {
				'Content-Type': 'application/json',
				Authorization: `Bearer ${getState().user.token}`
			},
			validateStatus: () => true
		}).then((result) => {
			if (result.status !== 200) {
				alert(result.data.message);

				return [];
			} else {
				dispatch({ type: 'LOAD_USERS', listUsers: result.data.data });
				return result.data.data;
			}
		});
	};
}

export function asyncLoadChat(chosenIndex, content) {
	return (dispatch, getState) => {
		let data = {
			partnerId: content.userId
		};
		data = JSON.stringify(data);
		return axios({
			method: 'post',
			url: 'http://localhost:8055/api/protected/list-chat',
			headers: {
				'Content-Type': 'application/json',
				Authorization: `Bearer ${getState().user.token}`
			},
			data: data,
			validateStatus: () => true
		})
			.then((result) => {
				if (result.status !== 200) alert(result.data.message);
				else {
					dispatch({ type: 'LOAD_CHAT', chat: result.data.data, index: chosenIndex });
				}
			})
			.catch((error) => {
				//alert(error.data.message)
			});
	};
}

export function transfer(transfer_info) {
	return (dispatch, getState) => {
		transfer_info = {
			...transfer_info,
			sender_id: getState().user.userId
		};
		const metadata = { Authorization: 'Bearer ' + getState().user.token };

		grpc.transfer(metadata, transfer_info, (err, response) => {
			if (response == null) {
				dispatch({ type: 'POP_UP_TRANSFER_COMPLETE_FAILED' });
			} else {
				response = response.toObject();
				console.log(response);
				switch (response.error.code) {
					case 0:
						dispatch({ type: 'POP_UP_TRANSFER_COMPLETE_SUCCESS' });
						dispatch({
							type: 'GET_BALANCE',
							payload: {
								balance: response.data.historyitem.balance,
								lastTimeUpdate: response.data.historyitem.transferTime
							}
						});
						dispatch({ type: 'ADD_TRANSFER_HISTORY', payload: response.data.historyitem });
						break;

					case 1:
						dispatch({ type: 'POP_UP_TRANSFER_COMPLETE_FAILED' });
						break;
					case 2:
						dispatch({ type: 'POP_UP_TRANSFER_COMPLETE_FAILED' });
						break;
					case 3:
						dispatch({ type: 'POP_UP_TRANSFER_COMPLETE_FAILED' });
						break;
					case 4:
						dispatch({ type: 'POP_UP_TRANSFER_COMPLETE_FAILED' });
						break;
					case 5:
						dispatch({ type: 'POP_UP_TRANSFER_COMPLETE_FAILED' });
						break;
					default:
						dispatch({ type: 'POP_UP_TRANSFER_COMPLETE_FAILED' });
						break;
				}
			}
		});

		dispatch({ type: 'TRANSFERING' });
	};
}

export function getBalance() {
	return (dispatch, getState) => {
		const metadata = { Authorization: 'Bearer ' + getState().user.token };

		grpc.getBalance(metadata, (err, response) => {
			let balance = {
				balance: 'NETWORK_ERROR',
				lastTimeUpdate: 'NETWORK_ERROR'
			};
			if (response != null) {
				balance = {
					balance: response.getData().getBalance(),
					lastTimeUpdate: response.getData().getLastTimeUpdateBalance()
				};
			}

			dispatch({ type: 'GET_BALANCE', payload: balance });
		});
	};
}

export function getHistory() {
	return (dispatch, getState) => {
		const metadata = { Authorization: 'Bearer ' + getState().user.token };

		grpc.getHistory(metadata, (err, response) => {
			//console.log(response.toObject());
			// const history = {
			//     partnerId: response.getData.getPartnerId(),
			//     transferType: response.getData.getTransferType(),
			//     amount: response.getData.getAmount(),
			//     message: response.getData.getMessage(),
			//     transferTime: response.getData.getTransferTime(),
			//     username: response.getData.getUsername(),
			//     fullName: response.getData.getFullName()
			// };
			if (response != null)
				dispatch({
					type: 'GET_TRANSFER_HISTORY',
					payload: response.toObject().data.historyItemsList.reverse()
				});
		});
	};
}
