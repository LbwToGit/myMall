package com.mall.service.impl;

import com.google.common.collect.Lists;
import com.mall.service.IFileService;
import com.mall.util.FTPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;


/**
 * Created by Cxl on 2018/12/19-9:14
 * Description: Mmall
 */
@Service
public class FileServiceImpl  implements IFileService {
    private Logger logger= LoggerFactory.getLogger(FileServiceImpl.class);
    public String upload(MultipartFile multipartFile,String path){
        String fileName=multipartFile.getOriginalFilename();
        //扩展名
        //aa.jpg
        String fileExtensionName=fileName.substring(fileName.lastIndexOf(".")+1);
        String uploadFileName= UUID.randomUUID()+"."+fileExtensionName;
        logger.info("开始上传文件，上传文件的文件名:{},上传的路径:{},新文件名:{}",fileName,path,uploadFileName);

        File file=new File(path);
        if (!file.exists()){
            file.setWritable(true);
            file.mkdirs();
        }

        File targetFile=new File(path,uploadFileName);
        try {
            multipartFile.transferTo(targetFile);
            //文件以及上传成功

            //todo  将targetFile上传到ftp服务器
            FTPUtil.uploadFile(Lists.<File>newArrayList(targetFile));
            //todo 上传完之后,删除upload下面的文件
            targetFile.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return targetFile.getName();
    }
}
