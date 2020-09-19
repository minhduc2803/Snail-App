# Chat App

## Redis

| Key | Value | Description |
|-----|-------|-------------|
| chatapp:user:{UserID}:  | Password | get password |
| chatapp:user:{UserID}: info | Username, Fullname, Password, Online, AvatarFilePath, BirthDay, Sex | get user's info |
| chatapp:user:{Username}: UserID | UserID | get UserId from Username |
| chatapp:ConversationMember:{MemberID}:ConversationID | ConversationID1, ConversationID2,... | get conversation list of a user |
| chatapp:ConversationMember:{ConversationID, MemberID}: NotSeenChat | NotSeenChat | get number of not seen chat from a conversation of a user |
| chatapp:ConversationMember:{ConversationID}:MemberID | MemberID1, MemberID2,... | get all member of a conversation |
| chatapp:chat:{ConversationID}:ChatID | ChatID1, ChatID2,... | get chat list of a conversation |
| chatapp:chat:{ChatID}: info | ConversationID, Type, UserSendID, Content | get a chat information |
| chatapp:friend:{UserID}| FriendID1, FriendID2,... | get friend list of a user |
| chatapp:FriendRequest:{UserYouID}:UserMeID | UserMeID1, UserMeId2,... | get all friend requests from a user |
| chatapp:FriendRequest:{UserMeID}:UserYouID | UserYouID1, UserYouId2,... | get all friend requests of a user |
