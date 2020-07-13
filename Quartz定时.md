# Quartz定时

### 一、Quartz的使用

#### 1.1、导入依赖

```xml
		<dependency>
            <groupId>org.quartz-scheduler</groupId>
            <artifactId>quartz</artifactId>
            <version>2.3.2</version>
        </dependency>
```

#### 1.2、定义Job

```java

/**
 * 工作类的具体实现，即需要定时执行的某件事
 */
public class MyJob implements Job {
    @Override
    //执行
    public void execute(JobExecutionContext context) throws JobExecutionException {
        //创建工作详情
        JobDetail jobDetail = context.getJobDetail();

        //获取工作的名称
        String jobName = jobDetail.getKey().getName();//任务名
        String jobGroup = jobDetail.getKey().getGroup();//任务group
        System.out.println("Job执行，job:" + jobName +"group:" + jobGroup);
        System.out.println("打印" +new Date());
    }
}
```

#### 1.3、API测试

```java
public class TestQuartz {
    public static void main(String[] args) throws Exception{
        //创建任务调度器
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        //创建任务  并设置名称和组名
        JobDetail jobDetail = JobBuilder.newJob(MyJob.class).withIdentity("job1", "group1").build();

        //创建触发器 并设置名称和组名
        SimpleTrigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1")
                .startNow()//程序运行时，立即执行
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().
                        withIntervalInSeconds(2).//每两秒执行一次
                        withRepeatCount(3))//重复执行三次
                        //repeatForever())  //一直执行
                .endAt(new GregorianCalendar(2020, 5, 30,
                        10, 1, 1).getTime())//结束时间 ：2020年6月30日 10：01:01
                .build();
        //加入任务调度器
        scheduler.scheduleJob(jobDetail,trigger);
        //执行
        scheduler.start();

    }
}

```

#### 1.4、核心类说明

> - [Scheduler:]()	调度器。所有调度都是由它控制，是Quartz的大脑，所有的任务都由它来管理
> - [Job：]()任务，想定时执行的事情（定义业务逻辑）
> - [JobDetail：]() 基于Job，进一步包装。其中关联一个Job，并为Job指定更详细的属性，比如标识等
> - [Trigger:]() 触发器，可以指定给某个任务，指定任务的触发机制

### 二、Trigger

#### 2.1、SimpleTrigger

> 以一定的时间间隔（单位是毫秒）执行的任务。
>
> - 指定起始和截止时间（时间段）
> - 指定时间间隔、执行次数

```java
//创建触发器 并设置名称和组名
        SimpleTrigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1")
                .startNow()//程序运行时，立即执行
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().
                        withIntervalInSeconds(2).//每两秒执行一次
                        repeatForever())  //不限制执行次数
                .endAt(new GregorianCalendar(2020, 5, 30,
                        10, 1, 1).getTime())//结束时间 ：2020年6月30日 10：01:01
                .build();
```

```java
//创建触发器 并设置名称和组名
        SimpleTrigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1")
                .startNow()//程序运行时，立即执行
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().
                        withIntervalInSeconds(2).//每两秒执行一次
                        withRepeatCount(3))//重复执行三次
                .endAt(new GregorianCalendar(2020, 5, 30,
                        10, 1, 1).getTime())//结束时间 ：2020年6月30日 10：01:01
                .build();
```

#### 2.2、CronTrigger【重点】

