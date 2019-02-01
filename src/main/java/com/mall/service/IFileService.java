package com.mall.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Cxl on 2018/12/19-9:14
 * Description: Mmall
 */
public interface IFileService {
    String upload(MultipartFile multipartFile, String path);
}
