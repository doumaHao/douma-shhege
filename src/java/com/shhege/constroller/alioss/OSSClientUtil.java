package com.shhege.constroller.alioss;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
 
/**
 * 阿里云 OSS文件类
 *
 * @author haoxijun
 */
@Component("ossClientUtil")
public class OSSClientUtil {
 
  private String endpoint = "oss-cn-shanghai.aliyuncs.com";
  private String accessKeyId = "";
  private String accessKeySecret = "";
  private String bucketName = "haoxijun";
  private String filedir = "data/";
 
  private OSSClient ossClient;
 
  public OSSClientUtil() {
	    ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
	  }
	 
	  /**
	   * 初始化
	   */
	  public void init() {
	    ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
	  }
	 
	  /**
	   * 销毁
	   */
	  public void destory() {
	    ossClient.shutdown();
	  }
	  
	  /**
	   * file上传阿里云OSS
	   * @param file
	   * @return
	   */
	  public String uploadImg2Oss(MultipartFile file) {
	    if (file.getSize() > 1024 * 1024) {
	      throw new RuntimeException("上传图片大小不能超过1M！");
	    }
	    String originalFilename = file.getOriginalFilename();
	    String substring = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
	    Random random = new Random();
	    String name = random.nextInt(10000) + System.currentTimeMillis() + substring;
	    try {
	      InputStream inputStream = file.getInputStream();
	      String key = this.uploadFile2OSS(inputStream, name);
	      
	      System.out.println(getImgUrl(name));
	      System.out.println(getImgUrl(key));
	      return name;
	    } catch (Exception e) {
	      throw new RuntimeException("图片上传失败");
	    }
	  }
	  
	  /**
	   * 上传到OSS服务器  如果同名文件会覆盖服务器上的
	   *
	   * @param instream 文件流
	   * @param fileName 文件名称 包括后缀名
	   * @return 出错返回"" ,唯一MD5数字签名
	   */
	  public String uploadFile2OSS(InputStream instream, String fileName) {
	    String ret = "";
	    try {
	      //创建上传Object的Metadata 
	      ObjectMetadata objectMetadata = new ObjectMetadata();
	      objectMetadata.setContentLength(instream.available());
	      objectMetadata.setCacheControl("no-cache");
	      objectMetadata.setHeader("Pragma", "no-cache");
	      objectMetadata.setContentType(getcontentType(fileName.substring(fileName.lastIndexOf("."))));
	      objectMetadata.setContentDisposition("inline;filename=" + fileName);
	      //上传文件
	      PutObjectResult putResult = ossClient.putObject(bucketName, filedir + fileName, instream, objectMetadata);
	      ret = putResult.getETag();
	    } catch (IOException e) {
	      System.out.println("上传失败");
	    } finally {
	      try {
	        if (instream != null) {
	          instream.close();
	        }
	      } catch (IOException e) {
	        e.printStackTrace();
	      }
	    }
	    return ret;
	  }
	 
	  /**
	   * 上传图片
	   *
	   * @param url
	   */
	  public void uploadImg2Oss(String url) {
	    File fileOnServer = new File(url);
	    FileInputStream fin;
	    try {
	      fin = new FileInputStream(fileOnServer);
	      String[] split = url.split("/");
	      this.uploadFile2OSS(fin, split[split.length - 1]);
	    } catch (FileNotFoundException e) {
	      throw new RuntimeException("图片上传失败");
	    }
	  }
	 
	  /**
	   * 获得图片路径
	   *
	   * @param fileUrl
	   * @return
	   */
	  public String getImgUrl(String imgId) {
	    if (!StringUtils.isEmpty(imgId)) {
	      String[] split = imgId.split("/");
	      return this.getUrl(this.filedir + split[split.length - 1]);
	    }
	    return null;
	  }
	 
	  
	 
	  /**
	   * Description: 判断OSS服务文件上传时文件的contentType
	   *
	   * @param FilenameExtension 文件后缀
	   * @return String
	   */
	  public static String getcontentType(String FilenameExtension) {
	    if (FilenameExtension.equalsIgnoreCase("bmp")) {
	      return "image/bmp";
	    }
	    if (FilenameExtension.equalsIgnoreCase("gif")) {
	      return "image/gif";
	    }
	    if (FilenameExtension.equalsIgnoreCase("jpeg") ||
	        FilenameExtension.equalsIgnoreCase("jpg") ||
	        FilenameExtension.equalsIgnoreCase("png")) {
	      return "image/jpeg";
	    }
	    if (FilenameExtension.equalsIgnoreCase("html")) {
	      return "text/html";
	    }
	    if (FilenameExtension.equalsIgnoreCase("txt")) {
	      return "text/plain";
	    }
	    if (FilenameExtension.equalsIgnoreCase("vsd")) {
	      return "application/vnd.visio";
	    }
	    if (FilenameExtension.equalsIgnoreCase("pptx") ||
	        FilenameExtension.equalsIgnoreCase("ppt")) {
	      return "application/vnd.ms-powerpoint";
	    }
	    if (FilenameExtension.equalsIgnoreCase("docx") ||
	        FilenameExtension.equalsIgnoreCase("doc")) {
	      return "application/msword";
	    }
	    if (FilenameExtension.equalsIgnoreCase("xml")) {
	      return "text/xml";
	    }
	    return "image/jpeg";
	  }
	 
	  /**
	   * 获得url链接
	   *
	   * @param key
	   * @return
	   */
	  public String getUrl(String key) {
	    // 设置URL过期时间为10年  3600l* 1000*24*365*10
	    Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 10);
	    // 生成URL
	    URL url = ossClient.generatePresignedUrl(bucketName, key, expiration);
	    if (url != null) {
	      return url.toString();
	    }
	    return null;
	  }
}
