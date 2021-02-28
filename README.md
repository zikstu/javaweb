# Java Web

## Tomcat

Web应该服务器: Tomcat, Jboos, Weblogic, Jetty

- 安装Tomcat

  官网下载：`https://tomcat.apache.org/download-90.cgi`

- 解压缩

  bin： 存放各个平台下启动和停止tomcat 服务的脚本文件

  conf：存放tomcat 服务器的配置文件

  lib：存放tomcat 服务所需要的jar

  logs：存放tomcat 服务运行的日志

  temp：tomcat 运行时的临时文件

  webapps：存放允许客户端方法的资源（Java 程序）

  work：存放tomcat 将jsp转换之后的Servlet 文件

### IDEA 集成 Tomcat

- 创建Java Web 工程
- IDEA 中配置 Tomcat



## Servlet

### 什么是Servle

Servlet 是Java Web 开发的基石，与平台无关的服务器组件，它运行在Servlet 客户端、Web 应用服务器、Tomcat，负责与客户端进行通信。

#### Servlet 的功能

1. 创建并返回客户端请求的动态HTML页面
2. 与数据库进行通信

#### 如何使用Servlet

Servlet 本身是一组接口，自定义一个类，并实现Servlet 接口，这个类就具备了接收客户端的请求并作出回应。

浏览器不是直接访问Servlet 文件，只能通过映射的方式间接来访问，映射需要开发者手动来配置，有两种配置方式。

- 基于XML文件配置

```java
package com.xuezhang.servlet;

import javax.servlet.*;
import java.io.IOException;

/**
 * @description:
 * @author: 学长
 * @date: 2021/2/27 13:05
 */
public class MyServlet implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("服务端MyServlet接收到请求");
        servletResponse.setContentType("text/html;charset=UTF-8");
        servletResponse.getWriter().write("你好! Client");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">
    
    <servlet>
        <servlet-name>MyServlet</servlet-name>
        <servlet-class>com.xuezhang.servlet.MyServlet</servlet-class>
    </servlet>
    
    <servlet-mapping>
        <servlet-name>MyServlet</servlet-name>
        <url-pattern>/myservlet</url-pattern>
    </servlet-mapping>
</web-app>
```

- 基于注解配置

```java
package com.xuezhang.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

/**
 * @description:
 * @author: 学长
 * @date: 2021/2/27 22:04
 */
@WebServlet("/hello")
public class HelloServlet implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        servletResponse.setContentType("text/html;charset=UTF-8");
        servletResponse.getWriter().write("你好, Servlet!");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
```

#### Servlet 生命周期

1. 当浏览器请求Servlet的时候，Tomcat 会查询当前Servlet 的对象实例是否存在，如果不存在，则通过反射机制动态创建对象。如果存在，直接执行第3步。
2. 执行init 方法完成初始化操作
3. 执行Service 方法完成业务逻辑操作
4. 关闭tomcat的时候，会执行destroy 方法，释放当前对象资源

Servlet 生命周期方法：无参构造函数，init，service，destroy

1. 无参构造只调用一次，创建对象
2. init 只调用一次，初始化对象
3. service 调用N次，执行业务方法
4. destroy 调用一次，释放对象资源

#### Servlet  层次结构

Servlet 》 GenericServlet 〉HttpServlet

GenericServlet 实现了Servlet 接口，同时为它屏蔽了不常用的方法，子类只需要重新Service 方法。

HttpServlet 继承了 GenericServlet ，根据请求方式进行分发处理。

开发者定义的 Servlet 类只需要继承 HttpServlet 即可。

- 简单代码实现

自定义MyGenericServlet类

```java
package com.xuezhang.servlet;

import javax.servlet.*;
import java.io.IOException;

/**
 * @description:
 * @author: 学长
 * @date: 2021/2/27 23:21
 */
public class MyGenericServlet implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
```

- 自定义MyHttpServlet 类

```java
package com.xuezhang.servlet;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description:
 * @author: 学长
 * @date: 2021/2/27 23:09
 */
public class MyHttpServlet extends MyGenericServlet {
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String method = request.getMethod();

        switch (method){
            case "GET":
                this.doGet(request, response);
                break;
            case "POST":
                this.doPost(request, response);
                break;
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }
}
```

- 定义测试类

```java
package com.xuezhang.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description:
 * @author: 学长
 * @date: 2021/2/27 23:33
 */
@WebServlet("/testHttpServlet")
public class TestHttpServlet extends MyHttpServlet{
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().write("TestHttpServlet-> GET");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.getWriter().write("TestHttpServlet-> POST");
    }
}
```

