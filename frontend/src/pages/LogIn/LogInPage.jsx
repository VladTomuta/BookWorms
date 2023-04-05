import React,{useEffect, useState} from 'react'
import axios from "axios";
import YourSvg from "../../assets/logo_bookworms.svg";
import "./LogInPage.css"

function LogInPage() {
    const [username,setUsername] = useState("");
    const [password,setPassword] = useState("");

    const setUsernameOnChange = (e) =>{
        setUsername(e.target.value);
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

    const handleOnSubmitButton = async (e) => {
		e.preventDefault();
		await axios
			.post("/", {
				username: username,
				password: password,
			})
			.then((resp) => {
				console.log("autentificat cu succes");
			})
			.catch((err) => {
				console.log("ceva nu a functionat corect");
			});
	};

  return (
    <div className='logInFormContainer'>
        <a href=''><img id="logo" src={YourSvg} alt="Your SVG" /></a>
        <div className='formBackground'>
          <form className='logInForm'>
            <span className='logintext'>SignIn</span>
            <input type="text" placeholder='email or phone number' onChange={setUsernameOnChange}></input>
            <input type="password" placeholder='password' onChange={setPasswordOnChange}></input>
            <a href='/' className='forgotpasstext'>Forgot password?</a>
            
            <button className='button' onClick={handleOnSubmitButton}>Log In</button>
            <div className='line'></div>
            <button className='button' onClick={LogInViaFacebook}>Facebook</button>
            <button className='button' onClick={LogInViaGoogle}>Google</button>
            <span className='notAMemberText'>Not a member? <a href='./signup'>Sign Up</a></span>
          </form>
        </div>
    </div>
  )
}
 
export default LogInPage