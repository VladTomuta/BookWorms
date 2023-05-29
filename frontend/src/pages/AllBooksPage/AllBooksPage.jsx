import React, { useEffect, useState } from 'react'
import {getAllBooks} from '../../apis'
import './AllBooksPage.css'

export default function AllBooksPage() {
    const [allBooks, setAllBooks] = useState();

    useEffect(() =>{
        const getAllBooksProperly = async () => {
            const response = await getAllBooks();
            setAllBooks(response);
        }

        getAllBooksProperly();
    },[])

    console.log(allBooks);

    return (
        <div className='AllBooksContainer'>
            <div className='AllBooksHeader'>
                <div className='backToProfileButton'>Back To Profile</div>
            </div>
        </div>
  )
}