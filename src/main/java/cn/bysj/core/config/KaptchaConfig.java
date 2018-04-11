package cn.bysj.core.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * @author victor.qin
 * @date 2018/3/30 11:19
 */

@Component
public class KaptchaConfig {
    private static DefaultKaptcha captchaProducer;

    static {
        initDefault();
    }

    public static void initDefault() {

        Properties defaultProperties = new Properties();
        defaultProperties.setProperty("kaptcha.border", "yes");
        defaultProperties.setProperty("kaptcha.border.color", "105,179,90");
        defaultProperties.setProperty("kaptcha.textproducer.font.color", "blue");
        defaultProperties.setProperty("kaptcha.image.width", "110");
        defaultProperties.setProperty("kaptcha.image.height", "40");
        defaultProperties.setProperty("kaptcha.textproducer.font.size", "30");
        defaultProperties.setProperty("kaptcha.session.key", "code");
        defaultProperties.setProperty("kaptcha.textproducer.char.length", "4");
        defaultProperties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");
        init(defaultProperties);
    }

    public static void init(Properties properties) {

        Config config = new Config(properties);

        captchaProducer = new DefaultKaptcha();

        captchaProducer.setConfig(config);
    }

    public static String createText() {
        return captchaProducer.createText();
    }

    public static BufferedImage createImage(String capText) {
        return captchaProducer.createImage(capText);
    }

    public static void writeToResponse(String capText, HttpServletResponse response) throws IOException {

        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");

        BufferedImage bi = captchaProducer.createImage(capText);
        OutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpg", out);

        try {
            out.flush();
        } finally {
            out.close();
        }
    }


}
