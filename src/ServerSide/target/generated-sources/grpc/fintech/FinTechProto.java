// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: fintech.proto

package fintech;

public final class FinTechProto {
  private FinTechProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_fintech_Error_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_fintech_Error_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_fintech_Error_ExtraEntry_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_fintech_Error_ExtraEntry_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_fintech_BalanceRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_fintech_BalanceRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_fintech_BalanceResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_fintech_BalanceResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_fintech_BalanceResponse_Data_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_fintech_BalanceResponse_Data_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_fintech_HistoryRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_fintech_HistoryRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_fintech_HistoryResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_fintech_HistoryResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_fintech_HistoryResponse_Data_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_fintech_HistoryResponse_Data_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_fintech_HistoryItem_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_fintech_HistoryItem_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_fintech_TransferRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_fintech_TransferRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_fintech_TransferResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_fintech_TransferResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_fintech_TransferResponse_Data_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_fintech_TransferResponse_Data_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_fintech_NotificationRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_fintech_NotificationRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_fintech_NotificationResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_fintech_NotificationResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_fintech_NotificationResponse_Data_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_fintech_NotificationResponse_Data_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_fintech_NotificationItem_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_fintech_NotificationItem_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\rfintech.proto\022\007fintech\"\215\001\n\005Error\022\033\n\004co" +
      "de\030\001 \001(\0162\r.fintech.Code\022\017\n\007message\030\002 \001(\t" +
      "\022(\n\005extra\030\003 \003(\0132\031.fintech.Error.ExtraEnt" +
      "ry\032,\n\nExtraEntry\022\013\n\003key\030\001 \001(\t\022\r\n\005value\030\002" +
      " \001(\t:\0028\001\"\020\n\016BalanceRequest\"\230\001\n\017BalanceRe" +
      "sponse\022\035\n\005error\030\001 \001(\0132\016.fintech.Error\022+\n" +
      "\004data\030\002 \001(\0132\035.fintech.BalanceResponse.Da" +
      "ta\0329\n\004Data\022\017\n\007balance\030\001 \001(\003\022 \n\030last_time" +
      "_update_balance\030\002 \001(\003\"\020\n\016HistoryRequest\"" +
      "\222\001\n\017HistoryResponse\022\035\n\005error\030\001 \001(\0132\016.fin",
      "tech.Error\022+\n\004data\030\002 \001(\0132\035.fintech.Histo" +
      "ryResponse.Data\0323\n\004Data\022+\n\rhistory_items" +
      "\030\001 \003(\0132\024.fintech.HistoryItem\"\217\002\n\013History" +
      "Item\022\022\n\nhistory_id\030\001 \001(\003\022\022\n\npartner_id\030\002" +
      " \001(\003\022\030\n\020partner_username\030\003 \001(\t\022\030\n\020partne" +
      "r_fullname\030\004 \001(\t\022\016\n\006amount\030\005 \001(\003\022\017\n\007mess" +
      "age\030\006 \001(\t\022\021\n\ttimestamp\030\007 \001(\003\022\017\n\007balance\030" +
      "\010 \001(\003\0228\n\rtransfer_type\030\t \001(\0162!.fintech.H" +
      "istoryItem.TransferType\"%\n\014TransferType\022" +
      "\010\n\004SEND\020\000\022\013\n\007RECEIVE\020\001\"Z\n\017TransferReques",
      "t\022\021\n\tsender_id\030\001 \001(\003\022\023\n\013receiver_id\030\002 \001(" +
      "\003\022\016\n\006amount\030\003 \001(\003\022\017\n\007message\030\004 \001(\t\"\317\001\n\020T" +
      "ransferResponse\022\035\n\005error\030\001 \001(\0132\016.fintech" +
      ".Error\022,\n\004data\030\002 \001(\0132\036.fintech.TransferR" +
      "esponse.Data\032n\n\004Data\022A\n\014isSuccessful\030\001 \001" +
      "(\0162+.fintech.TransferResponse.Data.IsSuc" +
      "cessful\"#\n\014IsSuccessful\022\010\n\004TRUE\020\000\022\t\n\005FAL" +
      "SE\020\001\"\025\n\023NotificationRequest\"\246\001\n\024Notifica" +
      "tionResponse\022\035\n\005error\030\001 \001(\0132\016.fintech.Er" +
      "ror\0220\n\004data\030\002 \001(\0132\".fintech.Notification",
      "Response.Data\032=\n\004Data\0225\n\022notification_it" +
      "ems\030\001 \003(\0132\031.fintech.NotificationItem\"\277\001\n" +
      "\020NotificationItem\022\027\n\017notification_id\030\001 \001" +
      "(\003\022\014\n\004mode\030\002 \001(\005\022\022\n\npartner_id\030\003 \001(\003\022\016\n\006" +
      "amount\030\004 \001(\003\022\017\n\007message\030\005 \001(\t\0220\n\006unread\030" +
      "\006 \001(\0162 .fintech.NotificationItem.UnRead\"" +
      "\035\n\006UnRead\022\010\n\004TRUE\020\000\022\t\n\005FALSE\020\001*n\n\004Code\022\013" +
      "\n\007SUCCESS\020\000\022\026\n\022INCORRECT_PASSWORD\020\001\022\020\n\014U" +
      "NAUTHORIZED\020\002\022\024\n\020NOT_ENOUGH_MONEY\020\003\022\031\n\025I" +
      "NTERNAL_SERVER_ERROR\020\0042\253\002\n\016FintechServic",
      "e\022A\n\ngetBalance\022\027.fintech.BalanceRequest" +
      "\032\030.fintech.BalanceResponse\"\000\022A\n\ngetHisto" +
      "ry\022\027.fintech.HistoryRequest\032\030.fintech.Hi" +
      "storyResponse\"\000\022A\n\010transfer\022\030.fintech.Tr" +
      "ansferRequest\032\031.fintech.TransferResponse" +
      "\"\000\022P\n\017getNotification\022\034.fintech.Notifica" +
      "tionRequest\032\035.fintech.NotificationRespon" +
      "se\"\000B\031\n\007fintechB\014FinTechProtoP\001b\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_fintech_Error_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_fintech_Error_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_fintech_Error_descriptor,
        new java.lang.String[] { "Code", "Message", "Extra", });
    internal_static_fintech_Error_ExtraEntry_descriptor =
      internal_static_fintech_Error_descriptor.getNestedTypes().get(0);
    internal_static_fintech_Error_ExtraEntry_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_fintech_Error_ExtraEntry_descriptor,
        new java.lang.String[] { "Key", "Value", });
    internal_static_fintech_BalanceRequest_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_fintech_BalanceRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_fintech_BalanceRequest_descriptor,
        new java.lang.String[] { });
    internal_static_fintech_BalanceResponse_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_fintech_BalanceResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_fintech_BalanceResponse_descriptor,
        new java.lang.String[] { "Error", "Data", });
    internal_static_fintech_BalanceResponse_Data_descriptor =
      internal_static_fintech_BalanceResponse_descriptor.getNestedTypes().get(0);
    internal_static_fintech_BalanceResponse_Data_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_fintech_BalanceResponse_Data_descriptor,
        new java.lang.String[] { "Balance", "LastTimeUpdateBalance", });
    internal_static_fintech_HistoryRequest_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_fintech_HistoryRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_fintech_HistoryRequest_descriptor,
        new java.lang.String[] { });
    internal_static_fintech_HistoryResponse_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_fintech_HistoryResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_fintech_HistoryResponse_descriptor,
        new java.lang.String[] { "Error", "Data", });
    internal_static_fintech_HistoryResponse_Data_descriptor =
      internal_static_fintech_HistoryResponse_descriptor.getNestedTypes().get(0);
    internal_static_fintech_HistoryResponse_Data_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_fintech_HistoryResponse_Data_descriptor,
        new java.lang.String[] { "HistoryItems", });
    internal_static_fintech_HistoryItem_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_fintech_HistoryItem_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_fintech_HistoryItem_descriptor,
        new java.lang.String[] { "HistoryId", "PartnerId", "PartnerUsername", "PartnerFullname", "Amount", "Message", "Timestamp", "Balance", "TransferType", });
    internal_static_fintech_TransferRequest_descriptor =
      getDescriptor().getMessageTypes().get(6);
    internal_static_fintech_TransferRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_fintech_TransferRequest_descriptor,
        new java.lang.String[] { "SenderId", "ReceiverId", "Amount", "Message", });
    internal_static_fintech_TransferResponse_descriptor =
      getDescriptor().getMessageTypes().get(7);
    internal_static_fintech_TransferResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_fintech_TransferResponse_descriptor,
        new java.lang.String[] { "Error", "Data", });
    internal_static_fintech_TransferResponse_Data_descriptor =
      internal_static_fintech_TransferResponse_descriptor.getNestedTypes().get(0);
    internal_static_fintech_TransferResponse_Data_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_fintech_TransferResponse_Data_descriptor,
        new java.lang.String[] { "IsSuccessful", });
    internal_static_fintech_NotificationRequest_descriptor =
      getDescriptor().getMessageTypes().get(8);
    internal_static_fintech_NotificationRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_fintech_NotificationRequest_descriptor,
        new java.lang.String[] { });
    internal_static_fintech_NotificationResponse_descriptor =
      getDescriptor().getMessageTypes().get(9);
    internal_static_fintech_NotificationResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_fintech_NotificationResponse_descriptor,
        new java.lang.String[] { "Error", "Data", });
    internal_static_fintech_NotificationResponse_Data_descriptor =
      internal_static_fintech_NotificationResponse_descriptor.getNestedTypes().get(0);
    internal_static_fintech_NotificationResponse_Data_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_fintech_NotificationResponse_Data_descriptor,
        new java.lang.String[] { "NotificationItems", });
    internal_static_fintech_NotificationItem_descriptor =
      getDescriptor().getMessageTypes().get(10);
    internal_static_fintech_NotificationItem_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_fintech_NotificationItem_descriptor,
        new java.lang.String[] { "NotificationId", "Mode", "PartnerId", "Amount", "Message", "Unread", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
