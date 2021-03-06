package edu.cqu.fly.crawler.controller;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
 
@RequestMapping("/file")
@Controller
public class FileUploadController {
 
    @RequestMapping(value="/uploadFile", method=RequestMethod.GET)
    public @ResponseBody String provideUploadInfo() {
        return "You can upload a file by posting to this same URL.";
    }
 
    @RequestMapping(value="/uploadFile", method=RequestMethod.POST)
    public @ResponseBody String handleFileUpload(@RequestParam(value = "name", required = false) String name,
            @RequestParam(value="file", required = false) MultipartFile file){
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(name + "-uploaded")));
                stream.write(bytes);
                stream.close();
                return "You successfully uploaded " + name + " into " + name + "-uploaded !";
            } catch (Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + name + " because the file was empty.";
        }
    }
 
}