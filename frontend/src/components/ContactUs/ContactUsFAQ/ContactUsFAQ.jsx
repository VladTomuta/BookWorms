import React,{useState,useEffect} from 'react'
import "./ContactUsFAQ.css"

function ContactUsFAQ() {

  const [FAQ,setFAQ] = useState([{
    idkId:1,
    question:"How long can i borrow a book?",
    answer:"For as long as you and the owner think it fits"
  },{
    idkId:2,
    question:"Another question i can't think about?Another question i can't think about? Another question i can't think about? ",
    answer:"Until this project is finished"
  }])

  return (
  <div className='FAQ-container'>
    <u><span id='FAQ-title'>Frequently asked questions!</span></u>
    {FAQ.map((element) => 
    <div key={element.idkId} className='question-answer-container'>
      <span id='question'>Q: {element.question}</span>
      <span id='answer'>A: {element.answer}</span>
    </div>
    )}
  </div>
  )
}

export default ContactUsFAQ