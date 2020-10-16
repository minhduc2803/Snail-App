// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: fintech.proto

package fintech;

public interface TransferRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:fintech.TransferRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * A transfer request from user
   * Contain password in header for authorization
   * </pre>
   *
   * <code>int64 sender_id = 1;</code>
   */
  long getSenderId();

  /**
   * <code>int64 receiver_id = 2;</code>
   */
  long getReceiverId();

  /**
   * <code>int64 amount = 3;</code>
   */
  long getAmount();

  /**
   * <code>string message = 4;</code>
   */
  java.lang.String getMessage();
  /**
   * <code>string message = 4;</code>
   */
  com.google.protobuf.ByteString
      getMessageBytes();

  /**
   * <code>string password = 5;</code>
   */
  java.lang.String getPassword();
  /**
   * <code>string password = 5;</code>
   */
  com.google.protobuf.ByteString
      getPasswordBytes();
}
