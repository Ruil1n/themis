package dangod.themis.util;

import com.alibaba.fastjson.JSON;
import dangod.themis.dao.score.MajorRepo;
import dangod.themis.model.po.score.Class;
import javassist.bytecode.analysis.Executor;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;

import java.io.File;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.*;


public class MD5UtilTest {
//    @Test
//    public void RecordGen() throws Exception {
//        String[] list = {"Activity", "Honor", "Office", "Practice", "Reserve", "Skill", "Volunteer"};
//        String targetPath = "src/main/java/dangod/themis/dao/score/record/";
//        for(String str : list){
//            Map<String, Object> map = new HashMap<>();
//            map.put("name", str);
//            CodeGenerate.generate("RecordRepo.ftl", targetPath+str+"Repo.java", map);
//        }
//    }
//
//    @Test
//    public void RecordSeviceImp() throws Exception {
//        String[] list = {"Activity", "Honor", "Office", "Practice", "Reserve", "Skill", "Volunteer"};
//        String targetPath = "src/main/java/dangod/themis/service/";
//        Map<String, Object> map = new HashMap<>();
//
//        List<String> entityList = Arrays.asList(list);
//        map.put("entityList", entityList);
//
//        CodeGenerate.generate("StudentRecordService.ftl", targetPath + "StudentRecordService.java", map);
//
//    }
//
//    @Test
//    public void RecordSeviceImplGen() throws Exception {
//        String[] list = {"Activity", "Honor", "Office", "Practice", "Reserve", "Skill", "Volunteer"};
//        String targetPath = "src/main/java/dangod/themis/service/impl/score/";
//        Map<String, Object> map = new HashMap<>();
//
//        List<String> entityList = Arrays.asList(list);
//        map.put("entityList", entityList);
//
//        CodeGenerate.generate("StudentRecordServiceImpl.ftl", targetPath + "StudentRecordServiceImpl.java", map);
//
//    }
//
    @Test
    public void RecordControllerGen() throws Exception {
        String s = "club/\\\\as\\sss";
        String[] ss = s.split("[/&\\\\]");
        for(String str : ss)
            if(str!="\n"&&str!=null&&str!=""&&str!="\r\n")
            System.out.println(str);

    }
//
}