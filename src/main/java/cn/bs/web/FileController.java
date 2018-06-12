package cn.bs.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
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
	public String upload(MultipartFile file,HttpServletRequest request) throws IOException{  
        String path = "d:" + File.separator +"fzTemp";
        Integer pId = Integer.valueOf(request.getParameter("pId"));
        String userName = request.getParameter("uName");
        path = path + File.separator + pId;
		String fileName = file.getOriginalFilename();
		if(fileName==null || fileName.trim() == "") {
			return "alert('请选择文件并上传');";
		}
		NUser user = nService.findByName(userName);
		Integer userID = user.getUid();
		String filePath = path + fileName;
		Project project = new Project();
		project.setPid(pId);
		project.setBlueprint(filePath);
		project.setUid(userID);
		project.setuName(userName);
		projectService.update(project);
		File newDir = new File(path);
		if(!newDir.exists()) {
			newDir.mkdirs();
		}
        File dir = new File(path,fileName);
        File myFile = new File("D:"+File.separator + "fzTemp" + File.separator +"test.bat");
        if(!myFile.exists()) {
        	myFile.createNewFile();
        }
        FileWriter fileWriter =new FileWriter(myFile);
        fileWriter.write("Start " + path +File.separator+fileName);
        fileWriter.flush();
        fileWriter.close();
        if(!dir.exists()){  
            dir.mkdirs();  
        }  
        //MultipartFile自带的解析方法  
        try {
			file.transferTo(dir);
		} catch (Exception e) {
			return e.getMessage();
		}
        return "<script>alert('success');window.location.href='../index1.html';</script>";  
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
