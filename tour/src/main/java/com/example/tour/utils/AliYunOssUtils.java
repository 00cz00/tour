package com.example.tour.utils;

import com.aliyun.oss.*;
import com.aliyun.oss.common.auth.*;
import com.aliyun.oss.model.GetObjectRequest;
import java.io.File;

public class AliYunOssUtils {
    public static void main(String url,String userId) throws Throwable {
        // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
        String endpoint = "https://oss-cn-hangzhou.aliyuncs.com";
        // 从环境变量中获取访问凭证。运行本代码示例之前，请确保已设置环境变量OSS_ACCESS_KEY_ID和OSS_ACCESS_KEY_SECRET。
        String assessKeyId="LTAI5tQBFnQSj6JB2NJs1qW2";
        String assessKeySecret="C4WUGmdsqLpXVNgpzzw3QseajsVKsA";
        // 填写Bucket名称，例如examplebucket。
        String bucketName = "316-tour";
        // 填写Object完整路径。Object完整路径中不能包含Bucket名称。
        String objectName = userId+".jpg";
        // 填写本地文件的完整路径，例如D:\\localpath\\example-resize.jpg。如果指定的本地文件存在会覆盖，不存在则新建。
        String localPath = url;

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint,assessKeyId,assessKeySecret);

        try {
            // 将图片缩放为固定宽高100 px。
            String style = "image/resize,m_fixed,w_100,h_100";
            GetObjectRequest request = new GetObjectRequest(bucketName, objectName);
            request.setProcess(style);
            // 将处理后的图片命名为example-resize.jpg并保存到本地。
            // 如果未指定本地路径只填写了本地文件名称（例如example-resize.jpg），则文件默认保存到示例程序所属项目对应本地路径中。
            ossClient.getObject(request, new File(localPath));
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }
}