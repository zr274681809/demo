package com.example.demo.utils;

import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/** @Author lyn
 * @Description //TODO fastdfs工具类
 * @Date 2020/1/13 9:37
 */
public class FdfsUtil {
    private FdfsUtil(){}

    /* @Author lyn
     * @ModuleName upload
     * @Description //TODO 上传文件到fastdfs
     * @Parme [file]
     * @return java.lang.String
     */
    public static String upLoad(MultipartFile file) throws IOException, MyException {
        ClientGlobal.initByProperties("fastdfs-client.properties");
        System.out.println("network_timeout="+ClientGlobal.g_network_timeout+"ms");
        System.out.println("charset="+ClientGlobal.g_charset);
        TrackerClient tc = new TrackerClient();
        TrackerServer ts = tc.getTrackerServer();
        if (ts==null){
            System.out.println("getConnection return null");
            return "-1";
        }
        StorageServer storeStorage = tc.getStoreStorage(ts);
        if(storeStorage==null){
            System.out.println("getStoreStorage return null");
            return "-1";
        }
        StorageClient1 storageClient1 = new StorageClient1(ts,storeStorage);
        if (file.getOriginalFilename()==null || "".equals(file.getOriginalFilename())){
            return "";
        }
        String[] split = file.getOriginalFilename().split("[.]");
        String path;
        if (split.length>1){
            path = storageClient1.upload_appender_file1(file.getBytes(),split[split.length-1],null);
        }else {
            path = storageClient1.upload_appender_file1(file.getBytes(),".txt",null);
        }
        return path;
    }

    /* @Author lyn
     * @ModuleName downLoad
     * @Description //TODO 下载文件
     * @Parme [path]
     * @return byte[]
     */
    public static byte[] downLoad(String path) throws IOException, MyException {
        ClientGlobal.initByProperties("fastdfs-client.properties");
        System.out.println("network_timeout="+ClientGlobal.g_network_timeout+"ms");
        System.out.println("charset="+ClientGlobal.g_charset);
        TrackerClient tc = new TrackerClient();
        TrackerServer ts = tc.getTrackerServer();
        StorageServer storeStorage = tc.getStoreStorage(ts);
        StorageClient1 storageClient1 = new StorageClient1(ts, storeStorage);
        return storageClient1.download_file1(path);
    }
}