> 适合于更复杂的任务，他支持类型于Linux Cron的语法（并且更加强大）
>
> - 指定Cron表达式即可
>
> [测试网站：](http://www.bejson.com/othertools/cronvalidate/)http://www.bejson.com/othertools/cronvalidate/

```java
public class TestCronTrigger {
    public static void main(String[] args) throws Exception{
        //创建任务调度器
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        //创建任务  并设置名称和组名
        JobDetail jobDetail = JobBuilder.newJob(MyJob.class).withIdentity("job1", "group1").build();

        //创建触发器 并设置名称和组名
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity("job", "group1")
                .withSchedule(CronScheduleBuilder.cronSchedule("*/2 * * * * ?"))//秒 分 时 天 月 星期 年（一般不写）
                .build();
        //加入任务调度器
        scheduler.scheduleJob(jobDetail,trigger);
        //执行
        scheduler.start();

    }
}

```

##### 2.2.1、Cron表达式组成

> 表达式组成：秒 分 时 天 月 星期几  [年] ，其中“年”是可选的，一般不指定
>
> - 如 “10 20 18 3 5 ？” 代表“5月3日18点20分10秒，星期几不确定”

| 位置 |           字段           |                 允许值                 |       允许的特殊字符       |
| :--: | :----------------------: | :------------------------------------: | :------------------------: |
|  1   |      秒（Seconds）       |               0~59的整数               |     , - * /   四个字符     |
|  2   |     分（*Minutes*）      |               0~59的整数               |     , - * /   四个字符     |
|  3   |     小时（*Hours*）      |               0~23的整数               |     , - * /   四个字符     |
|  4   |   日期（*DayofMonth*）   | 1~31的整数（但是你需要考虑你月的天数） | ,- * ? / L W C   八个字符  |
|  5   |     月份（*Month*）      |         1~12的整数或者 JAN-DEC         |     , - * /   四个字符     |
|  6   |   星期（*DayofWeek*）    |    1~7的整数或者 SUN-SAT （1=SUN）     | , - * ? / L C #   八个字符 |
|  7   | 年(可选，留空)（*Year*） |               1970~2099                |     , - * /   四个字符     |

##### 2.2.2、Cron表达式符号

> 表达式中可使用的特殊符号的含义如下

| 符号 | 语义                                                         |
| :--: | :----------------------------------------------------------- |
|  *   | 表示匹配该域的任意值。假如在Minutes域使用*, 即表示每分钟都会触发事件。 |
|  ？  | 只能用在DayofMonth和DayofWeek两个域。它也匹配域的任意值，但实际不会。因为DayofMonth和DayofWeek会相互影响。例如想在每月的20日触发调度，不管20日到底是星期几，则只能使用如下写法： 13 13 15 20 * ?, 其中最后一位只能用？，而不能使用*，如果使用*表示不管星期几都会触发，实际上并不是这样。 |
|  -   | 表示范围。例如在Minutes域使用5-20，表示从5分到20分钟每分钟触发一次。 |
|  /   | 表示起始时间开始触发，然后每隔固定时间触发一次。例如在Minutes域使用5/20,则意味着5分钟触发一次，而25，45等分别触发一次。 |
|  ,   | 表示列出枚举值。例如：在Minutes域使用5,20，则意味着在5和20分每分钟触发一次。 |
|  L   | 表示最后，只能出现在DayofWeek和DayofMonth域。如果在DayofWeek域使用5L,意味着在最后的一个星期四触发。 |
|  W   | 表示有效工作日(周一到周五),只能出现在DayofMonth域，系统将在离指定日期的最近的有效工作日触发事件。例如：在 DayofMonth使用5W，如果5日是星期六，则将在最近的工作日：星期五，即4日触发。如果5日是星期天，则在6日(周一)触发；如果5日在星期一到星期五中的一天，则就在5日触发。另外一点，W的最近寻找不会跨过月份 。 |
|  LW  | 这两个字符可以连用，表示在某个月最后一个工作日，即最后一个星期五。 |
|  #   | 用于确定每个月第几个星期几，只能出现在DayofWeek域。例如在4#2，表示某月的第二个星期三。 |

### 三、Spring整合Quartz【重点】

#### 3.1、导入依赖

```xml
<dependencies>
        <dependency>
            <groupId>org.quartz-scheduler</groupId>
            <artifactId>quartz</artifactId>
            <version>2.3.2</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-tx</artifactId>
            <version>5.2.6.RELEASE</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context-support</artifactId>
            <version>5.2.2.RELEASE</version>
        </dependency>
    </dependencies>
```

#### 3.2、定义Job类

```java
public class MyJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("Job执行：" + new Date());
    }
}
```

#### 3.3、配置

> [调度器：]()SchedulerFactoryBean
>
> [触发器：]()CronTriggerFactoryBean
>
> [jobDetail ：]() JobDetailFactoryBean

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!--Spring整合Quartz进行配置需要遵循以下步骤：
        1：定义工作任务的Job
        2：定义触发器Trigger，并将触发器与任务绑定
        3：定义调度器，并将Trigger注册到Scheduler
    -->

    <!--任务-->
    <bean id="jobDetail" class="org.springframework.scheduling.quartz.JobDetailFactoryBean">
        <!--设置任务名称-->
        <property name="name" value="job1"/>
        <!--设置组名-->
        <property name="group" value="group1"/>
        <!--指定具体的Job类-->
        <property name="jobClass" value="job.MyJob"/>
    </bean>

    <!--触发器-->
    <bean id="cronTrigger" class="org.springframework.scheduling.quartz.CronTriggerFactoryBean">
        <!--设置触发器名称-->
        <property name="name" value="trigger1"/>
        <!--设置触发器组名-->
        <property name="group" value="group1"/>
        <!--绑定任务-->
        <property name="jobDetail" ref="jobDetail"/>
        <!--设置触发规则  ，当前是每隔2s运行一次-->
        <property name="cronExpression" value="*/2 * * * * ?"/>
    </bean>
    <!--调度器-->
    <bean id="scheduler" class="org.springframework.scheduling.quartz.SchedulerFactoryBean">
        <property name="triggers">
            <list>
                <!--注册触发器-->
                <ref bean="cronTrigger"></ref>
            </list>
        </property>
        <!--添加quartz配置-->
        <property name="quartzProperties">
            <value>
                # 指定调度器名称，实际类型为：QuartzScheduler
                org.quartz.scheduler.instanceName = MyScheduler
                # 指定连接池
                org.quartz.threadPool.class = org.quartz.simpl.SimpleThreadPool
                # 连接池线程数量
                org.quartz.threadPool.threadCount = 11
                # 优先级
                org.quartz.threadPool.threadPriority = 5
                # 不持久化job
                org.quartz.jobStore.class = org.quartz.simpl.RAMJobStore
            </value>
        </property>
    </bean>
</beans>
```

#### 3.4、操作

##### 3.4.1、启动任务

```java
public class TestQuartz {
    public static void main(String[] args) throws Exception{
        //加载文件，调度器启动
        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("applicationContext.xml");
       
    }
}

```

##### 3.4.2、删除任务

```java
public class TestQuartz {
    public static void main(String[] args) throws Exception{
        //加载文件，调度器启动
        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("applicationContext.xml");
		//获得调度器对象
        StdScheduler scheduler = (StdScheduler) applicationContext.getBean("scheduler");
        // 删除Job
        scheduler.deleteJob(JobKey.jobKey("job1","group1"));

    }
}

```

##### 3.4.3、暂停、恢复

```java
public class TestQuartz {
    public static void main(String[] args) throws Exception{
        //加载文件，调度器启动
        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("applicationContext.xml");

       //获得调度器对象
        StdScheduler scheduler = (StdScheduler) applicationContext.getBean("scheduler");

        //暂停
        scheduler.pauseJob(JobKey.jobKey("job1","group1"));
        System.out.println("-------pause--------");
        Thread.sleep(10000);
        System.out.println("-------resume--------");
        //继续
        scheduler.resumeJob(JobKey.jobKey("job1","group1"));

    }
}

```

##### 3.4.4、批量操作

```java
public class TestQuartz {
    public static void main(String[] args) throws Exception{
        //加载文件，调度器启动
        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("applicationContext.xml");
		//获得调度器对象
        StdScheduler scheduler = (StdScheduler) applicationContext.getBean("scheduler");

        //以组的方式进行操作
        GroupMatcher<JobKey> group1 = GroupMatcher.groupEquals("group1");
        scheduler.pauseJobs(group1); // 暂停组中所有工作
        Thread.sleep(2000);
        scheduler.resumeJobs(group1); // 恢复组中所有工作
    }
}

```

