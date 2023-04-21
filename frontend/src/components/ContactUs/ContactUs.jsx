import React from 'react'
import ContactUsForm from './ContactUsForm/ContactUsForm'
import logo from "../../assets/logo_bookworms.svg";
import ContactUsFAQ from "./ContactUsFAQ/ContactUsFAQ"
import './ContactUs.css'


function ContactUs() {
  return (
    <div className='contactUs-container'>
        <div className='contactUs-form'>
            <ContactUsForm/>
        </div>
        <div className='contactUs-info'>
            <img src={logo} alt='logo' id='contactUs-logo'/>
            <span id='contactUs-info-description'>Together with his friends, Vladut spent long hours developing the app, designing the interface, and testing the functionality. They knew they were onto something special when they started receiving requests from friends of friends, who wanted to use the app to rent books from their own collections.</span>
        </div>
        <div className='contactUs-FAQ'>
            <ContactUsFAQ/>
        </div>
    </div>
  )
}

export default ContactUs