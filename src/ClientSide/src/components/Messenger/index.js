import React from 'react';
import ConversationList from '../ConversationList';
import MessageList from '../MessageList';
import './Messenger.css';
import Toolbar from '../Toolbar';
import ToolbarButton from '../ToolbarButton'
import axios from 'axios';

export default class Messenger extends React.Component {
  constructor(props){
    super(props);
    
    
    this.state = {
      user: props.user,
      conversationList: [],
      chosen: 0,
      key: ""
    }
  }

  componentDidMount(){
    console.log(this.props.user);

    var conversationList;
  
    axios({
      method: 'get',
      url: 'http://localhost:8055/api/protected/list-user',
      headers: {Authorization: 'Bearer '+this.state.user.Token}
    }).then(response => {
        
        conversationList = response.data.data.map(result => {
         
          return {
            photo: '#',
            name: `${result.Fullname}`,
            text: '',
            Token: this.state.user.Token,
            UserID: result.UserID,
            ChatList: []
          };
        });
        
        
        return conversationList;
    }).then(response => {
        

        const promisesArray = response.map((user, index) => {
           
            return axios({
              method: 'post',
              url: 'http://localhost:8055/api/protected/list-chat',
              data: {UserReceiveID: conversationList[index].UserID},
              headers: {'Authorization': 'Bearer '+this.state.user.Token},

            }).then(ChatList => {
                conversationList[index].ChatList = ChatList.data.data;
            })

        });

        //Wait to all promises
        Promise.all(promisesArray).then(() => {
          console.log("truoc");
            console.log(this.state.conversationList);

            this.setState({
              conversationList: conversationList,
              key: "change",
              chosen: conversationList[conversationList.length-1].UserID
            });

            console.log(this.state.conversationList)
            console.log("sau");
        });
        
    });

    var ws = new WebSocket("ws://localhost:9009/chat?token="+this.props.user.token);
    ws.onopen = () => {
      console.log("websocket connected");
    }

    ws.onmessage = (chat) => {
      console.log(chat);
        var i;

        let conversationListCopy = this.state.conversationList;
        for(i=0;i<conversationListCopy.length;i++){
            if(chat.UserSendID === conversationListCopy[i].UserID){
                conversationListCopy[i].ChatList.push(chat);
                break;
            }
        }
        this.setState({
          conversationList: conversationListCopy,
        });

    }

    
  }

  chosen(UserID){
    this.setState({
      chosen: UserID,
    })
  }
  render(){
        return (
          <div className="messenger">
            { <Toolbar
              title="Messenger"
              leftItems={[
                <ToolbarButton key="cog" icon="ion-ios-cog" />
              ]}
              rightItems={[
                <ToolbarButton key="add" icon="ion-ios-add-circle-outline" />
              ]}
            /> }

            { <Toolbar
              title="Conversation Title"
              rightItems={[
                <ToolbarButton key="info" icon="ion-ios-information-circle-outline" />,
                <ToolbarButton key="video" icon="ion-ios-videocam" />,
                <ToolbarButton key="phone" icon="ion-ios-call" />
              ]}
            /> }

            <div className="scrollable sidebar">
              <ConversationList conversationList={this.state.conversationList}
                                key={this.state.chosen}
                                chosen={(UserID) => this.chosen.bind(this)}/>
            </div>

            <div className="scrollable content">
              <MessageList user={this.state.user}
                          conversationList={this.state.conversationList}
                          key={this.state.chosen}/>
            </div>
          </div>
        );
      }
}