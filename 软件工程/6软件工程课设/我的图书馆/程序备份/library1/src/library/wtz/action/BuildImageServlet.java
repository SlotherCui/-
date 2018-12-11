package library.wtz.action;

import java.io.*;
import java.util.*;

import java.awt.*;
import java.awt.image.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.imageio.*;

import com.sun.image.codec.jpeg.*;
import com.sun.image.codec.jpeg.JPEGCodec;

public class BuildImageServlet extends HttpServlet {
    private Random generator = new Random();
    private Color getRandColor(int fc, int bc) {
        //给定范围获得随机颜色
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    public BuildImageServlet() {
    }

    //private Font mFont=new Font("宋体", Font.PLAIN,12);//设置字体
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //beginTime,endTime 为测试用
        long beginTime;
        long endTime;

        //设置页面不缓存
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        //测试图像生成时间
        beginTime = System.currentTimeMillis();

        // 在内存中创建图象
        //int width = 60, height = 40;
        int width = 100, height = 40;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // 获取图形上下文
        Graphics g = image.getGraphics();

        //生成随机类
        Random random = new Random();

        // 设定背景色
        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);

        //设定字体
        g.setFont(new Font("宋体", Font.PLAIN, 13));

        // 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
        g.setColor(Color.WHITE);

        // 取随机产生的认证码(4位数字)
        String sRand = "";
        /*
             for (int i = 0; i < 4; i++)
             {
          String rand = "";
          if (i % 2 == 0)
          {
            rand = String.valueOf(random.nextInt(10));
          }
          else
          {
            char c = 'A';
            c = (char) (c + (int) (Math.random() * 26));
            rand = String.valueOf(c);
          }

          sRand += rand;
          // 将认证码显示到图象中
          g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110))); //调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
          g.drawString(rand, 13 * i + 6, 26);
             }
         */
        for (int i = 0; i < 4; i++) {
            String rand = "";
            if (i % 2 == 0) {
                //rand = String.valueOf(random.nextInt(10));
                rand = getValidRand("N", 10, random);
                g.setFont(new Font(rand, Font.HANGING_BASELINE, height / 2));
            } else {
                //char c = 65;
                g.setFont(new Font(rand, Font.LAYOUT_LEFT_TO_RIGHT, height / 2));
                //c = (char) (c + random.nextInt(26));
                //rand = String.valueOf(c);
                rand = getValidRand("C", 10, random);
            }

            //g.setFont(new Font(rand, Font.ITALIC, height/2));

            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            g.drawString(String.valueOf(rand), 20 * i + random.nextInt(6) + 3, height - random.nextInt(25));

            sRand += rand;
        }
        //shear(g, width, height, Color.white);

        // 随机产生88个干扰点，使图象中的认证码不易被其它程序探测到
        for (int i = 0; i < 20; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            g.drawOval(x, y, 0, 0);
        }
        // 将认证码存入SESSION
        HttpSession session = request.getSession();
        session.setAttribute("VerifyCode", sRand);

        // 图象生效
        g.dispose();

        endTime = System.currentTimeMillis();
        //System.out.println("图像 " + sRand + " 生成时间：" + (endTime - beginTime));

        /**
         * 将验证码通过ImageIO.write方式输出到页面
         * 该方法已经停用，原因：耗时太长 2007-01-18 by zhanxb
         */
        //测试图像输出时间
        beginTime = System.currentTimeMillis();
        // 输出图象到页面
        //ImageIO.write(image, "JPEG", response.getOutputStream());
        endTime = System.currentTimeMillis();
        //System.out.println("图像 " + sRand + " 生成文件时间：" + (endTime - beginTime));

        /**
         * 使用JPEGImageEncoder将验证码输出到页面
         * added by zhanxb 2007-01-18
         */
        //测试图像输出-new文件时间
        beginTime = System.currentTimeMillis();

        ServletOutputStream out = response.getOutputStream();
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        encoder.encode(image);
        out.close();

        endTime = System.currentTimeMillis();
        //System.out.println("图像 " + sRand + " JPEGImageEncoder 生成文件时间：" + (endTime - beginTime));
    }

    private void shear(Graphics g, int w1, int h1, Color color) {

        shearX(g, w1, h1, color);
        shearY(g, w1, h1, color);
    }

    public void shearX(Graphics g, int w1, int h1, Color color) {

        int period = generator.nextInt(2);

        boolean borderGap = true;
        int frames = 1;
        int phase = generator.nextInt(2);

        for (int i = 0; i < h1; i++) {
            double d = (double) (period >> 1)
                       * Math.sin((double) i / (double) period
                                  + (6.2831853071795862D * (double) phase)
                                  / (double) frames);
            g.copyArea(0, i, w1, 1, (int) d, 0);
            if (borderGap) {
                g.setColor(color);
                g.drawLine((int) d, i, 0, i);
                g.drawLine((int) d + w1, i, w1, i);
            }
        }

    }

    public void shearY(Graphics g, int w1, int h1, Color color) {

        int period = generator.nextInt(40) + 10; // 50;

        boolean borderGap = true;
        int frames = 20;
        int phase = 7;
        for (int i = 0; i < w1; i++) {
            double d = (double) (period >> 1)
                       * Math.sin((double) i / (double) period
                                  + (6.2831853071795862D * (double) phase)
                                  / (double) frames);
            g.copyArea(i, 0, 1, h1, 0, (int) d);
            if (borderGap) {
                g.setColor(color);
                g.drawLine(i, (int) d, i, 0);
                g.drawLine(i, (int) d + h1, i, h1);
            }

        }

    }

    /**
     * 取得合法字符：不能出现如下易混淆的字符: 0（数字）、1（数字）、o（字母）、O（字母）、i、I、l（L）
     * @param charType
     * @param count
     * @return String
     * @throws Exception
     */
    public String getValidRand(String charType, int count, Random random) {
        //System.out.println("getValidRand: " + charType);
        String invalidCString = "01oOiIl";
        //Random random = new Random();
        String rand = "";
        if ("N".equalsIgnoreCase(charType)) {
            for (int i=0; i<count; i++) {
                rand = String.valueOf(random.nextInt(10));
                if ((rand != null) && (invalidCString.indexOf(rand) == -1)) {
                    return rand;
                }
            }
            return "9";//默认数字为9
        } else {
            for (int i = 0; i<count; i++) {
                char c = 65;
                c = (char) (c + random.nextInt(26));
                rand = String.valueOf(c);
                if ((rand != null) && (invalidCString.indexOf(rand) == -1)) {
                    return rand;
                }
            }
            return "Q";//默认字母为Q
        }
    }

}

