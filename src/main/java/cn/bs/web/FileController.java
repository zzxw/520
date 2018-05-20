package cn.bs.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.bs.entity.NUser;
import cn.bs.entity.Project;
import cn.bs.service.NUserService;
import cn.bs.service.ProjectService;


@Controller
@RequestMapping("/file")
public class FileController {

	@Resource
	private ProjectService projectService;
	
	@Resource
	private NUserService nService;
	
	@RequestMapping(value="/upload.do")
	@ResponseBody
	public String upload(MultipartFile file,HttpServletRequest request){  
        String path = "d:" + File.separator +"fzTemp";
		String fileName = file.getOriginalFilename();
		String userName = "";
		NUser user = nService.findByName(userName);
		Integer userID = user.getUid();
		String filePath = path + fileName;
		Project project = new Project();
		project.setBlueprint(filePath);
		project.setUid(userID);
		projectService.update(project);
        File dir = new File(path,fileName);          
        if(!dir.exists()){  
            dir.mkdirs();  
        }  
        //MultipartFile自带的解析方法  
        try {
			file.transferTo(dir);
		} catch (Exception e) {
			return e.getMessage();
		}
        return "success";  
	}
	
	@RequestMapping("/download")
	@ResponseBody
	public String down(HttpServletRequest request,HttpServletResponse response){
		/*Integer projectId = null;
		Project project = projectService.search(projectId);
		String fileName = project.getBlueprint();*/
		String fileName = "/myfile.txt";  
        //获取输入流  
        InputStream bis = null;  
        BufferedOutputStream out = null;
        //假如以中文名下载的话  
        String filename = "download.txt";  
        //转码，免得文件名中文乱码  
        try{
        	 bis = new BufferedInputStream(new FileInputStream(new File(fileName)));
        	 filename = URLEncoder.encode(filename,"UTF-8");  
             //设置文件下载头  
             response.addHeader("Content-Disposition", "attachment;filename=" + filename);    
             //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型    
             response.setContentType("multipart/form-data");   
             out  = new BufferedOutputStream(response.getOutputStream());  
             int len = 0;  
             while((len = bis.read()) != -1){  
                 out.write(len);  
                 out.flush();  
             }  
        }catch (Exception e) {
        	return e.getMessage();
		}finally {
			try {
				bis.close();
				out.close();
			} catch (IOException e) {
				return e.getMessage();
			}
		}
       return "success";
	}
}
