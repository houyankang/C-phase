# ssm框架中实现发送邮件

## 1.引入相关依赖

> 要想实现发送邮件，必须引入javax.mail这个依赖。

```xml
<dependency>
    <groupId>com.sun.mail</groupId>
    <artifactId>javax.mail</artifactId>
    <version>1.6.1</version>
</dependency>
```



## 2.配置mail.properties文件

> 这里的properties文件不写，直接写在applicationContext.xml中也是可以的；
>
> 但是为了后期方便，这里就直接把配置邮件接口所需要的信息直接写在properties文件中了。

```properties
#服务器主机名 smtp.xx.com，这里使用的是qq邮箱
mail.smtp.host=smtp.qq.com
## 填写你的邮箱
mail.smtp.username=******@qq.com
#客户端授权码
mail.smtp.password=**********
#编码字符
mail.smtp.defaultEncoding=utf-8
#是否进行用户名密码校验
mail.smtp.auth=true
#设置超时时间
mail.smtp.timeout=20000

```

## 3. 在applicationContext.xml文件中配置邮件接口

```xml
 	<!--导入jdbc.properties文件-->
	<!--如果想要引入多个prperties文件的话，可以使用，
		ignore-unresolvable="true"这个属性，不然会扫描不到-->
    <context:property-placeholder location="classpath:jdbc.properties,classpath:mail.properties" ignore-unresolvable="true"/>    


<!--配置邮件接口-->
    <bean id="javaMailSender" class="org.springframework.mail.javamail.JavaMailSenderImpl">
        <property name="host" value="${mail.smtp.host}"/>
        <property name="username" value="${mail.smtp.username}"/>
        <property name="password" value="${mail.smtp.password}"/>
        <property name="defaultEncoding" value="${mail.smtp.defaultEncoding}"/>
        <property name="javaMailProperties">
            <props>
                <prop key="mail.smtp.auth">${mail.smtp.auth}</prop>
                <prop key="mail.smtp.timeout">${mail.smtp.timeout}</prop>
            </props>
        </property>
    </bean>
```







## 4.在controller层直接写发送邮件的方法

> 这里是前端发送一个





```java
@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;
  	@Autowired
    private JavaMailSender javaMailSender;
	//声明一个邮件发送人
	//将邮箱信息赋给发送人
    @Value("2722680320@qq.com")
    private String emailFrom;
     /**
     *发送邮件到邮箱
     * @param user
     * @return
     */
    @RequestMapping("sendEmail")
    @ResponseBody
    public String sendEmail(User user,HttpServletRequest request){
        HttpSession httpSession = request.getSession();
        User result = userService.validateEmail(user);
        if (null == result){
            return  "hasNoUser";
        }else {
            //生成六位数验证码
            String captcha = String.valueOf(new Random().nextInt(899999) + 100000);
            httpSession.setAttribute("captcha",captcha);
            SimpleMailMessage message = new SimpleMailMessage();
            //发件人的邮箱地址
            message.setFrom(emailFrom);
            //收件人的邮箱地址
            message.setTo(user.getEmail());
            //邮件主题
            message.setSubject("验证邮件");
            //邮件内容
            message.setText("内容："+captcha);
            //发送邮件
            javaMailSender.send(message);
            if (true){
                return "success";
            }
        }
        return "error";
    }
}
```

