## Springmvc+PageHelper实现不同的分页效果

​	这个分页效果本来用到了bootstrap的分页插件，但是为了方便理解和查看，这里的前端并没有将样式给加上。如果可以的话可以自己将这里写的逻辑运用到bootstrap的分页插件上，这样会让你的前端页面更加美观。

### 1. 最简单普通的用法

> 简单的实现首页、上一页、下一页、尾页
>
> 前端效果：第一页的时候，首页和上一页虽然显示但是无法点击，下一页也和末页也同理

![11](https://cdn.jsdelivr.net/gh/qiao112qiao/photo//pic/20200628190048.jpg)

> 后端代码：
>
> - 使用的是端午节写的项目作为例子，

```java
//查询所有信息
@RequestMapping("list")
    public String findAll(Model model,
                          @RequestParam(defaultValue = "1")Integer pageNum,
                          @RequestParam(defaultValue = "5")Integer pageSize){
        //使用pageHelper实现分页
        PageHelper.startPage(pageNum,pageSize);
        PageInfo<Video> pageInfo = new PageInfo<>(videoService.findAll());
        //将查询到的数据存在Model对象里边
        model.addAttribute("pageInfo",pageInfo);
        return "/WEB-INF/jsp/behind/videoList.jsp";
    }
```

> 前端页面代码：简单的实现首页、上一页、下一页、尾页，以及页码的展示



```jsp
  第${pageInfo.pageNum}页/共${pageInfo.pages}页(${pageInfo.total}条)
<!--判断 如果不是第一页的话，首页和上一页是可以点击进行页面跳转的，否则a标签的 onclick 事件不生效-->
<a href="javaScript:void (0)" 
   <c:if test="${!pageInfo.isFirstPage}"> onclick="fenye(1)" </c:if>>首页</a>
<a href="javaScript:void (0)" 
   <c:if test="${!pageInfo.isFirstPage}"> onclick="fenye(${pageInfo.pageNum-1})" 	</c:if>>上一页</a>

<!--同理 判断 如果不是最后一页的话，末页和下一页是可以点击进行页面跳转的，否则a标签的 onclick 事件不生效-->
<a href="javaScript:void (0)" 
  <c:if test="${!pageInfo.isLastPage}">onclick="fenye(${pageInfo.pageNum+1})" 	</c:if>>下一页</a>
<a href="javaScript:void (0)" 
   <c:if test="${!pageInfo.isLastPage}"> onclick="fenye(1)" </c:if>>未页</a>

```

> - 前端：a标签里边的`onclick="fenye(1)"`  其中`fenye()`是一个方法,跳转的路径写在页面上太长了，不方便查看，所以这里用一个方法来实现跳转；
>
> 注意：**`href="javaScript:void (0)"`如果想实现a标签里的onclick事件的话的话是必须要写的，因为a标签会默认先执行href跳转 ，跳转之后便不会执行onclick里边的内容**

```js
//这里只实现一个分页的地址跳转  
function fenye(pageNum) {
      location.href = "${pageContext.request.contextPath}/video/findAll?pageNum="+pageNum;
 }
```



### 2.纯前端jstl标签+PageHelper有样式的实现分页

> 这种方法会造成前端页面比较冗余，也是比较麻烦
>
> 本方法实现的分页效果图如下：

![45](https://cdn.jsdelivr.net/gh/qiao112qiao/photo//pic/20200628201620.jpg)

![44](https://cdn.jsdelivr.net/gh/qiao112qiao/photo//pic/20200628201456.jpg)

![55](https://cdn.jsdelivr.net/gh/qiao112qiao/photo//pic/20200628201512.jpg)

> 后端代码：这里后端代码并没有做修改，

```java
//查询所有信息
@RequestMapping("list")
    public String findAll(Model model,
                          @RequestParam(defaultValue = "1")Integer pageNum,
                          @RequestParam(defaultValue = "5")Integer pageSize){
        //使用pageHelper实现分页
        PageHelper.startPage(pageNum,pageSize);
        PageInfo<Video> pageInfo = new PageInfo<>(videoService.findAll());
        //将查询到的数据存在Model对象里边
        model.addAttribute("pageInfo",pageInfo);
        return "/WEB-INF/jsp/behind/videoList.jsp";
   }
```



> 前端代码：由于实现的效果要好点，所以前端代码可能会稍微复杂一点

```jsp

    <a href="javaScript:void (0)" onclick="fenye(1)" >首页</a>
<!-- 如果不是第一页的话，显示当前页码减一，如果是第一页的话显示  << -->
    <c:if test="${pageInfo.pageNum > 1}">
         <a href="javaScript:void (0)" onclick="fenye(${pageInfo.pageNum-1})">
             ${pageInfo.pageNum-1}
           </a>
     </c:if>

      <c:if test="${pageInfo.pageNum <= 1}">
         <a href="javaScript:void (0)" >   <<   </a>     
      </c:if>
      <!-- 当前页码一直显示在中间的位置  << -->
      <a href="javaScript:void (0)">
             ${speakerList.pageNum}
      </a>
		<!-- 如果不是最后一页的话，显示当前页码加，如果是最后一页的话显示  >> -->
      <c:if test="${pageInfo.pageNum < pageInfo.pages}">
         <a href="javaScript:void (0)" onclick="fenye(${pageInfo.pageNum+1})">
              ${pageInfo.pageNum+1}
         </a>
       </c:if>
              
       <c:if test="${pageInfo.pageNum >= pageInfo.pages}">
            <a href="javaScript:void (0)" >  >>  </a>
       </c:if>
       <a href="javaScript:void (0)" onclick="fenye(${pageInfo.pages})">
             尾页
        </a>

```

> 分页的方法，和第一个方法一样，只用来做页面跳转的操作

```js
//这里只实现一个分页的地址跳转  
function fenye(pageNum) {
      location.href = "${pageContext.request.contextPath}/video/findAll?pageNum="+pageNum;
     }
```



### 3. 动态的展示页码，每次只显示固定数量的页码

> 这个分页可以显示所有的页码，可是这样并不符合实际，特别是当页码过多时，全部页码都展示在页面上，会让页面显得不太美观。所以，这里运用了动态的展示页码。



![14](https://cdn.jsdelivr.net/gh/qiao112qiao/photo//pic/20200628203117.jpg)



 ![11 (https://cdn.jsdelivr.net/gh/qiao112qiao/photo//pic/20200629215009.jpg)](C:\Users\Administrator\Desktop\11 (2).jpg)







![`12](https://cdn.jsdelivr.net/gh/qiao112qiao/photo//pic/20200628203247.jpg)





> 后端实现： 导航条上的第一页 navigateFirstPage; 导航条上的最后一页 navigateLastPage;
>
> 使用pageInfo中这两个属性，通过设置开始页码和结束页码来实现对前端显示页码的控制。把每页显示的页码控制在五页，或者可以自己设置显示页码的数量。



```java
//查询所有信息
@RequestMapping("list")
public String findAll(Model model,
    @RequestParam(defaultValue = "1")Integer pageNum,
    @RequestParam(defaultValue = "5")Integer pageSize){
    //使用pageHelper实现分页
    PageHelper.startPage(pageNum,pageSize);
    PageInfo<Video> pageInfo = new PageInfo<>(videoService.findAll());
  		//设置开始和结束页码，默认页面展示五个页码
       int begin = 0;
       int end = 5;    
       if (pageInfo.getPages() < 6){
            begin=1;
            end=pageInfo.getPages();
       }else{
            nums = new int[5];// 如果总页数大于等于六的话，页面展示五个页码
            begin = pageInfo.getPageNum()-2;//开始页码等于当前页码减二
            end = pageInfo.getPageNum()+2;//结束页码等于当前页码加二
            if (begin< 1){		//如果设置的开始页码小一的话，设置开始页码为1
               begin= 1;
               end = begin + 4;		
             }
       		if (end > pageInfo.getPages()){//如果结束页码大于总页数的话，结束页码等于总页数
               end = pageInfo.getPages();
               begin = end -4;
            }
        }
       	pageInfo.setNavigateFirstPage(begin);
        pageInfo.setNavigateLastPage(end);
       	//将查询到的数据存在Model对象里边
        model.addAttribute("pageInfo",pageInfo);
        return "/WEB-INF/jsp/behind/videoList.jsp";
}
```



> 前端实现：直接在前端遍历从开始页码navigateFirstPage 到结束页码 navigateLastPage数据就可以了

```jsp

         <a href="javaScript:void (0)" <c:if test="${!pageInfo.isFirstPage}"> onclick="fenye(${pageInfo.pageNum-1})" </c:if> >
               <<  </a>

        <c:forEach begin="${pageInfo.navigateFirstPage}" end="${pageInfo.navigateLastPage}"  var="i">
            <c:choose>
                <c:when test="${i == pageInfo.pageNum}">
                   <a href="javaScript:void (0)"> ${i} </a> 
                </c:when> 
                <c:otherwise>
   					<a href="javaScript:void (0)" onclick="fenye(${i})">${i}</a>  
                </c:otherwise>
            </c:choose>
        </c:forEach>   
    <a href="javaScript:void (0)" <c:if test="${!pageInfo.isLastPage}"> onclick="fenye(${pageInfo.pageNum+1})" </c:if>>
           >>  </a>
    
```



> 分页的方法，和第一个方法一样，只用来做页面跳转的操作

```js
//这里只实现一个分页的地址跳转  
function fenye(pageNum) {
      location.href = "${pageContext.request.contextPath}/video/findAll?pageNum="+pageNum;
        }
```





