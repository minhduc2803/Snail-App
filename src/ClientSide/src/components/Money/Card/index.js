import React from 'react';
import Avatar from 'react-avatar';
import { Card } from 'antd';
import { useSelector } from 'react-redux';
import '../../Frame/PageStructure/antd.css'

const { Meta } = Card;

export default function(){
  const user = useSelector(state => state.user);
  const balance = useSelector(state => state.balance);

    return (
        <Card
          hoverable
          style={{ width: 340, margin:0, marginTop: 40 }}
          
        >
          <Avatar name={`${user.fullName}`} size="60" round={true}/>
          <div>{user.fullName}</div>
          <div>Số dư: {balance.balance}</div>
          <div>Thay đổi lần cuối: {balance.lastTimeUpdate}</div>
        </Card>
      );
}