package com.lzqs.zhangyushu.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzqs.zhangyushu.common.ConfigBeanProp;
import com.lzqs.zhangyushu.common.UploadActionUtil;
import com.lzqs.zhangyushu.dao.CommonFileMapper;
import com.lzqs.zhangyushu.entity.CommonFile;
import com.lzqs.zhangyushu.service.CommonFileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-07-06
 */
@Service
public class CommonFileServiceImpl extends ServiceImpl<CommonFileMapper, CommonFile> implements CommonFileService {

    @Resource
    private ConfigBeanProp configBeanProp;

    @Resource
    private CommonFileMapper commonFileMapper;

    /**
     * 上传文件
     *
     * @param request
     * @param
     * @param
     * @return
     */
    @Override
    public List<CommonFile> uploadFile(HttpServletRequest request) {
        List<CommonFile> commonFiles = new ArrayList<>();
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        Iterator<String> iterator = multiRequest.getFileNames();
        while (iterator.hasNext()) {
            // 取得上传文件
            MultipartFile file = multiRequest.getFile(iterator.next());
            if (file != null) {
                // 取得当前上传文件的文件名称
                String myFileName = file.getOriginalFilename();
                // 如果名称不为“”,说明该文件存在，否则说明该文件不存在
                if (myFileName.trim() != "") {
                    String fileTyps = myFileName.substring(myFileName.lastIndexOf("."));
                    // String tempName="demo"+fileTyps;
                    String tempName = UUID.randomUUID().toString() + fileTyps;
                    // 创建文件夹
                    String folderPath = configBeanProp.getFile_upload_folder() + UploadActionUtil.folderName();
                    System.out.println("folderPath========" + folderPath);
                    File fileFolder = new File(folderPath);
                    if (!fileFolder.exists() && !fileFolder.isDirectory()) {
                        fileFolder.mkdirs();
                    }
                    File uploadFile = new File(folderPath + File.separator + tempName);
                    try {
                        file.transferTo(uploadFile);
                        myFileName = UploadActionUtil.folderName() + File.separator + tempName;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    commonFiles.add(saveCommonFile(file.getOriginalFilename(), UploadActionUtil.folderName() + File.separator + tempName, uploadFile.length()));
                }
            }
        }
        return commonFiles;
    }

    /**
     * 上传文件
     *
     * @param
     * @param fileName
     * @param filePath
     * @param fileSize
     * @param
     * @return
     */
    @Override
    public CommonFile saveCommonFile(String fileName, String filePath, Long fileSize) {
        CommonFile commonFile = new CommonFile();
        commonFile.setFileName(fileName);
        commonFile.setFilePath(filePath);
        commonFile.setFileSize(fileSize);
        commonFile.setCreateTime(LocalDateTime.now());
        commonFile.setIsDelete(0);
        commonFileMapper.insert(commonFile);
        return commonFile;
    }
}
