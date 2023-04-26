import React,{useEffect, useState} from 'react'
import { useNavigate } from 'react-router-dom';
import axios from "axios";
import YourSvg from "../../assets/logo_bookworms.svg";
import "./LogInPage.css"

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

    const [email,setEmail] = useState("");
    const [password,setPassword] = useState("");

    /*
    const [users, setUsers] = useState([]);

    useEffect(() => {
      loadUsers();
    }, []);
    */

    // const loadUsers = async () => {
    //   const result = await axios.get("http://127.0.0.1:8080/users/getAllUsers")
    //   console.log(result.data);
    // }


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
      await axios.post("http://127.0.0.1:8080/users/login", {
        email: email,
        password: password,
        }).then((res) =>
        {
          console.log(res.data);
        
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
      }, fail => {
        console.error(fail); // Error!
         });
    }
      catch (err) {
      alert(err);
    }
  }

  return (
    <div className='logInFormContainer'>
      <div className='logoContainer'>
        <a href='#' onClick={navigateToHomePage}><img id="logo" src={YourSvg} alt="Your SVG" /></a>
      </div>
      <div className='FormContainer'>
        <form className='logInForm'>
          <span className='logintext'>Log In</span>
          <input id='logInInput' type="text" placeholder='email or phone number' onChange={setUsernameOnChange}></input>
          <input id='logInInput' type="password" placeholder='password' onChange={setPasswordOnChange}></input>
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

