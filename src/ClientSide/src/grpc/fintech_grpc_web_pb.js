/**
 * @fileoverview gRPC-Web generated client stub for fintech
 * @enhanceable
 * @public
 */

// GENERATED CODE -- DO NOT EDIT!


/* eslint-disable */
// @ts-nocheck



const grpc = {};
grpc.web = require('grpc-web');

const proto = {};
proto.fintech = require('./fintech_pb.js');

/**
 * @param {string} hostname
 * @param {?Object} credentials
 * @param {?Object} options
 * @constructor
 * @struct
 * @final
 */
proto.fintech.FintechServiceClient =
    function(hostname, credentials, options) {
  if (!options) options = {};
  options['format'] = 'text';

  /**
   * @private @const {!grpc.web.GrpcWebClientBase} The client
   */
  this.client_ = new grpc.web.GrpcWebClientBase(options);

  /**
   * @private @const {string} The hostname
   */
  this.hostname_ = hostname;

};


/**
 * @param {string} hostname
 * @param {?Object} credentials
 * @param {?Object} options
 * @constructor
 * @struct
 * @final
 */
proto.fintech.FintechServicePromiseClient =
    function(hostname, credentials, options) {
  if (!options) options = {};
  options['format'] = 'text';

  /**
   * @private @const {!grpc.web.GrpcWebClientBase} The client
   */
  this.client_ = new grpc.web.GrpcWebClientBase(options);

  /**
   * @private @const {string} The hostname
   */
  this.hostname_ = hostname;

};


/**
 * @const
 * @type {!grpc.web.MethodDescriptor<
 *   !proto.fintech.BalanceRequest,
 *   !proto.fintech.BalanceResponse>}
 */
