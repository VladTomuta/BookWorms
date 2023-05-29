import React, {useState} from 'react'
import './ImageSlider.css'
import i1 from "./images/image1.jpg"
import i2 from "./images/image2.jpg"
import i3 from "./images/image3.jpg"
import {FaArrowAltCircleRight,FaArrowAltCircleLeft} from 'react-icons/fa';
import YourSvg from "../../assets/logo_bookworms.png";
import HorizontalMenu from '../../components/HorizontalMenu/HorizontalMenu';
import { useNavigate } from 'react-router-dom';


function ImageSlider() {

    const [currentIndex,setCurrentIndex] = useState(0)

    const images = [
      {image: i1,
      alt: 'image1'
    },
      {image: i2,
      alt: 'image2'
    },
      {image: i3,
      alt: 'image3'
    },

    ]

    const right_button_slide = () =>{
        if(currentIndex<2){
          setCurrentIndex(currentIndex+1);
        }
        else{
          setCurrentIndex(0);
        }

    }
    const left_button_slide = () =>{    
      if(currentIndex>0){
        setCurrentIndex(currentIndex-1);
      }        else{
        setCurrentIndex(2);
      }
    }

    const activeStyle = {
      width:"20px", 
      height:"20px", 
      borderRadius:"50%",
      backgroundColor:"white",
      border:"2px solid white",
      margin:"0px 10px"
    };

    const inactiveStyle = {
      width:"20px", 
      height:"20px", 
      borderRadius:"50%",
      backgroundColor:"transparent",
      border:"2px solid white",
      margin:"0px 10px"
    };

    const navigate = useNavigate();

    const navigateToHomePage = () =>{
      window.location.reload(false);
    }
    
    return (
    <>
      <div className='upper-part'>
          <img id="logo-on-homepage" src={YourSvg} alt="Your SVG" onClick={navigateToHomePage}/>
          <div className='horizontalMenu-container'>
            <HorizontalMenu/>
          </div>
      </div>
      <div className='slide-container' style={{backgroundImage:`url(${images[currentIndex].image})`}}>
        <div className='lower-part'>
          <FaArrowAltCircleLeft className="left-arrow" onClick={left_button_slide} />
          <div className='bullets_container'>
            <div style={{...(currentIndex===0 ? activeStyle : inactiveStyle), cursor:"pointer" }} onClick={() => setCurrentIndex(0)}></div>
            <div style={{...(currentIndex===1 ? activeStyle : inactiveStyle), cursor:"pointer" }} onClick={() => setCurrentIndex(1)}></div>
            <div style={{...(currentIndex===2 ? activeStyle : inactiveStyle), cursor:"pointer" }} onClick={() => setCurrentIndex(2)}></div>
          </div>
          <FaArrowAltCircleRight className="right-arrow" onClick={right_button_slide} />
        </div>
      </div>
    </>
    
  )
}

export default ImageSlider