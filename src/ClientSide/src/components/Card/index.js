import React from 'react';

import { Card } from 'antd';
import '../Demo/Demo.css'

const { Meta } = Card;

export default function(){
    return (
        <Card
          hoverable
          style={{ width: 240 }}
          cover={<img alt="example" src="https://os.alipayobjects.com/rmsportal/QBnOOoLaAfKPirc.png" />}
        >
          <Meta title="Europe Street beat" description="www.instagram.com" />
        </Card>
      );
}