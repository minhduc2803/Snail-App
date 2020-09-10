import React from 'react';


export default class LoginForm extends React.Component {
  
    async loginAsync(Username, Password){
      let content = {
        UserID: 0,
        Username: "",
        Fullname: "",
      }
      try{
        let rawReponse = await fetch('http://localhost:8055/api/public/login',{
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          Username: Username,
          Password: Password
        })
  
      });
      content = await rawReponse.json();
    }catch(e){
      console.log(e);
    }
      return content;
    }
  
    handleSignIn(e) {
      e.preventDefault()
      let Username = this.refs.Username.value
      let Password = this.refs.Password.value
  
      
     this.loginAsync(Username, Password)
      .then( content => {
        if(content){
          if(content.status === 200){
              this.props.onSignIn(content.data.UserID, Username, content.data.Fullname, content.data.token);
              console.log(content);
          }else
              alert(content.message);
        }else{
            alert("Backend server is not working");
        }
      });
          
     
    }
    
    render() {
      return (
        <div className="container">
  
          <div className="row justify-content-center">
          <div className="col-md-6">
          <div className="card">
          <header className="card-header">
            <button  className="float-right btn btn-outline-primary mt-1" onClick={() => this.props.changeForm()} >Register</button>
            <h4 className="card-title mt-2">Login</h4>
          </header>
          <article className="card-body">
  
          <form onSubmit={this.handleSignIn.bind(this)}>
  
          <div className="form-group">
                  <label>Username</label>
                  <input ref="Username" type="text" className="form-control" placeholder="Enter Username"/>
                </div>

                <div className="form-group">
                  <label>Password</label>
                  <input ref="Password" type="password" className="form-control" placeholder="Enter Your Password"/>
                </div>
                
                <button type="submit" className="btn btn-primary" value="Login">Submit</button>
          </form>
          </article>
          </div>
          </div>
          </div>
        </div>
      )
    }
  
  }