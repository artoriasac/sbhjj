package com.service.imp.file;

import com.aliyun.oss.OSSClient;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sts.model.v20150401.AssumeRoleRequest;
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;
import com.common.utils.BarcodeUtil;
import com.common.utils.QrCodeUtils;
import com.google.zxing.qrcode.encoder.QRCode;
import com.model.vo.file.TokenVO;
import com.service.inf.file.FileService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.UUID;

@Service
@Transactional(rollbackFor = Exception.class)
public class FileServiceImpl implements FileService {
    @Value(value = "${STS_ENDPOINT}")
    private String stsEndpoint;
    @Value(value = "${ACCESS_KEY_ID}")
    private String accessKeyId;
    @Value(value = "${ACCESS_KEY_SECRET}")
    private String accessKeySecret;
    @Value(value = "${ROLE_ARN}")
    private String roleArn;
    @Value(value = "${ROLE_SESSION_NAME}")
    private String roleSessionName;
    @Value(value = "${BUCKET_NAME}")
    private String bucket;
    @Value(value ="${OSS_URL}")
    private String ossUrl;
    @Value(value = "${OSS_ENDPOINT}")
    private String ossEndpoint;

    private final static String BARCODE="barcode";

    private final static String QRCODE="qrCode";

    @Override
    public TokenVO getToken() {
        try {
            // 添加endpoint（直接使用STS endpoint，前两个参数留空，无需添加region ID）
            DefaultProfile.addEndpoint("", "", "Sts", stsEndpoint);
            // 构造default profile（参数留空，无需添加region ID）
            IClientProfile profile = DefaultProfile.getProfile("", accessKeyId, accessKeySecret);
            // 用profile构造client
            DefaultAcsClient client = new DefaultAcsClient(profile);
            final AssumeRoleRequest request = new AssumeRoleRequest();
            request.setMethod(MethodType.POST);
            request.setRoleArn(roleArn);
            request.setRoleSessionName(roleSessionName);
            final AssumeRoleResponse response = client.getAcsResponse(request);
            System.out.println("Expiration: " + response.getCredentials().getExpiration());
            System.out.println("Access Key Id: " + response.getCredentials().getAccessKeyId());
            System.out.println("Access Key Secret: " + response.getCredentials().getAccessKeySecret());
            System.out.println("Security Token: " + response.getCredentials().getSecurityToken());
            System.out.println("RequestId: " + response.getRequestId());
            TokenVO tokenVO=new TokenVO();
            BeanUtils.copyProperties(response,tokenVO);
            tokenVO.setBucket(bucket);
            return tokenVO;
        } catch (ClientException e) {
            System.out.println("Failed：");
            System.out.println("Error code: " + e.getErrCode());
            System.out.println("Error message: " + e.getErrMsg());
            System.out.println("RequestId: " + e.getRequestId());
        }
        return null;
    }

    @Override
    public String getQrCode(String qrCode) {
        ByteArrayOutputStream byteArrayOutputStream = QrCodeUtils.encodeImg(qrCode);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        String key =QRCODE+"/"+ UUID.randomUUID().toString();
        key+=".png";
        upload(key,byteArrayInputStream);
        String url=ossUrl+key;
        return url;
    }

    @Override
    public String getBarcode(String barcode) {
        byte[] generate = BarcodeUtil.generate(barcode);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(generate);
        String key =BARCODE+"/"+ UUID.randomUUID().toString();
        key+=".png";
        upload(key,byteArrayInputStream);
        String url=ossUrl+key;
        return url;
    }

    private void upload(String key,ByteArrayInputStream byteArrayInputStream){
        OSSClient ossClient = new OSSClient(ossEndpoint, accessKeyId, accessKeySecret);
        ossClient.putObject(bucket, key, byteArrayInputStream);
        ossClient.shutdown();
    }
}
