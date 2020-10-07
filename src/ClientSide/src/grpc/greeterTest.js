import { FintechServiceClient } from './hello_grpc_web_pb';
import { HelloRequest } from './hello_pb';

const URL = 'http://localhost:8080';

const client = new FintechServiceClient(URL, null, null);

const metadata = { 'custom-header-1': 'value1'};

export default {
    getBalance: (name, callback) => {
        const request = new HelloRequest();
        request.setName(name);
        client.getBalance(request, metadata, callback);
    },

    getHistory: (name, callback) => {
        const request = new HelloRequest();
        request.setName(name);
        client.getHistory(request, metadata, callback);
    },

    transfer: (name, callback) => {
        const request = new HelloRequest();
        request.setName(name);
        client.transfer(request, metadata, callback);
    },

    getNotification: (name, callback) => {
        const request = new HelloRequest();
        request.setName(name);
        client.getNotification(request, metadata, callback);
    },
};