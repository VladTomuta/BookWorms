import React,{useState,useEffect} from 'react'
import './ContactUsForm.css'

function ContactUsForm() {
    const [email,setEmail] = useState("");
    const [fullName,setFullName] = useState("");
    const [message,setMessage] = useState("");
    const onSubmitFormButton = (e) =>{
        e.preventDefault();
        console.log(email,fullName,message);
    }

  return (
    <form className='ContactUsForm-container' onSubmit={onSubmitFormButton}>
        <input id='mailInput' type='mail' placeholder='e-mail' onChange={(e)=> setEmail(e.target.value)}/>
        <input id='fullNameInput' type='text' placeholder='full name' onChange={(e)=> setFullName(e.target.value)}/>
        <textarea id='message' rows="5" cols="80" name='message' placeholder='message' spellCheck="false" onChange={(e)=> setMessage(e.target.value)}/>
        <button type='submit' id='submitButton'>Submit</button>
    </form>
  )
}

export default ContactUsForm