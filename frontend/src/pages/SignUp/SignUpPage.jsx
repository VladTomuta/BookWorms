import React,{useEffect, useState} from 'react'
import { useNavigate } from 'react-router-dom';
import axios from "axios";
import YourSvg from "../../assets/logo_bookworms.svg";
import "./SignUpPage.css"

function LogInPage() {

    const [user, setUser] = useState(
      {
        email:"",
        fullName:"",
        username:"",
        password:"",
        phoneNumber:"",
        region:""
      }
    );

    const{email,fullName,username,password,phoneNumber,region}=user

    const onInputChange=(e)=>{
        setUser({...user,[e.target.name]:e.target.value})
    }

    const navigate = useNavigate();
    
    const LogInViaFacebook = (e) =>{
      return true
    }

    const LogInViaGoogle = (e) =>{
      return true
    }

    /*
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
  */

  const handleOnSubmitButton = async (e) => {
    e.preventDefault()
    console.log(user)
    try {
      await axios.post("http://127.0.0.1:8080/users/signup",user)
      .then((res) =>
        {
          console.log(res.data);

          if(res.data.status == true) {
            navigate("/");
          } else {
            alert(res.data.message);
          }
        
          /*
          if(res.data.status == true)
          {
            console.log(res.data.user.role);

            if(res.data.user.role == "USER") {
              navigate("/home")
            } else {
              navigate("/admin")
            }
              
          }
          else
          {
            alert("Incorrect Email and Password");
          }
          */
      }, fail => {
        console.error(fail); // Error!
         });
    }
      catch (err) {
      alert(err);
    }
  }

  const navigateToHomePage = () => {
    navigate("/");
  }

  return (
    <div className='SignUpPageContainer'>
      <div className='middle-layer-signup'>
        <div className='SignUpPage-logoContainer'>
          <div className='logoInsideLogoContainer'>
          <img onClick={navigateToHomePage} id="SignUpPagelogo" src={YourSvg} alt="Your SVG" />
          </div>
          <span id='page-description-signup'>Welcome to our book switching application! With our platform, you can easily share books with others and discover new titles. Whether you're an avid reader or just starting out, our community is here to help you find your next favorite book. Login or sign up today to get started!</span>
        </div>
        <div className='signUpFormContainer'>
          <form className='signUpForm'>
            <span className='signUpText'>Sign Up</span>
            <input id='signUpInput' type="text" placeholder='email' name='email' value={email} onChange={(e)=>onInputChange(e)}></input>
            <input id='signUpInput' type="text" placeholder='full name' name='fullName' value={fullName} onChange={(e)=>onInputChange(e)}></input>
            <input id='signUpInput' type="text" placeholder='username' name='username' value={username} onChange={(e)=>onInputChange(e)}></input>
            <input id='signUpInput' type="password" placeholder='password' name='password' value={password} onChange={(e)=>onInputChange(e)}></input>
            <input id='signUpInput' type="text" placeholder='phone number' name='phoneNumber' value={phoneNumber} onChange={(e)=>onInputChange(e)}></input>
            <input id='signUpInput' type="text" placeholder='region' name='region' value={region} onChange={(e)=>onInputChange(e)}></input>
            <a href='/' className='signUpForgotPassText'>Forgot password?</a>
            
            <button className='signUpButton' onClick={handleOnSubmitButton}>Sign Up</button>
            <div className='line'></div>
            <button className='LogInViaFacebookButton' onClick={LogInViaFacebook}>Facebook</button>
            <button className='LogInViaGoogleButton' onClick={LogInViaGoogle}>Google</button>
            <span className='SignUpAlreadyAMember'>Already a member? <a href='./login'>Log In</a></span>
          </form>
        </div>
      </div>
    </div>
  )
}
 
export default LogInPage