import React from 'react'
import './AboutUs.css'
import logo from "../../assets/logo_bookworms.svg";
import iulian from "../../assets/iulian.jpg";
import vladut from "../../assets/vladut.jpg";
function AboutUs() {
  return (
    <div className='about-us-container'>
        <div id='aboutUs-title'>About Us</div>
        <div className='aboutUs-logo-container'>
            <img id='logo' alt='logo' src={logo}></img>
            <div className='description'>
                <span id='logo-text'>BookWorm was born in the basement of Vladut's momma's house, where a group of avid readers came together to share their passion for books. Vladut, a computer science student, had an idea to create an app that would make it easy for people to share books with each other, without the need to buy them.</span>
                <span id='logo-text'>Together with his friends, Vladut spent long hours developing the app, designing the interface, and testing the functionality. They knew they were onto something special when they started receiving requests from friends of friends, who wanted to use the app to rent books from their own collections.</span>
                <span id='logo-text'>As word spread, BookWorm quickly gained popularity in their community, and Vladut and his friends realized they had created something that could benefit readers everywhere. They worked hard to refine the app, adding new features and improving the user experience.</span>
            </div>
        </div>
        <div className='separator'></div>
        <div className='aboutUs-vlad-container'>
        <div className='vlad-description'>
                <span id='logo-text'>Vladut is the creative mind behind BookWorm. He is a passionate computer science student who is dedicated to making a difference in the world through technology. From a young age, Vladut has always been interested in programming and has spent countless hours honing his skills in coding.</span>
                <span id='logo-text'>Vladut is a natural problem-solver and thrives on finding innovative solutions to complex challenges. He is always seeking to learn new things and is constantly pushing the boundaries of what is possible.</span>
                <span id='logo-text'>Despite his many achievements, Vladut remains humble and grounded. He is committed to making a positive impact in the world and is always looking for ways to give back to his community. With his passion for technology and his love of reading, Vladut is truly a force to be reckoned with.</span>
            </div>
            <div className='vladut'>
            <img alt='vladutPhoto' id='vladutPhoto' src={vladut}></img>
            <span id='numeleLuiVlad'>Tomuta Vlad</span>
            </div>
        </div>
        <div className='separator'></div>
        <div className='aboutUs-iulian-container'>
        <div className='iulian'>
        <img id='iulianPhoto' alt='iulianPhoto' src={iulian}></img>
            <span id='numeleLuiVlad'>Toderesc Iulian</span>
        </div>
            <div className='description'>
                <span id='logo-text'>BookWorm was born in the basement of Vladut's momma's house, where a group of avid readers came together to share their passion for books. Vladut, a computer science student, had an idea to create an app that would make it easy for people to share books with each other, without the need to buy them.</span>
                <span id='logo-text'>Together with his friends, Vladut spent long hours developing the app, designing the interface, and testing the functionality. They knew they were onto something special when they started receiving requests from friends of friends, who wanted to use the app to rent books from their own collections.</span>
                <span id='logo-text'>As word spread, BookWorm quickly gained popularity in their community, and Vladut and his friends realized they had created something that could benefit readers everywhere. They worked hard to refine the app, adding new features and improving the user experience.</span>
            </div>
        </div>

    </div>
  )
}

export default AboutUs