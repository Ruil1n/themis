package dangod.themis.util;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.Map;

public class CodeGenerate {
    private static final String ROOT_PATH = ClassUtils.getDefaultClassLoader().getResource("").getPath();
//    private static final String TEMPLATES_PATH = ROOT_PATH + BaseFile.separator + "templates";
    private static final String TEMPLATES_PATH = "templates" + File.separator;

    public static String getRootPath() {
        return ROOT_PATH;
    }

    public static String getTemplatesPath() {
        return TEMPLATES_PATH;
    }

    public static void generate(String templateFile, String target, Map<String, Object> model) {
        generate(TEMPLATES_PATH, templateFile, target, model);
    }

    public static void generate(String rootPath, String templateFile, String targetPath, Map<String, Object> model) {
        Configuration cfg = new Configuration(Configuration.getVersion());
        Template template = null;
        Writer out = null;
        try {
            cfg.setDirectoryForTemplateLoading(new File(rootPath));
            template = cfg.getTemplate(templateFile);
            File target = new File(targetPath);
            if(!target.exists()) {
                File targetParent = new File(target.getParent());
                if(!targetParent.exists())
                    targetParent.mkdirs();
                target.createNewFile();
            }
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(target.getPath()), "UTF-8"));
            template.process(model, out);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
