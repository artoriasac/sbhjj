package com.service.inf.file;

import com.model.vo.file.TokenVO;

public interface FileService {


    TokenVO getToken();

    String getQrCode(String barcode);

    String getBarcode(String barcode);
}
