import React from 'react'
import './ReviewComponent.css'

export default function ReviewComponent(props) {

    const {reviewData} = props

  return (<div className='reviewComponentContainer'>
            <div className='review-data-left'>
                <div className='avatarInitials'>
                    TV
                </div>
                <div className='avatarName'>
                    Written_By: {reviewData.written_by_id}
                </div>
                <div className='reviewDate'>
                    Date: {reviewData.date_of_review}
                </div>
                <div className='rating'>
                    Rating: {reviewData.rating} *STARS*
                </div>
            </div>
            <div className='description'>
                {reviewData.description}
            </div>
        </div>
  )
}
