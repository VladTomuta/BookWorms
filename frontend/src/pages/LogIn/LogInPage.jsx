import React,{useState} from 'react'
import { useNavigate } from 'react-router-dom';
import axios from "axios";
import YourSvg from "../../assets/logo_bookworms.svg";
import "./LogInPage.css"

function LogInPage() {
  
  const [email,setEmail] = useState("");
  const [password,setPassword] = useState("");
  const navigate = useNavigate();

  const setEmailOnChange = (e) =>{
      setEmail(e.target.value);
  }
 
  const setPasswordOnChange = (e) =>{
      setPassword(e.target.value);
  }
    
  const LogInViaFacebook = (e) =>{
    return true
  }

  const LogInViaGoogle = (e) =>{
    return true
  }

  const navigateToHomePage = () => {
    navigate("/");
  }

  const handleOnSubmitButton = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post("http://127.0.0.1:8080/users/login", {
        email: email,
        password: password,
        }).then((res) =>
        {        
          if(res.data.status == true)
          {
            //asta ii tokenul pe care trebuie sa il salvezi local pentru fiecare api in afara de users/signup si users/login
            console.log(res.data.token);
            sessionStorage.setItem('user', JSON.stringify(res.data.user));
            sessionStorage.setItem('user_token', JSON.stringify(res.data.token));

            if(res.data.user.role == "USER") {
              navigate("/loggedIn")
            } else {
              navigate("/admin")
            }
          }
          else
          {
            alert("Incorrect Email and Password");
          }
      }, fail => {
        console.error(fail); // Error!
         });
    }
      catch (err) {
        console.log("DA");
        alert(err);
    }
  }

  return (
    <div className='logInFormContainer'>
      <div className='middle-layer'>
        <div className='logoContainer'>
          <div className='containerOfTheLogo'>
            <a href='#' onClick={navigateToHomePage}><img id="logo" src={YourSvg} alt="Your SVG" /></a>
          </div>
          <span id='page-description-login'>Welcome to our book switching application! With our platform, you can easily share books with others and discover new titles. Whether you're an avid reader or just starting out, our community is here to help you find your next favorite book. Login or sign up today to get started!</span>
        </div>
        <div className='FormContainer'>
          <form className='logInForm'>
            <span className='logintext'>Log In</span>
            <input id='logInInput' type="text" placeholder='email or phone number' onChange={setEmailOnChange}></input>
            <input id='logInInput' type="password" placeholder='password' onChange={setPasswordOnChange}></input>
            <a href='/' className='forgotpasstext'>Forgot password?</a>
            
            <button className='button' onClick={handleOnSubmitButton}>Log In</button>
            <div className='line'/>
            <button className='button' onClick={LogInViaFacebook}>Facebook</button>
            <button className='button' onClick={LogInViaGoogle}>Google</button>
            <span className='notAMemberText'>Not a member? <a href='./signup'>Sign Up</a></span>
          </form>
        </div>
      </div>
    </div>
  )
}
 
export default LogInPage

