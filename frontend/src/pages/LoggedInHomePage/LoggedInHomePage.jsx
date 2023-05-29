import React from 'react'
import './LoggedInHomePage.css'
import ProfileSideBar from '../../components/Profile/ProfileSideBar'
import {useLocation} from 'react-router-dom';
import ProfileMainContent from '../../components/ProfileMainContent/ProfileMainContent'

function LoggedInHomePage() {

    const location = useLocation();

    return (
        <div className='LoggedInHomePage-Container'>
            <ProfileSideBar />
            <ProfileMainContent />
        </div>
    )
}

export default LoggedInHomePage