const methodDescriptor_FintechService_getBalance = new grpc.web.MethodDescriptor(
  '/fintech.FintechService/getBalance',
  grpc.web.MethodType.UNARY,
  proto.fintech.BalanceRequest,
  proto.fintech.BalanceResponse,
  /**
   * @param {!proto.fintech.BalanceRequest} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.fintech.BalanceResponse.deserializeBinary
);


/**
 * @const
 * @type {!grpc.web.AbstractClientBase.MethodInfo<
 *   !proto.fintech.BalanceRequest,
 *   !proto.fintech.BalanceResponse>}
 */
const methodInfo_FintechService_getBalance = new grpc.web.AbstractClientBase.MethodInfo(
  proto.fintech.BalanceResponse,
  /**
   * @param {!proto.fintech.BalanceRequest} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.fintech.BalanceResponse.deserializeBinary
);


/**
 * @param {!proto.fintech.BalanceRequest} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @param {function(?grpc.web.Error, ?proto.fintech.BalanceResponse)}
 *     callback The callback function(error, response)
 * @return {!grpc.web.ClientReadableStream<!proto.fintech.BalanceResponse>|undefined}
 *     The XHR Node Readable Stream
 */
proto.fintech.FintechServiceClient.prototype.getBalance =
    function(request, metadata, callback) {
  return this.client_.rpcCall(this.hostname_ +
      '/fintech.FintechService/getBalance',
      request,
      metadata || {},
      methodDescriptor_FintechService_getBalance,
      callback);
};


/**
 * @param {!proto.fintech.BalanceRequest} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @return {!Promise<!proto.fintech.BalanceResponse>}
 *     A native promise that resolves to the response
 */
proto.fintech.FintechServicePromiseClient.prototype.getBalance =
    function(request, metadata) {
  return this.client_.unaryCall(this.hostname_ +
      '/fintech.FintechService/getBalance',
      request,
      metadata || {},
      methodDescriptor_FintechService_getBalance);
};


/**
 * @const
 * @type {!grpc.web.MethodDescriptor<
 *   !proto.fintech.HistoryRequest,
 *   !proto.fintech.HistoryResponse>}
 */
const methodDescriptor_FintechService_getHistory = new grpc.web.MethodDescriptor(
  '/fintech.FintechService/getHistory',
  grpc.web.MethodType.UNARY,
  proto.fintech.HistoryRequest,
  proto.fintech.HistoryResponse,
  /**
   * @param {!proto.fintech.HistoryRequest} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.fintech.HistoryResponse.deserializeBinary
);


/**
 * @const
 * @type {!grpc.web.AbstractClientBase.MethodInfo<
 *   !proto.fintech.HistoryRequest,
 *   !proto.fintech.HistoryResponse>}
 */
const methodInfo_FintechService_getHistory = new grpc.web.AbstractClientBase.MethodInfo(
  proto.fintech.HistoryResponse,
  /**
   * @param {!proto.fintech.HistoryRequest} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.fintech.HistoryResponse.deserializeBinary
);


/**
 * @param {!proto.fintech.HistoryRequest} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @param {function(?grpc.web.Error, ?proto.fintech.HistoryResponse)}
 *     callback The callback function(error, response)
 * @return {!grpc.web.ClientReadableStream<!proto.fintech.HistoryResponse>|undefined}
 *     The XHR Node Readable Stream
 */
proto.fintech.FintechServiceClient.prototype.getHistory =
    function(request, metadata, callback) {
  return this.client_.rpcCall(this.hostname_ +
      '/fintech.FintechService/getHistory',
      request,
      metadata || {},
      methodDescriptor_FintechService_getHistory,
      callback);
};


/**
 * @param {!proto.fintech.HistoryRequest} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @return {!Promise<!proto.fintech.HistoryResponse>}
 *     A native promise that resolves to the response
 */
proto.fintech.FintechServicePromiseClient.prototype.getHistory =
    function(request, metadata) {
  return this.client_.unaryCall(this.hostname_ +
      '/fintech.FintechService/getHistory',
      request,
      metadata || {},
      methodDescriptor_FintechService_getHistory);
};


/**
 * @const
 * @type {!grpc.web.MethodDescriptor<
 *   !proto.fintech.TransferRequest,
 *   !proto.fintech.TransferResponse>}
 */
const methodDescriptor_FintechService_transfer = new grpc.web.MethodDescriptor(
  '/fintech.FintechService/transfer',
  grpc.web.MethodType.UNARY,
  proto.fintech.TransferRequest,
  proto.fintech.TransferResponse,
  /**
   * @param {!proto.fintech.TransferRequest} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.fintech.TransferResponse.deserializeBinary
);


/**
 * @const
 * @type {!grpc.web.AbstractClientBase.MethodInfo<
 *   !proto.fintech.TransferRequest,
 *   !proto.fintech.TransferResponse>}
 */
const methodInfo_FintechService_transfer = new grpc.web.AbstractClientBase.MethodInfo(
  proto.fintech.TransferResponse,
  /**
   * @param {!proto.fintech.TransferRequest} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.fintech.TransferResponse.deserializeBinary
);


/**
 * @param {!proto.fintech.TransferRequest} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @param {function(?grpc.web.Error, ?proto.fintech.TransferResponse)}
 *     callback The callback function(error, response)
 * @return {!grpc.web.ClientReadableStream<!proto.fintech.TransferResponse>|undefined}
 *     The XHR Node Readable Stream
 */
proto.fintech.FintechServiceClient.prototype.transfer =
    function(request, metadata, callback) {
  return this.client_.rpcCall(this.hostname_ +
      '/fintech.FintechService/transfer',
      request,
      metadata || {},
      methodDescriptor_FintechService_transfer,
      callback);
};


/**
 * @param {!proto.fintech.TransferRequest} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @return {!Promise<!proto.fintech.TransferResponse>}
 *     A native promise that resolves to the response
 */
proto.fintech.FintechServicePromiseClient.prototype.transfer =
    function(request, metadata) {
  return this.client_.unaryCall(this.hostname_ +
      '/fintech.FintechService/transfer',
      request,
      metadata || {},
      methodDescriptor_FintechService_transfer);
};


module.exports = proto.fintech;

