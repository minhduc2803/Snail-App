// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: fintech.proto

package vn.zalopay.ducnm8.grpc;

/**
 * Protobuf enum {@code fintech.Code}
 */
public enum Code
    implements com.google.protobuf.ProtocolMessageEnum {
  /**
   * <code>SUCCESS = 0;</code>
   */
  SUCCESS(0),
  /**
   * <code>INCORRECT_PASSWORD = 1;</code>
   */
  INCORRECT_PASSWORD(1),
  /**
   * <code>UNAUTHORIZED = 2;</code>
   */
  UNAUTHORIZED(2),
  /**
   * <code>NOT_ENOUGH_MONEY = 3;</code>
   */
  NOT_ENOUGH_MONEY(3),
  /**
   * <code>INTERNAL_SERVER_ERROR = 4;</code>
   */
  INTERNAL_SERVER_ERROR(4),
  UNRECOGNIZED(-1),
  ;

  /**
   * <code>SUCCESS = 0;</code>
   */
  public static final int SUCCESS_VALUE = 0;
  /**
   * <code>INCORRECT_PASSWORD = 1;</code>
   */
  public static final int INCORRECT_PASSWORD_VALUE = 1;
  /**
   * <code>UNAUTHORIZED = 2;</code>
   */
  public static final int UNAUTHORIZED_VALUE = 2;
  /**
   * <code>NOT_ENOUGH_MONEY = 3;</code>
   */
  public static final int NOT_ENOUGH_MONEY_VALUE = 3;
  /**
   * <code>INTERNAL_SERVER_ERROR = 4;</code>
   */
  public static final int INTERNAL_SERVER_ERROR_VALUE = 4;


  public final int getNumber() {
    if (this == UNRECOGNIZED) {
      throw new java.lang.IllegalArgumentException(
          "Can't get the number of an unknown enum value.");
    }
    return value;
  }

  /**
   * @deprecated Use {@link #forNumber(int)} instead.
   */
  @java.lang.Deprecated
  public static Code valueOf(int value) {
    return forNumber(value);
  }

  public static Code forNumber(int value) {
    switch (value) {
      case 0: return SUCCESS;
      case 1: return INCORRECT_PASSWORD;
      case 2: return UNAUTHORIZED;
      case 3: return NOT_ENOUGH_MONEY;
      case 4: return INTERNAL_SERVER_ERROR;
      default: return null;
    }
  }

  public static com.google.protobuf.Internal.EnumLiteMap<Code>
      internalGetValueMap() {
    return internalValueMap;
  }
  private static final com.google.protobuf.Internal.EnumLiteMap<
      Code> internalValueMap =
        new com.google.protobuf.Internal.EnumLiteMap<Code>() {
          public Code findValueByNumber(int number) {
            return Code.forNumber(number);
          }
        };

  public final com.google.protobuf.Descriptors.EnumValueDescriptor
      getValueDescriptor() {
    return getDescriptor().getValues().get(ordinal());
  }
  public final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptorForType() {
    return getDescriptor();
  }
  public static final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptor() {
    return vn.zalopay.ducnm8.grpc.FinTechProto.getDescriptor().getEnumTypes().get(0);
  }

  private static final Code[] VALUES = values();

  public static Code valueOf(
      com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
    if (desc.getType() != getDescriptor()) {
      throw new java.lang.IllegalArgumentException(
        "EnumValueDescriptor is not for this type.");
    }
    if (desc.getIndex() == -1) {
      return UNRECOGNIZED;
    }
    return VALUES[desc.getIndex()];
  }

  private final int value;

  private Code(int value) {
    this.value = value;
  }

  // @@protoc_insertion_point(enum_scope:fintech.Code)
}

