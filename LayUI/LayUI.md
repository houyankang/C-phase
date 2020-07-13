# LayUI

### 一、引言

------

#### 1.1介绍

> 官网：https://www.layui.com/
>
> 在官网首页，可以很方便的下载LayUI

> LayUI是一款经典模块化的UI框架，我们只需要定义简单的HTML、CSS、JS即可实现很复杂的前端效果。
>
> 使得前端页面的制作变得更加简单

### 二、环境搭建

------

#### 2.1、下载

> 在官网即可完成下载

#### 2.2、导入依赖

> 下载LayUI解压后，将其中的layui目录导入项目中

![image-20200701150903568](https://yankang.oss-cn-beijing.aliyuncs.com/imgs/image-20200701150903568.png)

![image-20200701150932415](https://yankang.oss-cn-beijing.aliyuncs.com/imgs/image-20200701150932415.png)

![image-20200701151002354](https://yankang.oss-cn-beijing.aliyuncs.com/imgs/image-20200701151002354.png)

### 三、页面元素

------



#### 3.1、布局

> 响应式栅格布局，每行分为12等分

```html
<!-- layui-container居中显示有固定尺寸；layui-fluid占满行宽 -->
<!--<div class="layui-container">-->
<div class="layui-container">
    <div class="layui-row">
        <div class="layui-col-md9 layui-col-lg6 layui-bg-orange">
            你的内容 9/12
        </div>
        <div class="layui-col-md3 layui-col-lg6 layui-bg-gray">
            你的内容 3/12
        </div>
    </div>

    <div class="layui-row">
        <div class="layui-col-xs6 layui-col-sm6 layui-col-md4 layui-col-lg3">
            移动：6/12 | 平板：6/12 | 桌面：4/12;
        </div>
        <div class="layui-col-xs6 layui-col-sm6 layui-col-md4 layui-col-lg3">
            移动：6/12 | 平板：6/12 | 桌面：4/12;
        </div>
        <div class="layui-col-xs4 layui-col-sm12 layui-col-md4 layui-col-lg3">
            移动：4/12 | 平板：12/12 | 桌面：4/12;
        </div>
        <div class="layui-col-xs4 layui-col-sm7 layui-col-md8 layui-col-lg3">
            移动：4/12 | 平板：7/12 | 桌面：8/12;
        </div>
        <div class="layui-col-xs4 layui-col-sm5 layui-col-md4 layui-col-lg3">
            移动：4/12 | 平板：5/12 | 桌面：4/12;
        </div>
    </div>
</div>

```

#### 3.2、字体图标

> [class="layui-icon"]() 具体的图标样式

```html
<script src="layui/layui.js"></script>
<link rel="stylesheet" href="layui/css/layui.css">

<body>
	<i class="layui-icon layui-icon-heart-fill" style="font-size: 30px; color: red;"></i>
	<i class="layui-icon layui-icon-heart" style="font-size: 30px; color: #1E9FFF;"></i>
</body>
```

#### 3.3、按钮

> [class="layui-btn	主题	样式"]()

```html
<body>
<button type="button" class="layui-btn">标准的按钮</button>
<a href="http://www.layui.com" class="layui-btn">可跳转的按钮</a>
<a href="http://www.layui.com" class="layui-btn layui-btn-primary">主题的按钮</a>
<a href="http://www.layui.com" class="layui-btn layui-btn-primary layui-btn-sm">主题的按钮</a>
<a href="http://www.layui.com" class="layui-btn layui-btn-primary layui-btn-radius ">圆角的按钮</a>
<a href="http://www.layui.com" class="layui-btn layui-btn-primary layui-btn-sm  layui-btn-radius ">
    <i class="layui-icon layui-icon-heart-fill" style="font-size: 30px; color: #1E9FFF;"></i>
    带图标的按钮
</a>

</body>
```

#### 3.4、表单

> [class="layui-form">]()

```html
<form class="layui-form" action="demo1.html">
    <div class="layui-form-item">
        <label class="layui-form-label">输入框</label>
        <div class="layui-input-block">
            <input type="text" name="title" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">密码框</label>
        <div class="layui-input-inline">
            <input type="password" name="password" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
        </div>
        <div class="layui-form-mid layui-word-aux">辅助文字</div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">选择框</label>
        <div class="layui-input-block">
            <select name="city" lay-verify="required">
                <option value="">请选择城市</option>
                <option value="0">北京</option>
                <option value="1">上海</option>
                <option value="2">广州</option>
                <option value="3">深圳</option>
                <option value="4">杭州</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">复选框</label>
        <div class="layui-input-block">
            <input type="checkbox" name="like[write]" title="写作">
            <input type="checkbox" name="like[read]" title="阅读" checked>
            <input type="checkbox" name="like[dai]" title="发呆">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">开关</label>
        <div class="layui-input-block">
            <input type="checkbox" name="switch" lay-skin="switch">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">单选框</label>
        <div class="layui-input-block">
            <input type="radio" name="sex" value="男" title="男">
            <input type="radio" name="sex" value="女" title="女" checked>
        </div>
    </div>
    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">文本域</label>
        <div class="layui-input-block">
            <textarea name="desc" placeholder="请输入内容" class="layui-textarea"></textarea>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit lay-filter="formDemo">立即提交</button>
            <button type="button" class="layui-btn" id="test1">
                <i class="layui-icon">&#xe67c;</i>上传图片
            </button>
        </div>
    </div>
</form>
```

[注意：必须要导入以下form模块，才能保证表单正常渲染]()

```html
<script>
    // 必须要导入form模块，才能保证表单正常渲染
    layui.use('form', function(){
        var form = layui.form;
        //监听提交
        form.on('submit(formDemo)', function(data){// data就是表单中的所有数据
            layer.msg(JSON.stringify(data.field));
            return true;//true表示跳转到action对应的路径，false表示不跳转
        });
    });
</script>
```

#### 3.5、数据表格

##### 3.5.1、动态表格

```html

<body>
	<table id="demo" lay-filter="test"></table>
</body>

<script>
    // 必须要导入 table模块 layui.use('table',...)
    layui.use('table', function(){
        var table = layui.table;
        // 为表格填充数据
        table.render({
            elem: '#demo'
            ,height: 312
            ,url: 'json/data.json' //获取数据
            ,page: {limit:5//每页显示1条(向后台传值，每页显示条数)
                ,limits:[1,2,3] //可选每页条数
                ,first: '首页' //首页显示文字，默认显示页号
                ,last: '尾页'
                ,prev: '<em>←</em>' //上一页显示内容，默认显示 > <
                ,next: '<i class="layui-icon layui-icon-next"></i>'
                ,layout:['prev', 'page', 'next','count','limit','skip','refresh'] //自定义分页布局
            } //page为开启分页
            ,cols: [[ //表头
                {field:'id', width:80, title: 'ID', sort: true}
                ,{field:'username', width:80, title: '用户名'}
                ,{field:'sex', width:80, title: '性别', sort: true}
                ,{field:'city',  title: '城市'} //没定义宽度则占满剩余所有宽度，都不定义则所有列均分
                ,{field:'score',width:80, title: '评分', sort: true}
            ]]
        });

    });
</script>
```

> [数据格式如下：]()
>
> - code：0，代表查询成功，为1时，会显示msg中的内容
>
> - count：是为分页准备的，共有多少条数据
>
> [可以通过一个json文件模拟获取json数据，不用走controller]()

```js
{"msg":"no data",
  "code":0,
  "data":[{"id":1,"username":"shine1","sex":"男","city":"保定","score":100},
    {"id":2,"username":"shine2","sex":"女","city":"石家庄","score":100},
    {"id":3,"username":"shine3","sex":"男","city":"邢台","score":100},
  "count":100}
```

![image-20200701154136913](https://yankang.oss-cn-beijing.aliyuncs.com/imgs/image-20200701154136913.png)

##### 3.5.2、分页参数

> 分页条细节定制

```html

<body>
<table id="demo" lay-filter="test"></table>
</body>

<script>
    // 必须要导入 table模块 layui.use('table',...)
    layui.use('table', function(){
        var table = layui.table;
        // 为表格填充数据
        table.render({
            elem: '#demo'
            ,toolbar:true
            ,height: 312
            ,url: 'json/data.json' //获取数据
            ,page: {limit:5//每页显示1条(向后台传值，每页显示条数)
                ,limits:[1,2,3] //可选每页条数
                ,first: '首页' //首页显示文字，默认显示页号
                ,last: '尾页'
                ,prev: '<em>←</em>' //上一页显示内容，默认显示 > <
                ,next: '<i class="layui-icon layui-icon-next"></i>'
                ,layout:['prev', 'page', 'next','count','limit','skip','refresh'] //自定义分页布局
            } ,
    });

    });
</script>
```

##### 3.5.3、显示工具栏

> 右上角工具按钮	[toolbar:true]()

```html

<body>
	<table id="demo" lay-filter="test"></table>
</body>

<script>
    // 必须要导入 table模块 layui.use('table',...)
    layui.use('table', function(){
        var table = layui.table;
        // 为表格填充数据
        table.render({
            elem: '#demo'
            ,toolbar:true
            ,height: 312
            ,url: 'json/data.json' //获取数据
            ,page: {......} ,//分页
            ,cols: [[ .....]]//表头
   		 });

    });
</script>
```

##### 3.5.4、操作按钮

> 为每行增加操作按钮	

```html
<script>
    // 必须要导入 table模块 layui.use('table',...)
    layui.use('table', function(){
        var table = layui.table;
        // 为表格填充数据
        table.render({
            elem: '#demo'
            ,toolbar:true
            ,height: 312
            ,url: 'json/data.json' //获取数据
            ,page: {limit:5//每页显示1条(向后台传值，每页显示条数)
                ,limits:[1,2,3] //可选每页条数
                ,first: '首页' //首页显示文字，默认显示页号
                ,last: '尾页'
                ,prev: '<em>←</em>' //上一页显示内容，默认显示 > <
                ,next: '<i class="layui-icon layui-icon-next"></i>'
                ,layout:['prev', 'page', 'next','count','limit','skip','refresh'] //自定义分页布局
            } //开启分页
            ,cols: [[ //表头
                {field:'id', width:80, title: 'ID', sort: true}
                ,{field:'username', width:80, title: '用户名'}
                ,{field:'sex', width:80, title: '性别', sort: true}
                ,{field:'city',  title: '城市'} //没定义宽度则占满剩余所有宽度，都不定义则所有列均分
                ,{field:'score',width:80, title: '评分', sort: true}
                
                //设置操作按钮
                ,{field:"right",title:"操作",toolbar: '#barDemo'}
            ]]
        });


</script>

<!-- 如下script可以定义在页面的任何位置 -->
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
```

##### 3.5.5、操作按钮回调

> 按钮的单击事件

```html

<body>
	<table id="demo" lay-filter="test"></table>
</body>


<script>
//注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
    table.on('tool(test)', function(obj){
        var data = obj.data; //获得当前行数据
        //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var layEvent = obj.event;
        var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）
        if(layEvent === 'del'){ //删除
            layer.confirm('真的删除行么', function(index){
                // 向服务端发送删除请求
                // $.ajax(
                //     url:"deleteUser"
                //     ...
                // )
                // 此处可以发送ajax
                obj.del(); //删除对应行（tr）的DOM结构
                layer.close(index);
            });
        } else if(layEvent === 'edit'){ //编辑
            // 向服务端发送更新请求
            // 同步更新缓存对应的值
            obj.update({
                username: 'shine001',
                city: '北京',
                sex:'女',
                score:99});
        }
    });

    });
</script>

```

#### 3.6、导航

> 导航条
>
> -  [class="layui-nav"]()	水平导航条
> -  [class="layui-nav  layui-nav-tree"]()  垂直导航条

```html
<!-- 导入css和js -->
<script src="layui/layui.js"></script>
<link rel="stylesheet" href="layui/css/layui.css">

<body>
    <ul class="layui-nav layui-nav-tree" lay-filter="">
        <li class="layui-nav-item"><a href="">最新活动</a></li>
        <li class="layui-nav-item layui-this"><a href="">产品</a></li>
        <li class="layui-nav-item"><a href="">大数据</a></li>
        <li class="layui-nav-item">
            <a href="javascript:void(0);">解决方案</a>
            <dl class="layui-nav-child"> <!-- 二级菜单 -->
            <dd><a href="">移动模块</a></dd>
            <dd><a href="">后台模版</a></dd>
            <dd><a href="">电商平台</a></dd>
        </dl>
        </li>
        <li class="layui-nav-item"><a href="">社区</a></li>
    </ul>

</body>

<script>
    //注意：导航 依赖 element 模块，否则无法进行功能性操作
    layui.use('element', function(){});
</script>
```

#### 3.7、动画

> LayUI提供了动画支持

| 样式类                 | 描述             |
| :--------------------- | ---------------- |
| layui-anim-up          | 从最底部往上滑入 |
| layui-anim-upbit       | 微微往上滑入     |
| layui-anim-scale       | 平滑放大         |
| layui-anim-scaleSpring | 弹簧式放大       |
| layui-anim-fadein      | 渐现             |
| layui-anim-fadeout     | 渐隐             |
| layui-anim-rotate      | 360度旋转        |
| 追加：layui-anim-loop  | 循环动画         |

```html
<!-- 导入css和js -->
<script src="layui/layui.js"></script>
<link rel="stylesheet" href="layui/css/layui.css">

<body>

    <!-- 整个div会在页面显示时，以特定动画显示出来 -->
    <div class="layui-anim layui-anim-up" style="height: 100px">aa</div>

    <!-- 额外添加样式类：layui-anim-loop 使得动画循环运行 -->
    <div class="layui-anim layui-anim-rotate layui-anim-loop"
         style="text-align:center;line-height: 100px;margin-left:50px;height: 100px;width:100px">bb</div>
    
</body>
```

### 四、内置模块

------

#### 4.1、layer

##### 4.1.1、弹窗方法

> 弹窗 [msg()、alert()、confirm()]()

```html
<!-- 导入css和js -->
<script src="layui/layui.js"></script>
<link rel="stylesheet" href="layui/css/layui.css">

<body>

<div id="testmain" style="display:none;padding:10px; height: 173px; width: 275px;">
    hello world!
</div>

<input type="button" value="按钮">

</body>

<script>
    // 导入 layer模块
    layui.use(["layer"],function(){
        var layer = layui.layer;
        layer.msg("hello world!!");
        layer.msg("确定吗？",{btn:["确定！","放弃！"],
           yes:function(i){layer.close(i);layer.msg("yes!!!")},
            btn2:function(i){layer.close(i);layer.msg("no!!!")},}
        );
    });
</script>
```



```html
<script>
    // 导入 layer模块
    layui.use(["layer"],function(){
        var layer = layui.layer;
        //0-6 7种图标  0:warning  1:success  2:error  3:question  4:lock  5:哭脸  6：笑脸
        layer.alert("alert弹框蓝",
           {title:'alert',icon:6 },
            function(){//点击“确定”按钮时的回调
                layer.msg("好滴");
            }
        );
    });
</script>
```



```html
<script>
    // 导入 layer模块
    layui.use(["layer"],function(){
        var layer = layui.layer;
        layer.confirm("hello world~",
            {shade:false,icon:3,btn:["好滴","不行"]},
            function(){layer.msg("好滴！");},
            function(){layer.msg("不行！")}
    });
</script>

```



##### 4.1.2、弹窗属性

> - type弹窗类型，可选值0-4
> - title弹窗标题，可选值text/array
> - content弹窗内容，可选值text/html/dom

```html
<body>

    <div id="testmain" style="display:none;padding:10px; height: 173px; width: 275px;">
        hello world!
    </div>

    <input type="button" value="按钮">

</body>


<script>
    layui.use(["layer"],function(){
        layer = layui.layer;
        // layer.open({
        //     type:1,// 消息框，没有确定按钮
        //     title:["hello","padding-left:5px"], // 标题，及标题样式
        //     content:layui.$("#testmain"), // dom格式
        //     offset:'rb',//可以在右下角显示
        //     shade:true //是否遮罩
        // });

        layer.open({
            type:2,// iframe加载，需要一个url
            content:"demo1.html"
        });
    });
</script>
```

#### 4.2、layDate

> 日期框

```html
<!-- 导入css和js -->
<script src="layui/layui.js"></script>
<link rel="stylesheet" href="layui/css/layui.css">

<body>

    <form class="layui-form layui-form-pane" action="" method="post">
        <!-- layui-form-item 一个输入项-->
        <div class="layui-form-item">
            <label class="layui-form-label">生日</label>
            <!-- layui-input-block 输入框会占满除文字外的整行 -->
            <div class="layui-input-block">
                <input readonly id="birth" type="text" name="birth" placeholder="请选择生日日期" autocomplete="off" class="layui-input">
            </div>
        </div>
    </form>

</body>

<script>
    layui.use(["laydate","form"],function(){
        var laydate = layui.laydate;
        //执行一个laydate实例
        laydate.render({
            elem: '#birth', //指定元素
            format:'yyyy/MM/dd',
            //value:'2012/12/12' //默认值
            value:new Date() //默认值
        });
    });
</script>
```

#### 4.3、upload

> 上传按钮

##### 前端页面

```html
<!-- 导入css和js -->

<script src="layui/layui.js"></script>

<link rel="stylesheet" href="layui/css/layui.css">

<body>

    <form class="layui-form layui-form-pane" action="" method="post">


        <div class="layui-form-item">
            <div class="layui-input-block">
                <button type="button" class="layui-btn" id="test1">
                    <i class="layui-icon">&#xe67c;</i>上传图片
                </button>
            </div>
        </div>


​    </form>

</body>

<script>
    layui.use(['upload','layer'], function(){
        var upload = layui.upload;
        //执行实例
        var uploadInst = upload.render({
            elem: '#test1' //绑定元素
            ,url: 'upload' //上传接口
            ,accept:'images' // file代表所有文件，默认是images代表图片
            ,size:100 // 文件最大100kb
            ,done: function(res){
                //上传完毕回调
                layui.layer.msg("ok");
            }
            ,error: function(){
                //请求异常回调
                layui.layer.msg("error");
            }
        });
    });
</script>


```

##### 后台页面

```java
 @RequestMapping("upload")
    public Map<String,Object> upload(@RequestParam("file") MultipartFile photo, Model model, HttpServletRequest request)throws Exception{
        //request.setCharacterEncoding("utf-8");
        //获取上传文件的名称
        String filename = photo.getOriginalFilename();
		
		filename = new String(filename.getBytes("iso-8859-1"),"utf-8);	//手动解码
		
        model.addAttribute("filename",filename);
        System.out.println(filename);
        //修改上传文件的名称
        //filename+= UUID.randomUUID().toString().replace("-","");

        //上传
        //设置上传路径
        String path = "D:\\server\\apache-tomcat-8.5.31\\webapps\\upload\\";
            try {
            photo.transferTo(new File(path,filename));
        } catch (IOException e) {
            e.printStackTrace();
        }

        HashMap<String, Object> map = new HashMap<>();
        map.put("msg","ok");
        map.put("code","1");

        return map;
    }
```



#### 4.4、carousel

> 轮播图

```html
<!-- 导入css和js -->
<script src="layui/layui.js"></script>
<link rel="stylesheet" href="layui/css/layui.css">

<body>
    <div class="layui-carousel" id="test1">
        <div carousel-item style="text-align: center;line-height: 280px">
            <div>条目1</div>
            <div>条目2</div>
            <div>条目3</div>
            <div>条目4</div>
            <div>条目5</div>
        </div>
    </div>

</body>

<script>
    layui.use(['carousel'], function(){
        var carousel = layui.carousel;
        //建造实例
        carousel.render({
            elem: '#test1'
            ,width: '100%' //设置容器宽度
            ,arrow: 'always' //始终显示箭头
        });
    });
</script>
```

