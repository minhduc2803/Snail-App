// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: fintech.proto

package vn.zalopay.ducnm8.grpc;

public interface ErrorOrBuilder extends
    // @@protoc_insertion_point(interface_extends:fintech.Error)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * code = 0 means success
   * </pre>
   *
   * <code>.fintech.Code code = 1;</code>
   */
  int getCodeValue();
  /**
   * <pre>
   * code = 0 means success
   * </pre>
   *
   * <code>.fintech.Code code = 1;</code>
   */
  vn.zalopay.ducnm8.grpc.Code getCode();

  /**
   * <code>string message = 2;</code>
   */
  java.lang.String getMessage();
  /**
   * <code>string message = 2;</code>
   */
  com.google.protobuf.ByteString
      getMessageBytes();

  /**
   * <code>map&lt;string, string&gt; extra = 3;</code>
   */
  int getExtraCount();
  /**
   * <code>map&lt;string, string&gt; extra = 3;</code>
   */
  boolean containsExtra(
      java.lang.String key);
  /**
   * Use {@link #getExtraMap()} instead.
   */
  @java.lang.Deprecated
  java.util.Map<java.lang.String, java.lang.String>
  getExtra();
  /**
   * <code>map&lt;string, string&gt; extra = 3;</code>
   */
  java.util.Map<java.lang.String, java.lang.String>
  getExtraMap();
  /**
   * <code>map&lt;string, string&gt; extra = 3;</code>
   */

  java.lang.String getExtraOrDefault(
      java.lang.String key,
      java.lang.String defaultValue);
  /**
   * <code>map&lt;string, string&gt; extra = 3;</code>
   */

  java.lang.String getExtraOrThrow(
      java.lang.String key);
}
