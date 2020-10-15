import React from 'react';
import Dash from '../Card';
import List from '../List';

import { Divider } from 'antd';
import '../Demo/Demo.css'

export default function(){


    return(
        <div className="content-inside">
        <Dash />
        <Divider>Snail Project</Divider>
        <List />


        </div>
    )
}