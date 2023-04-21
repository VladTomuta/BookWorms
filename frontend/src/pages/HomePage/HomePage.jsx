import React from 'react'
import './HomePage.css'
import ImageSlider from '../../components/ImageSlider/ImageSlider';
import PageDescription from '../../components/PageDescription/PageDescription';
import AboutUs from '../../components/AboutUs/AboutUs'
import ContactUs from '../../components/ContactUs/ContactUs';


function HomePage() {
  return (
    <div className='home-page-container'>
      <ImageSlider/>  
      <PageDescription/>
      <AboutUs />
      <ContactUs/>
    </div>


  )
}

export default HomePage