import { PageContainer, dateArrayFormatter } from '@ant-design/pro-components';
import React, { useEffect, useState } from 'react';
import {Button, Card, Descriptions, Form, message, Input, Spin, Divider, Col, Row} from 'antd';
import { useParams } from '@@/exports';
import { getInterfaceInfoByIdUsingGet, invokeInterfaceInfoUsingPost, updateInterfaceInfoUsingPost } from "@/services/wellapi-backend/interfaceInfoController";
import { size } from 'lodash';
import { addUserInterfaceInfoUsingPost, getUserInterfaceInfoByIdUsingGet, updateUserInterfaceInfoUsingPost } from '@/services/wellapi-backend/userInterfaceInfoController';

/**
 * 主页
 * @constructor
 */
const Index: React.FC = () => {
  const [loading, setLoading] = useState(false);
  const [data, setData] = useState<API.InterfaceInfo>();
  const [invokeRes, setInvokeRes] = useState<any>();
  const [CountData, setCountData] = useState<API.UserInterfaceInfo>();
  const [invokeLoading, setInvokeLoading] = useState(false);

  const params = useParams();
  const loadData = async () => {
    if (!params.id) {
      message.error('参数不存在');
      return;
    }
    setLoading(true);
    try {
      const res = await getInterfaceInfoByIdUsingGet({
        id: Number(params.id),
      });
      setData(res.data);
    } catch (error: any) {
      message.error('请求失败，' + error.message);
    }
    refresh();
    setLoading(false);
  };

  const refresh = async () =>{
    try {
      const res = await getUserInterfaceInfoByIdUsingGet({
        id: Number(params.id),
      });
      setCountData(res.data);
    } catch (error: any) {
      message.error('请求失败，' + error.message);
    }
  }
  useEffect(() => {
    loadData();
  }, []);

  const onFinish = async (values: any) => {
    if (!params.id) {
      message.error('接口不存在');
      return;
    }
    setInvokeLoading(true);
    try {
      const res = await invokeInterfaceInfoUsingPost({
        id: params.id,
        ...values,
      });
      setInvokeRes(res.data);
      if(res.code === 0){
        message.info('请求已发送');
      }else{
      message.success('请求成功');
      }
    } catch (error: any) {
      message.error('操作失败，' + error.message);
    }   
    refresh();
    setInvokeLoading(false);
  };

  const addUserInterfaceInfo = async (values: any) => {
    try {
      const res = await addUserInterfaceInfoUsingPost({
        interfaceInfoId: values,
        leftNum: 5,
      });
      if(res){
        message.info("已增加");
        refresh();
      }
    } catch (error: any) {
      message.error('请求失败，' + error.message);
    }
  };

  const addleftNum = async (values: API.UserInterfaceInfo | any) => {
    try {
      const res = await updateUserInterfaceInfoUsingPost({
        leftNum: values.leftNum + 5,
        id: values.id,
        interfaceInfoId: values.InterfaceInfoId,
        userId: values.userId,    
      });
      if(res){
        message.info("已增加");
        refresh();
      }
    } catch (error: any) {
      message.error('请求失败，' + error.message);
    }
  };

  return (
    <PageContainer title="查看接口文档">
      <Card>
        {data ? (
          <Descriptions title={data.name} column={1}>
            <Descriptions.Item label="接口状态">{data.status ? '开启' : '关闭'}</Descriptions.Item>
            <Descriptions.Item label="描述">{data.description}</Descriptions.Item>
            <Descriptions.Item label="请求地址">{data.url}</Descriptions.Item>
            <Descriptions.Item label="请求方法">{data.method}</Descriptions.Item>
            <Descriptions.Item label="请求参数">{data.requestParams}</Descriptions.Item>
            <Descriptions.Item label="请求头">{data.requestHeader}</Descriptions.Item>
            <Descriptions.Item label="响应头">{data.responseHeader}</Descriptions.Item>
            <Descriptions.Item label="创建时间">{data.createTime}</Descriptions.Item>
            <Descriptions.Item label="更新时间">{data.updateTime}</Descriptions.Item>
          </Descriptions>
        ) : (
          <>接口不存在</>
        )}
      </Card>
      <Divider />
      <Row gutter={16}>
        <Col span={8}>
          <Card title="总调用次数 " bordered={false}>
            {CountData?.totalNum == null ? 0 :CountData?.totalNum}
          </Card>
        </Col>
        <Col span={8}>
          <Card title="剩余调用次数" 
                extra={
                  <Button onClick={() => {
                    if(CountData === null){
                      addUserInterfaceInfo(data?.id) 
                      
                    }else{
                      addleftNum(CountData)
                    }
                  }}>
                   增加次数
                  </Button>} 
                bordered={false}>
            {CountData?.leftNum == null ? 0 :CountData?.leftNum}
          </Card>
        </Col>
      </Row>
      <Divider />
      <Card title="在线测试">
        <Form name="invoke" layout="vertical" onFinish={onFinish}>
          <Form.Item label="请求参数" name="userRequestParams">
            <Input.TextArea />
          </Form.Item>
          <Form.Item wrapperCol={{ span: 16 }}>
            <Button type="primary" htmlType="submit">
              调用
            </Button>
          </Form.Item>
        </Form>
      </Card>
      <Divider />
      <Card title="返回结果" loading={invokeLoading}>
        {invokeRes}
      </Card>
    </PageContainer>
  );
};

export default Index;
