import { FintechServiceClient } from './fintech_grpc_web_pb';
import { BalanceRequest, HistoryRequest, NotificationRequest, TransferRequest } from './fintech_pb';

const URL = 'http://localhost:8080';

const client = new FintechServiceClient(URL, null, null);

//const metadata = { 'custom-header-1': 'value1'};

export default {
    getBalance: (metadata, callback) => {
        const request = new BalanceRequest();
        client.getBalance(request, metadata, callback);
    },

    getHistory: (metadata, offset, callback) => {
        const request = new HistoryRequest();
        request.setOffset(offset);
        client.getHistory(request, metadata, callback);
    },

    transfer: (metadata, transfer_info, callback) => {
        const request = new TransferRequest();
        request.setSenderId(transfer_info.sender_id);
        request.setReceiverId(transfer_info.receiver_id);
        request.setAmount(transfer_info.amount);
        request.setMessage(transfer_info.message);
        request.setPassword(transfer_info.password);
        client.transfer(request, metadata, callback);
    },
};