## JSP

### 什么是JSP

JSP本质就是一个Servlet，JSP主要负责与用户交互，将最终的界面呈现给用户，HTML+JS+CSS+JAVA的混合文件。

当服务器接收到一个后缀是jsp的请求时，会将该请求交给JSP 引擎去处理，每个jsp页面第一次被访问的时候，JSP 引擎会将它翻译成一个Servlet 文件，在由Web 容器调用Servlet 去完成响应。

单纯的从开发角度看，JSP就是在HTML中嵌入Java 程序。

具体的嵌入方式由三种：

1. JSP脚本：执行Java逻辑代码

   ```jsp
   <%
   	Java代码
   %>
   ```

   

2. JSP声明：定义java 方法

   ```
   <%!
   	声明 Java 方法
   %>
   ```

   

3. JSP表达式：把Java 对象直接输出到HTML页面中

   ```jsp
   <%=Java 变量%>
   ```

### JSP 内置对象（9个）

1. request：表示一次请求，HttpServletRequest。
2. response：表示一次响应，HttpServletResponse。
3. pageContext：页面上下文，获取页面信息，PageContext。
4. session：表示一次会话，保存用户信息，HttpSession。
5. appellation：表示当前Web应用，全局对象，保存所有用户共享信息，ServletContext。
6. config：当前JSP对应的Servlet的ServletConfig 对象，获取当前Servlet 的信息。
7. out：向浏览器输出数据，JspWriter。
8. page：当前JSP 对应的Servlet 对象，Servlet。
9. exception：表示JSP页面发生的异常，Exception。

## Filter 过滤器

### 功能

1. 用来拦截传入的请求和传出的响应
2. 修改或者以某种方式处理正在客户端与服务端之前交互的数据流

### 使用

与使用Servlet 类似，Filter 是Java Web 提供的一个接口，开发者只需要自定义一个类来实现该接口即可

- 创建实现类MyFilter

```java
package com.xuezhang.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @description:
 * @author: 学长
 * @date: 2021/2/28 12:43
 */
public class MyFilter implements Filter {
    public MyFilter() {
        System.out.println("MyFilter通过无参构造创建对象。。。");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("MyFilter初始化。。。");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("MyFilter执行业务方法。。。");
        servletResponse.setContentType("text/html;charset=UTF-8");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("MyFilter释放对象资源。。。");
    }
}
```

- 通过web.xml配置MyFilter

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">

  <servlet>
    <servlet-name>MyServlet</servlet-name>
    <servlet-class>com.xuezhang.servlet.MyServlet</servlet-class>
  </servlet>

  <servlet-mapping>
    <servlet-name>MyServlet</servlet-name>
    <url-pattern>/myservlet</url-pattern>
  </servlet-mapping>


  <servlet>
    <servlet-name>TestServlet</servlet-name>
    <servlet-class>com.xuezhang.servlet.TestServlet</servlet-class>
  </servlet>
  <servlet-mapping>
    <servlet-name>TestServlet</servlet-name>
    <url-pattern>/test</url-pattern>
  </servlet-mapping>

  <filter>
    <filter-name>MyFilter</filter-name>
    <filter-class>com.xuezhang.filter.MyFilter</filter-class>
  </filter>
  <filter-mapping>
    <filter-name>MyFilter</filter-name>
    <url-pattern>/myservlet</url-pattern>
  </filter-mapping>
</web-app>
```

- 通过注解配置MyFilter

```java
package com.xuezhang.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @description:
 * @author: 学长
 * @date: 2021/2/28 12:43
 */
