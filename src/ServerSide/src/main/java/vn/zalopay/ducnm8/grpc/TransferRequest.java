// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: fintech.proto

package vn.zalopay.ducnm8.grpc;

/**
 * Protobuf type {@code fintech.TransferRequest}
 */
public  final class TransferRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:fintech.TransferRequest)
    TransferRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use TransferRequest.newBuilder() to construct.
  private TransferRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private TransferRequest() {
    senderId_ = 0L;
    receiverId_ = 0L;
    amount_ = 0L;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private TransferRequest(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!parseUnknownFieldProto3(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
          case 8: {

            senderId_ = input.readInt64();
            break;
          }
          case 16: {

            receiverId_ = input.readInt64();
            break;
          }
          case 24: {

            amount_ = input.readInt64();
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return vn.zalopay.ducnm8.grpc.FinTechProto.internal_static_fintech_TransferRequest_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return vn.zalopay.ducnm8.grpc.FinTechProto.internal_static_fintech_TransferRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            vn.zalopay.ducnm8.grpc.TransferRequest.class, vn.zalopay.ducnm8.grpc.TransferRequest.Builder.class);
  }

  public static final int SENDER_ID_FIELD_NUMBER = 1;
  private long senderId_;
  /**
   * <pre>
   * A transfer request from user
   * Contain password in header for authorization
   * </pre>
   *
   * <code>int64 sender_id = 1;</code>
   */
  public long getSenderId() {
    return senderId_;
  }

  public static final int RECEIVER_ID_FIELD_NUMBER = 2;
  private long receiverId_;
  /**
   * <code>int64 receiver_id = 2;</code>
   */
  public long getReceiverId() {
    return receiverId_;
  }

  public static final int AMOUNT_FIELD_NUMBER = 3;
  private long amount_;
  /**
   * <code>int64 amount = 3;</code>
   */
  public long getAmount() {
    return amount_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (senderId_ != 0L) {
      output.writeInt64(1, senderId_);
    }
    if (receiverId_ != 0L) {
      output.writeInt64(2, receiverId_);
    }
    if (amount_ != 0L) {
      output.writeInt64(3, amount_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (senderId_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, senderId_);
    }
    if (receiverId_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(2, receiverId_);
    }
    if (amount_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(3, amount_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof vn.zalopay.ducnm8.grpc.TransferRequest)) {
      return super.equals(obj);
    }
    vn.zalopay.ducnm8.grpc.TransferRequest other = (vn.zalopay.ducnm8.grpc.TransferRequest) obj;

    boolean result = true;
    result = result && (getSenderId()
        == other.getSenderId());
    result = result && (getReceiverId()
        == other.getReceiverId());
    result = result && (getAmount()
        == other.getAmount());
    result = result && unknownFields.equals(other.unknownFields);
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + SENDER_ID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getSenderId());
    hash = (37 * hash) + RECEIVER_ID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getReceiverId());
    hash = (37 * hash) + AMOUNT_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getAmount());
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static vn.zalopay.ducnm8.grpc.TransferRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static vn.zalopay.ducnm8.grpc.TransferRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static vn.zalopay.ducnm8.grpc.TransferRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static vn.zalopay.ducnm8.grpc.TransferRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static vn.zalopay.ducnm8.grpc.TransferRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static vn.zalopay.ducnm8.grpc.TransferRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static vn.zalopay.ducnm8.grpc.TransferRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static vn.zalopay.ducnm8.grpc.TransferRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static vn.zalopay.ducnm8.grpc.TransferRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static vn.zalopay.ducnm8.grpc.TransferRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static vn.zalopay.ducnm8.grpc.TransferRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static vn.zalopay.ducnm8.grpc.TransferRequest parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(vn.zalopay.ducnm8.grpc.TransferRequest prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code fintech.TransferRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:fintech.TransferRequest)
      vn.zalopay.ducnm8.grpc.TransferRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return vn.zalopay.ducnm8.grpc.FinTechProto.internal_static_fintech_TransferRequest_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return vn.zalopay.ducnm8.grpc.FinTechProto.internal_static_fintech_TransferRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              vn.zalopay.ducnm8.grpc.TransferRequest.class, vn.zalopay.ducnm8.grpc.TransferRequest.Builder.class);
    }

    // Construct using vn.zalopay.ducnm8.grpc.TransferRequest.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    public Builder clear() {
      super.clear();
      senderId_ = 0L;

      receiverId_ = 0L;

      amount_ = 0L;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return vn.zalopay.ducnm8.grpc.FinTechProto.internal_static_fintech_TransferRequest_descriptor;
    }

    public vn.zalopay.ducnm8.grpc.TransferRequest getDefaultInstanceForType() {
      return vn.zalopay.ducnm8.grpc.TransferRequest.getDefaultInstance();
    }

    public vn.zalopay.ducnm8.grpc.TransferRequest build() {
      vn.zalopay.ducnm8.grpc.TransferRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public vn.zalopay.ducnm8.grpc.TransferRequest buildPartial() {
      vn.zalopay.ducnm8.grpc.TransferRequest result = new vn.zalopay.ducnm8.grpc.TransferRequest(this);
      result.senderId_ = senderId_;
      result.receiverId_ = receiverId_;
      result.amount_ = amount_;
      onBuilt();
      return result;
    }

    public Builder clone() {
      return (Builder) super.clone();
    }
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.setField(field, value);
    }
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof vn.zalopay.ducnm8.grpc.TransferRequest) {
        return mergeFrom((vn.zalopay.ducnm8.grpc.TransferRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(vn.zalopay.ducnm8.grpc.TransferRequest other) {
      if (other == vn.zalopay.ducnm8.grpc.TransferRequest.getDefaultInstance()) return this;
      if (other.getSenderId() != 0L) {
        setSenderId(other.getSenderId());
      }
      if (other.getReceiverId() != 0L) {
        setReceiverId(other.getReceiverId());
      }
      if (other.getAmount() != 0L) {
        setAmount(other.getAmount());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      vn.zalopay.ducnm8.grpc.TransferRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (vn.zalopay.ducnm8.grpc.TransferRequest) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private long senderId_ ;
    /**
     * <pre>
     * A transfer request from user
     * Contain password in header for authorization
     * </pre>
     *
     * <code>int64 sender_id = 1;</code>
     */
    public long getSenderId() {
      return senderId_;
    }
    /**
     * <pre>
     * A transfer request from user
     * Contain password in header for authorization
     * </pre>
     *
     * <code>int64 sender_id = 1;</code>
     */
    public Builder setSenderId(long value) {
      
      senderId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * A transfer request from user
     * Contain password in header for authorization
     * </pre>
     *
     * <code>int64 sender_id = 1;</code>
     */
    public Builder clearSenderId() {
      
      senderId_ = 0L;
      onChanged();
      return this;
    }

    private long receiverId_ ;
    /**
     * <code>int64 receiver_id = 2;</code>
     */
    public long getReceiverId() {
      return receiverId_;
    }
    /**
     * <code>int64 receiver_id = 2;</code>
     */
    public Builder setReceiverId(long value) {
      
      receiverId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 receiver_id = 2;</code>
     */
    public Builder clearReceiverId() {
      
      receiverId_ = 0L;
      onChanged();
      return this;
    }

    private long amount_ ;
    /**
     * <code>int64 amount = 3;</code>
     */
    public long getAmount() {
      return amount_;
    }
    /**
     * <code>int64 amount = 3;</code>
     */
    public Builder setAmount(long value) {
      
      amount_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 amount = 3;</code>
     */
    public Builder clearAmount() {
      
      amount_ = 0L;
      onChanged();
      return this;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:fintech.TransferRequest)
  }

  // @@protoc_insertion_point(class_scope:fintech.TransferRequest)
  private static final vn.zalopay.ducnm8.grpc.TransferRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new vn.zalopay.ducnm8.grpc.TransferRequest();
  }

  public static vn.zalopay.ducnm8.grpc.TransferRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<TransferRequest>
      PARSER = new com.google.protobuf.AbstractParser<TransferRequest>() {
    public TransferRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new TransferRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<TransferRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<TransferRequest> getParserForType() {
    return PARSER;
  }

  public vn.zalopay.ducnm8.grpc.TransferRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

