// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: hello.proto

package vn.zalopay.ducnm8.grpc;

public interface HelloRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:helloworld.HelloRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * Each message attribute is strongly typed.
   * You also must assign a "tag" number.
   * Each tag number is unique within the message.
   * </pre>
   *
   * <code>string name = 1;</code>
   */
  java.lang.String getName();
  /**
   * <pre>
   * Each message attribute is strongly typed.
   * You also must assign a "tag" number.
   * Each tag number is unique within the message.
   * </pre>
   *
   * <code>string name = 1;</code>
   */
  com.google.protobuf.ByteString
      getNameBytes();

  /**
   * <pre>
   * This defines a strongly typed list of String
   * </pre>
   *
   * <code>repeated string hobbies = 2;</code>
   */
  java.util.List<java.lang.String>
      getHobbiesList();
  /**
   * <pre>
   * This defines a strongly typed list of String
   * </pre>
   *
   * <code>repeated string hobbies = 2;</code>
   */
  int getHobbiesCount();
  /**
   * <pre>
   * This defines a strongly typed list of String
   * </pre>
   *
   * <code>repeated string hobbies = 2;</code>
   */
  java.lang.String getHobbies(int index);
  /**
   * <pre>
   * This defines a strongly typed list of String
   * </pre>
   *
   * <code>repeated string hobbies = 2;</code>
   */
  com.google.protobuf.ByteString
      getHobbiesBytes(int index);
}
