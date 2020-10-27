const initialState = {
	loginState: 'LOGIN',
	page: 'MESSENGER',
	user: {},
	balance: {},
	listUsers: [],
	chosenIndex: 0,

	transfer: {
		transferPopUp: false,
		transferComplete: false,
		transferLoding: false,
		errorCode: 0
	},

	transferHistory: [],
	isHistoryEmpty: false
};
const rootReducer = (state = initialState, action) => {
	switch (action.type) {
		case 'ALREADY_LOGIN':
			return {
				...state,
				loginState: 'ALREADY_LOGIN',
				user: action.user
			};
		case 'LOGIN':
			return {
				...state,
				loginState: 'LOGIN'
			};

		case 'REGISTER':
			return {
				...state,
				loginState: 'REGISTER'
			};

		case 'START_LOGIN':
			return {
				...state,
				user: {
					username: action.username
				}
			};

		case 'LOGOUT':
			return initialState;

		case 'GOTO_WALLET':
			return {
				...state,
				page: 'WALLET'
			};

		case 'GOTO_MESSENGER':
			return {
				...state,
				page: 'MESSENGER'
			};

		case 'LOAD_USERS':
			return {
				...state,
				listUsers: action.listUsers
			};

		case 'LOAD_CHAT':
			return {
				...state,
				listUsers: state.listUsers.map((content, i) => {
					return i === action.payload.index
						? { ...content, chat: action.payload.chat, moreChat: action.payload.moreChat }
						: content;
				})
			};

		case 'SETUP_WEBSOCKET':
			return {
				...state,
				websocket: action.websocket
			};

		case 'CHAT':
			return {
				...state,
				listUsers: state.listUsers.map((content, i) => {
					if (content.userId === action.chat.senderId || content.userId === action.chat.receiverId) {
						content.chat.push(action.chat);
						return { ...content, chat: content.chat };
					} else return content;
				})
			};

		case 'CHOOSE_CONVERSATION':
			return {
				...state,
				chosenIndex: action.chosenIndex
			};

		case 'POP_UP_TRANSFER':
			return {
				...state,
				transfer: {
					transferPopUp: true,
					transferComplete: false,
					transferLoding: false,
					errorCode: 0
				}
			};

		case 'POP_UP_TRANSFER_COMPLETE_SUCCESS':
			return {
				...state,
				transfer: {
					transferPopUp: true,
					transferComplete: true,
					transferLoding: false,
					errorCode: 0
				}
			};

		case 'POP_UP_TRANSFER_COMPLETE_WRONG_PASSWORD':
			return {
				...state,
				transfer: {
					transferPopUp: true,
					transferComplete: true,
					transferLoding: false,
					errorCode: 3
				}
			};

		case 'POP_UP_TRANSFER_COMPLETE_NOT_ENOUGH_MONEY':
			return {
				...state,
				transfer: {
					transferPopUp: true,
					transferComplete: true,
					transferLoding: false,
					errorCode: 4
				}
			};

		case 'POP_UP_TRANSFER_COMPLETE_INTERNAL_SERVER_ERROR':
			return {
				...state,
				transfer: {
					transferPopUp: true,
					transferComplete: true,
					transferLoding: false,
					errorCode: 5
				}
			};

		case 'POP_DOWN_TRANSFER':
			return {
				...state,
				transfer: {
					transferPopUp: false,
					transferComplete: false,
					transferLoding: false,
					errorCode: 0
				}
			};

		case 'TRANSFERING':
			return {
				...state,
				transfer: {
					transferPopUp: true,
					transferComplete: false,
					transferLoding: true,
					errorCode: 0
				}
			};

		case 'GET_BALANCE':
			return {
				...state,
				balance: action.payload
			};

		case 'GET_TRANSFER_HISTORY':
			return {
				...state,
				transferHistory: action.payload
			};

		case 'ADD_TRANSFER_HISTORY':
			return {
				...state,
				transferHistory: [ action.payload ].concat(state.transferHistory)
			};

		case 'DONE_GET_TRANSFER_HISTORY':
			return {
				...state,
				isHistoryEmpty: true
			};
		default:
			return state;
	}
};

export default rootReducer;
