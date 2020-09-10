import React from 'react';
import Messenger from '../Messenger';
import RegisterForm from '../Register';
import LoginForm from '../Login';


class App extends React.Component {
  
  constructor(props) {
    super(props)
    // the initial application state
    this.state = {
      user: null,
      isRegister: false,
    }
  }
  
  // App "actions" (functions that modify state)
  signIn(UserId, Username,Fullname, Token) {
    // This is where you would call Firebase, an API etc...
    // calling setState will re-render the entire app (efficiently!)
    this.setState({
      user: {
        UserID: UserId,
        Username: Username,
        Fullname: Fullname,
        Token: Token,
      },
      
      isRegister: false,
    })
  }

  onRegister(){
    this.setState({
      isRegister: false
    });
  }
  
  signOut() {
    // clear out user from state
    this.setState({user: null, isRegister: false})
  }

  changeForm(){
    this.setState({
      isRegister: !this.state.isRegister
    })
  }
  
  render() {
   
    return (
      <div>
        { (this.state.isRegister) ? 
          <RegisterForm 
          onRegister={this.onRegister.bind(this)}
          changeForm={this.changeForm.bind(this)}
          />
          :
          (this.state.user) ? 
            <Messenger user={this.state.user}
                        signOut={this.signOut.bind(this)}/>
            :
            <LoginForm 
             onSignIn={this.signIn.bind(this)} 
             changeForm={this.changeForm.bind(this)}
            />
        }
      </div>
    )
    
  }
  
}

export default App;
