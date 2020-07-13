# Git流程

[特别注意：当中央仓库是空的时，可以将本地文件直接传入到中央仓库，若是中央仓库有文件时，只能先clone下来修改后再去进行上传操作！！！]()

# 一、单人操作

### 1.1、步骤1

> 创建库的存储位置，并运行软件

![1592291159760](https://yankang.oss-cn-beijing.aliyuncs.com/imgs/1592291159760.png)步骤2

### 1.2、步骤2：登录（第一次使用时）

> git config --global user.name "Your Name"  #用户名
> git config --global user.email "email@example.com"  #邮箱
>
> git config -l  查看信息

![1592291436256](https://yankang.oss-cn-beijing.aliyuncs.com/imgs/1592291436256.png)

> 全局文件所在位置

![1592291602333](https://yankang.oss-cn-beijing.aliyuncs.com/imgs/1592291602333.png)

### 1.3、步骤3 ：初始化

> 指令：$ git init

### 1.4、步骤4：将本地文件上传到缓存区

> 指令：$ git status	查看状态
>
> ​				$git add [文件名]()	将文件添加到缓存区
>
> ​				$git commit -m [“注释”]()	提交到远程仓库

![1592291911611](https://yankang.oss-cn-beijing.aliyuncs.com/imgs/1592291911611.png)

![1592293051643](https://yankang.oss-cn-beijing.aliyuncs.com/imgs/1592293051643.png)

### 1.5、步骤5：将文件上传到中央仓库

> 指令：$ git remote add origin [url]()	连接到中央仓库
>
> ​			  $ git push origin master		推送到中央仓库
>
> [注意：若是本身就是clone下来的文件 连接中央仓库时可以不加add]()



![1592307269239](https://yankang.oss-cn-beijing.aliyuncs.com/imgs/1592307269239.png)

![1592307309971](https://yankang.oss-cn-beijing.aliyuncs.com/imgs/1592307309971.png)

![1592296775397](https://yankang.oss-cn-beijing.aliyuncs.com/imgs/1592296775397.png)



# 二、伙伴协作（给予仓库权限）

![1592307936863](https://yankang.oss-cn-beijing.aliyuncs.com/imgs/1592307936863.png)

![1592308149914](https://yankang.oss-cn-beijing.aliyuncs.com/imgs/1592308149914.png)

> 然后合作伙伴会在邮箱中收到邀请，进而同意请求

![1592308289249](https://yankang.oss-cn-beijing.aliyuncs.com/imgs/1592308289249.png)

> 此时合作伙伴进入自己的GitHub，就会看到被分享出来的仓库

![1592309730924](https://yankang.oss-cn-beijing.aliyuncs.com/imgs/1592309730924.png)

> 此时，合作伙伴可以clone中的数据然后进行修改提交，这和个时候，合作伙伴有权限直接对主仓库进行提交

![1592310276494](https://yankang.oss-cn-beijing.aliyuncs.com/imgs/1592310276494.png)

![1592310343257](https://yankang.oss-cn-beijing.aliyuncs.com/imgs/1592310343257.png)

# 三、第三方协助（没有仓库权限）

> ​        再创建一个新用户，然后登录，拷贝仓库地址（https://github.com/xxxxxxx.git），在浏览器执行，然后点击Fork，表示拷贝之前的仓库到该用户下  

![img](https://yankang.oss-cn-beijing.aliyuncs.com/imgs/clip_image002.gif)

 

此时，相当于在GitHub中有了两个相同的仓库，一个为master，一个为Fork，然后在磁盘再创建一个目录，在目录下右键git bash 打开新窗口，进行如下操作：

 

![img](https://yankang.oss-cn-beijing.aliyuncs.com/imgs/clip_image004.gif)

 

此时C把A仓库克隆下来之后进行了修改，然后又提交到了C自己的仓库，但实际运行的是A仓库中的代码，所以C需要向A发送请求，A需要同意请求，然后合并即可。

 

C发送请求：（首先刷新C当前浏览器页面，点击New pull requst）

![img](https://yankang.oss-cn-beijing.aliyuncs.com/imgs/clip_image006.gif)

 

确认发送请求

![img](https://yankang.oss-cn-beijing.aliyuncs.com/imgs/clip_image008.gif)

 

 

再次确认发送请求

![img](https://yankang.oss-cn-beijing.aliyuncs.com/imgs/clip_image010.gif)

 

 

此时请求已经发送，A登录后查看请求，若已登录可直接刷新当前浏览器页面

 

![img](https://yankang.oss-cn-beijing.aliyuncs.com/imgs/clip_image012.gif)

点击查看请求后，同意合并请求

![img](https://yankang.oss-cn-beijing.aliyuncs.com/imgs/clip_image014.gif)

 

再次确认合并

 