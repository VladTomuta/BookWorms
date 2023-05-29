import React, { useEffect, useState } from 'react'
import { getAllTheReviewsOfAUser } from '../../apis';
import ReviewComponent from './ReviewComponent/ReviewComponent';
import './UserReviews.css'

/*
{ 
    "review_id": 0,
    "written_by_id": 0,
    "description": "string",
    "rating": 0,
    "addressed_to_id": 0,
    "date_of_review": "string"
}
*/
export default function UserReviews() {
    
    const user = JSON.parse(sessionStorage.getItem('user'));
    const token = JSON.parse(sessionStorage.getItem('user_token'));
    const [listOfReviews,setListOfReviews] = useState([]);
    
    useEffect(()=>{

        const getAllReviewsProperly = async () => {
            const response = await getAllTheReviewsOfAUser(token,user.user_id);
            setListOfReviews(response);
            console.log(response);
        }
        getAllReviewsProperly();
    },[])

  return (
    <div className='UserReviewsContainer'>
        {listOfReviews.length > 0 ? 
        (listOfReviews.map(review => {
            return <ReviewComponent key={review.id} reviewData = {review} />
        }
        ))
        : 
        <p>..loading</p>
        }
    </div>
  )
}
