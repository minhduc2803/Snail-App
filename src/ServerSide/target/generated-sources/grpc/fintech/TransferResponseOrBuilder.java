// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: fintech.proto

package fintech;

public interface TransferResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:fintech.TransferResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.fintech.Error error = 1;</code>
   */
  boolean hasError();
  /**
   * <code>.fintech.Error error = 1;</code>
   */
  fintech.Error getError();
  /**
   * <code>.fintech.Error error = 1;</code>
   */
  fintech.ErrorOrBuilder getErrorOrBuilder();

  /**
   * <code>.fintech.TransferResponse.Data data = 2;</code>
   */
  boolean hasData();
  /**
   * <code>.fintech.TransferResponse.Data data = 2;</code>
   */
  fintech.TransferResponse.Data getData();
  /**
   * <code>.fintech.TransferResponse.Data data = 2;</code>
   */
  fintech.TransferResponse.DataOrBuilder getDataOrBuilder();
}
