import React from 'react';


// const Welcome = ({user, onSignOut})=> {
//   // This is a dumb "stateless" component
//   return (
//     <div>
//       Welcome <strong>{user.username}</strong>!
//       <a href="javascript:;" onClick={onSignOut}>Sign out</a>
//     </div>
//   )
// }

export default class RegisterForm extends React.Component {
  
  async registerAsync(Username, Fullname, Password){
    let content = {
      UserID: -1,
      Username: "",
      Fullname: "",
      Password: ""
    }
    try{
      let rawReponse = await fetch('http://localhost:8055/api/public/register',{
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        Username: Username,
        Fullname: Fullname,
        Password: Password
      })

    });
    content = await rawReponse.json();
  }catch(e){
    console.log(e);
  }
    return content;
  }

  handleRegister(e) {
    e.preventDefault()
    let Username = this.refs.Username.value;
    let Fullname = this.refs.Fullname.value;
    let Password = this.refs.Password.value;
    let RePassword = this.refs.RePassword.value;
    if(Password === RePassword){
    
        this.registerAsync(Username, Password, Fullname)
          .then( content => {
            if(content != null){
              if(content.status === 200){
                alert("Register successful, click OK to Login");
                  this.props.onRegister();
                  
              }else
                  alert("Username is not available");
            }else{
              alert("Backend server is not working");
            }
          });
    }else{
      alert("Verify Password doesn't match");
    }
        
   
  }
  
  render() {
    return (
      <div className="container">
  
          <div className="row justify-content-center">
          <div className="col-md-6">
          <div className="card">
          <header className="card-header">
            <button  className="float-right btn btn-outline-primary mt-1" onClick={() => this.props.changeForm()} >Log in</button>
            <h4 className="card-title mt-2">Register</h4>
          </header>
          <article className="card-body">
  
          <form onSubmit={this.handleRegister.bind(this)}>
  
                <div className="form-group">
                  <label>Username</label>
                  <input ref="Username" type="text" className="form-control" placeholder="Enter Username"/>
                </div>
  
                <div className="form-group">
                  <label>Fullname</label>
                  <input ref="Fullname" type="text" className="form-control" placeholder="Enter Your Full Name"/>
                </div>

                <div className="form-group">
                  <label>Password</label>
                  <input ref="Password" type="password" className="form-control" placeholder="Enter Your Password"/>
                </div>

                <div className="form-group">
                  <label >Verify Password</label>
                  <input ref="RePassword" type="password" className="form-control" placeholder="Re-type Your Password"/>
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
