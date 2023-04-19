import React from 'react'
import './HomePage.css'
import ImageSlider from '../../components/ImageSlider/ImageSlider';

function HomePage() {
  return (
    <div className='home_page_container'>
      <ImageSlider/>  
      <div>
        <span>ABOUT US</span>
        
      </div>
    </div>


  )
}

export default HomePage