@WebFilter("/myservlet")
public class MyFilter implements Filter {
    public MyFilter() {
        System.out.println("MyFilter通过无参构造创建对象。。。");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("MyFilter初始化。。。");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("MyFilter执行业务方法。。。");
        servletResponse.setContentType("text/html;charset=UTF-8");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("MyFilter释放对象资源。。。");
    }
}
```

### 生命周期

当Tomcat 启动的时候，通过反射机制调用Filter的无参构造创建实例话对象，并执行init 方法初始化对象，doFilter 方法调用多次，当Tomcat 关闭的时候，调用destroy 方法Filter销毁对象。

### 简单应用场景

#### 登陆校验

- 创建需要校验的目标页面download.jsp

  ```jsp
  <%--
    Created by IntelliJ IDEA.
    User: xuezhang
    Date: 2021/2/27
    Time: 12:11
    To change this template use File | Settings | File Templates.
  --%>
  <%@ page contentType="text/html;charset=UTF-8" language="java" %>
  <html>
  <head>
      <title>$Title$</title>
  </head>
  <body>
  <a href="">资源1</a>
  <a href="">资源2</a>
  <a href="">资源3</a>
  </body>
  </html>
  ```

- 创建登陆页面

  ```jsp
  <%--
    Created by IntelliJ IDEA.
    User: xuezhang
    Date: 2021/2/27
    Time: 12:11
    To change this template use File | Settings | File Templates.
  --%>
  <%@ page contentType="text/html;charset=UTF-8" language="java" %>
  <html>
  <head>
      <title>登陆</title>
  </head>
  <body>
  <form action="/login" method="post">
      <input type="text" name="username">
      <input type="password" name="password">
      <input type="submit" value="登陆">
  </form>
  </body>
  </html>
  ```

- 创建登陆处理servlet

  ```java
  package com.xuezhang.servlet;
  
  import javax.servlet.ServletException;
  import javax.servlet.annotation.WebServlet;
  import javax.servlet.http.HttpServlet;
  import javax.servlet.http.HttpServletRequest;
  import javax.servlet.http.HttpServletResponse;
  import javax.servlet.http.HttpSession;
  import java.io.IOException;
  
  /**
   * @description:
   * @author: 学长
   * @date: 2021/2/28 13:23
   */
  @WebServlet("/login")
  public class LoginServlet extends HttpServlet {
      @Override
      protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          String username = req.getParameter("username");
  
          String password = req.getParameter("password");
  
          if (username.equals("admin") && password.equals("123456")){
              HttpSession httpSession = req.getSession();
  
              httpSession.setAttribute("username", username);
  
              resp.sendRedirect("download.jsp");
          }
      }
  }
  ```

- 创建过滤器

  ```java
  package com.xuezhang.filter;
  
  import javax.servlet.*;
  import javax.servlet.annotation.WebFilter;
  import javax.servlet.http.HttpServletRequest;
  import javax.servlet.http.HttpServletResponse;
  import javax.servlet.http.HttpSession;
  import java.io.IOException;
  
  /**
   * @description:
   * @author: 学长
   * @date: 2021/2/28 13:28
   */
  @WebFilter("/download.jsp")
  public class DownLoadFilter implements Filter {
      @Override
      public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
          HttpServletRequest request = (HttpServletRequest) servletRequest;
  
          HttpServletResponse response = (HttpServletResponse) servletResponse;
  
          HttpSession httpSession = request.getSession();
  
          String username = (String) httpSession.getAttribute("username");
  
          if (username == null){
              response.sendRedirect("login.jsp");
          }else {
              filterChain.doFilter(servletRequest, servletResponse);
          }
      }
  }
  ```

#### 文件上传

- 创建upload.jsp

  ```jsp
  <%--
    Created by IntelliJ IDEA.
    User: xuezhang
    Date: 2021/2/27
    Time: 12:11
    To change this template use File | Settings | File Templates.
  --%>
  <%@ page contentType="text/html;charset=UTF-8" language="java" %>
  <html>
  <head>
      <title>文件上传</title>
  </head>
  <body>
  <form action="/fileUpload" method="post" enctype="multipart/form-data">
      <input type="text" name="name"><br>
      <input type="file" name="file"><br>
      <input type="submit" value="上传">
  </form>
  </body>
  </html>
  ```

- 创建 UploadServlet

  ```java
  package com.xuezhang.servlet;
  
  import javax.servlet.ServletException;
  import javax.servlet.ServletInputStream;
  import javax.servlet.annotation.WebServlet;
  import javax.servlet.http.HttpServlet;
  import javax.servlet.http.HttpServletRequest;
  import javax.servlet.http.HttpServletResponse;
  import java.io.*;
  
  /**
   * @description:
   * @author: 学长
   * @date: 2021/2/28 14:01
   */
  @WebServlet("/upload")
  public class UploadServlet extends HttpServlet {
      @Override
      protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          // 字节流
          ServletInputStream stream = req.getInputStream();
  
          int temp = 0;
  
  //        while ((temp = stream.read()) != -1){
  //            System.out.println(temp);
  //        }
  
          //字符流
          Reader reader = new InputStreamReader(stream);
  
          //缓冲字符流
          BufferedReader bufferedReader = new BufferedReader(reader);
  
          //获取保存文件的path
          String realPath = req.getServletContext().getRealPath("file/copy.txt");
  
          // 输出到本地 获取输出流
          OutputStream outputStream = new FileOutputStream(realPath);
  
          //输入字节流
          Writer writer = new OutputStreamWriter(outputStream);
          BufferedWriter bufferedWriter = new BufferedWriter(writer);
  
          String str = "";
          while ((str = bufferedReader.readLine()) != null){
              bufferedWriter.write(str);
          }
  
          // 不要忘记关闭流
          bufferedWriter.close();
          writer.close();
          outputStream.close();
          bufferedReader.close();
          reader.close();
          stream.close();
      }
  }
  ```

使用FileUpload 组件可以将所有的请求信息解析成FileItem 对象，可以通过操作FileItem 对象来完成文件上传，面向对象的思想。

- 创建HttpUploadServlet

  ```java
  package com.xuezhang.servlet;
  
  
  import org.apache.commons.fileupload.FileItem;
  import org.apache.commons.fileupload.FileUploadException;
  import org.apache.commons.fileupload.disk.DiskFileItemFactory;
  import org.apache.commons.fileupload.servlet.ServletFileUpload;
  
  import javax.servlet.ServletException;
  import javax.servlet.annotation.WebServlet;
  import javax.servlet.http.HttpServlet;
  import javax.servlet.http.HttpServletRequest;
  import javax.servlet.http.HttpServletResponse;
  import java.io.*;
  import java.util.List;
  
  /**
   * @description:
   * @author: 学长
   * @date: 2021/2/28 15:26
   */
  @WebServlet("/fileUpload")
  public class FileUploadServlet extends HttpServlet {
      @Override
      protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          try {
              DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
  
              ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
  
              List<FileItem> fileItemList = servletFileUpload.parseRequest(req);
  
              for (FileItem fileItem: fileItemList){
                  if (fileItem.isFormField()){
                      String name = fileItem.getFieldName();
                      String value = fileItem.getString("UTF-8");
                      System.out.println(name + ":" + value);
                  }else {
                      String fileName = fileItem.getName();
  
                      long size = fileItem.getSize();
  
                      System.out.println(fileName + ":" + size + "Byte");
  
                      InputStream inputStream = fileItem.getInputStream();
  
                      String path = req.getServletContext().getRealPath("file/" + fileName);
  
                      OutputStream outputStream = new FileOutputStream(path);
  
                      int temp = 0;
  
                      while ((temp = inputStream.read()) != -1){
                          outputStream.write(temp);
                      }
  
                      outputStream.close();
                      inputStream.close();
                      System.out.println("上传成功！");
                  }
              }
  
          } catch (FileUploadException e) {
              e.printStackTrace();
          }
      }
  }
  ```

  

#### 文件下载

- 创建download.jsp

  ```jsp
  <%--
    Created by IntelliJ IDEA.
    User: xuezhang
    Date: 2021/2/28
    Time: 18:05
    To change this template use File | Settings | File Templates.
  --%>
  <%@ page contentType="text/html;charset=UTF-8" language="java" %>
  <html>
  <head>
      <title>Title</title>
  </head>
  <body>
  <a href="/download"> 文件下载 </a>
  </body>
  </html>
  ```

- 创建DownLoadServlet

  ```java
  package com.xuezhang.servlet;
  
  import javax.servlet.ServletException;
  import javax.servlet.annotation.WebServlet;
  import javax.servlet.http.HttpServlet;
  import javax.servlet.http.HttpServletRequest;
  import javax.servlet.http.HttpServletResponse;
  import java.io.FileInputStream;
  import java.io.IOException;
  import java.io.InputStream;
  import java.io.OutputStream;
  
  /**
   * @description:
   * @author: 学长
   * @date: 2021/2/28 18:06
   */
  @WebServlet("/download")
  public class DownloadServlet extends HttpServlet {
      @Override
      protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          //设置响应方式
          resp.setContentType("application/x-msdownload");
  
          String fileName = "1.png";
  
          //设置下载后的文件名
          resp.setHeader("Content-Disposition", "attachment;filename=" + fileName);
  
          //获取输出流
          OutputStream outputStream = resp.getOutputStream();
  
          //获取要下载的文件路径
          String path = req.getServletContext().getRealPath("file/qr.png");
  
          InputStream inputStream = new FileInputStream(path);
  
          int temp = 0;
  
          while ((temp = inputStream.read()) != -1){
              outputStream.write(temp);
          }
  
          outputStream.close();
          inputStream.close();
      }
  }
  ```

  