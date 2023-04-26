import React from 'react'
import {useNavigate} from 'react-router-dom'
import ContactUsForm from './ContactUsForm/ContactUsForm'
import logo from "../../assets/logo_bookworms.svg";
import ContactUsFAQ from "./ContactUsFAQ/ContactUsFAQ"
import './ContactUs.css'
import { FaArrowAltCircleUp } from 'react-icons/fa';


function ContactUs() {

  const scrollToTop = () =>{
    window.scrollTo({top: 0, left: 0, behavior: 'smooth'});
  }

  return (
    <div className='contactUs-container'>
        <div className='contactUs-form'>
            <ContactUsForm/>
        </div>
        <div className='contactUs-info'>
          <div className='scrollToTop'>
            <img src={logo} onClick={() => {scrollToTop()}} alt='logo' id='contactUs-logo'/>
            <FaArrowAltCircleUp className='ArrowUp'/>
          </div>
            <span id='contactUs-info-description'>Together with his friends, Vladut spent long hours developing the app, designing the interface, and testing the functionality. They knew they were onto something special when they started receiving requests from friends of friends, who wanted to use the app to rent books from their own collections.</span>
        </div>
        <div className='contactUs-FAQ'>
            <ContactUsFAQ/>
        </div>
    </div>
  )
}

export default ContactUs