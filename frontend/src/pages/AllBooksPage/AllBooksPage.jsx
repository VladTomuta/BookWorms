import React, { useEffect, useState } from 'react'
import {useNavigate} from 'react-router-dom'
import {getAllAvailableBooks} from '../../apis'
import './AllBooksPage.css'
import BookCard from '../../components/BookCard/BookCard'
import ImagePlaceholder from './images/No-Image-Placeholder.png'

export default function AllBooksPage() {
    const [allBooks, setAllBooks] = useState();


    const navigate = useNavigate();
    const user = JSON.parse(sessionStorage.getItem('user'));
    console.log(user);
    const token = JSON.parse(sessionStorage.getItem('user_token'));

    useEffect(() =>{
        const getAllBooksProperly = async () => {
            const response = await getAllAvailableBooks(token,user.user_id);
            setAllBooks(response);
        }

        getAllBooksProperly();
    },[])

    const navigateToProfile = () =>{
        navigate("/loggedIn");
    }
    

    return (
        <div className='AllBooksContainer'>
            <div className='AllBooksHeader'>
                <div className='backToProfileButton' style={{color:"white", cursor:"pointer"}} onClick={() => {navigateToProfile()}}>Back To Profile</div>
                <div className='BookCardsContainer'>
                {allBooks ? (allBooks.map(b => {
                    return <BookCard
                    key = {b.id}
                    image= {ImagePlaceholder}
                    description={b.description}
                    title={b.title}
                    author={b.author}
                    />
                })) : 
                <p>loading</p>}
                </div>
            </div>
        </div>
  )
}