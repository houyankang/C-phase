# ajax实现分页和查询

## 1. ajax实现分页的好处

> 提高页面的交互效果
>
> 可以实现局部刷新数据
>
> 有效节省带宽

## 2. ajax实现分页查询的具体实现

> 这个是，点击页码实现的分页函数，点击页码后会 ajax会携带   查询的条件以及要查询的页码  向后台发送请求。后台响应的是一个jsp或者html页面，这个页面什么都不需要，只需要一个页面头以及你想要刷新的页面的表格就可以了，甚至html、body标签都不需要

```js
function fenye(pageNum){
    //form1  里边是查询的条件
    var params = $("#form1").serialize();
    $.ajax({
      url: "${pageContext.request.contextPath}/video/findAllByTerm?pageNum="+pageNum,
      data: params,
      success: function (data) {
          //这里返回的数据是一个jsp/html页面
       //form2里边是你需要刷新数据的表格，将form2里边的html，替换成后台响应给你的页面就可以了
         $("#form2").html(data)
      }
    });        
}
```

> 后台代码：
>
> 简单的查询数据，返回的是一个模板页面的路径，这里并不需要写@ResponseBody，因为这里返回的是一个页面，而不是json数据。

```java
 	@RequestMapping("findAllByTerm")
    public String findAllByTerm(Model model,@RequestParam(defaultValue = "1")Integer pageNum,QueryVo queryVo){
        System.out.println(queryVo);
        PageHelper.startPage(pageNum,5);
        PageInfo<Video> videoList = new PageInfo<>(videoService.findAllByTerm(queryVo));
        videoList = fenye(videoList);  //这里使用的是分页，设置导航栏的开始页码和结束页码
        model.addAttribute("videoList",videoList);
        return "/WEB-INF/jsp/behind/videoModel.jsp";
    }
```



> 这个是  要刷新的表格，有点多  

```jsp
    <form id="form2" action="${pageContext.request.contextPath}/video/delBatchVideos" method="post">
        <table class="table table-bordered table-hover"
               style="text-align: center;table-layout:fixed">
            <thead>
            <tr class="active">
                <th style="width:3%"><input type="checkbox" onclick="selectAll(this)"
                                            id="checkAllId"/></th>
                <th style="width:5%">序号</th>
                <th style="width:15%">名称</th>
                <th style="width:42%;">介绍</th>
                <th>讲师</th>
                <th>时长</th>
                <th style="width:7%">播放次数</th>
                <th>编辑</th>
                <th>删除</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${videoList.list}" var="video" varStatus="status">
                <tr>
                    <td><input type="checkbox" name="ids" value="${video.id}"
                               onclick="selectOne(this)"/></td>
                    <td>${status.count}</td>
                    <td>${video.title}</td>
                    <td style="overflow:hidden;white-space:nowrap;text-overflow:ellipsis;">${video.detail}</td>
                    <td>${video.speaker.speakerName}</td>
                    <td>${video.time}</td>
                    <td>${video.playNum}</td>
                    <td><a href="${pageContext.request.contextPath}/video/addVideo?id=${video.id}">
                        <span class="glyphicon glyphicon glyphicon-edit" aria-hidden="true">
                        </span>
                        </a>
                    </td>
                    <!-- js中如果使用el表达式，请用单引号包括，避免造成一些语法问题 -->
                    <td><a onclick="return delVideoById(this,'${video.id}','${video.title}')">
                        <span class="glyphicon glyphicon-trash" aria-hidden="true"</span>
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
         <div class="navbar-right" style="padding-right: 17px">
            第${videoList.pageNum}页/共${videoList.pages}页(${videoList.total}条)
            <nav aria-label="Page navigation">
                <ul class="pagination">  
                    <li>
                        <a href="javaScript:void (0)" <c:if test="${!videoList.isFirstPage}"> onclick="fenye(${videoList.pageNum-1})" </c:if> aria-label="Previous">
                            <span aria-hidden="true"><<</span>
                        </a>
                    </li>
                    <c:forEach begin="${videoList.navigateFirstPage}" end="${videoList.navigateLastPage}"  var="i">
                        <c:choose>
                            <c:when test="${i == videoList.pageNum}">
                                <li class="active">
                                    <a href="javaScript:void (0)"  aria-label="Previous">
                                        <span aria-hidden="true">${i}</span>
                                    </a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li>
                                    <a href="javaScript:void (0)" onclick="fenye(${i})" aria-label="Previous">
                                        <span aria-hidden="true">${i}</span>
                                    </a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <li>
                        <a href="javaScript:void (0)" <c:if test="${!videoList.isLastPage}"> onclick="fenye(${videoList.pageNum+1})" </c:if> aria-label="Previous">
                            <span aria-hidden="true">>></span>
                        </a>
                    </li>
                </ul>
            </nav>
        </div>
    </form>
```



> 模板页面，用于后台将此页面返回给前端，这里的模板页面，不需要html、body标签，他只是作为数据被返回给前端。

```jsp
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!--  -->
<form id="form2" action="${pageContext.request.contextPath}/video/delBatchVideos" method="post">
    <table class="table table-bordered table-hover"
           style="text-align: center;table-layout:fixed">
        <thead>
        <tr class="active">
            <th style="width:3%"><input type="checkbox" onclick="selectAll(this)"
                                        id="checkAllId"/></th>
            <th style="width:5%">序号</th>
            <th style="width:15%">名称</th>
            <th style="width:42%;">介绍</th>
            <th>讲师</th>
            <th>时长</th>
            <th style="width:7%">播放次数</th>
            <th>编辑</th>
            <th>删除</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${videoList.list}" var="video" varStatus="status">
            <tr>
                <td><input type="checkbox" name="ids" value="${video.id}"
                           onclick="selectOne(this)"/></td>
                <td>${status.count}</td>
                <td>${video.title}</td>
                <td style="overflow:hidden;white-space:nowrap;text-overflow:ellipsis;">${video.detail}</td>
                <td>${video.speaker.speakerName}</td>
                <td>${video.time}</td>
                <td>${video.playNum}</td>
                <td><a href="${pageContext.request.contextPath}/video/addVideo?id=${video.id}"><span
                        class="glyphicon glyphicon glyphicon-edit" aria-hidden="true"></span></a></td>
                <!-- js中如果使用el表达式，请用单引号包括，避免造成一些语法问题 -->
                <td><a
                        onclick="return delVideoById(this,'${video.id}','${video.title}')"><span
                        class="glyphicon glyphicon-trash" aria-hidden="true"></span></a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="navbar-right" style="padding-right: 17px">
        第${videoList.pageNum}页/共${videoList.pages}页(${videoList.total}条)
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <li>
                    <a href="javaScript:void (0)" <c:if test="${!videoList.isFirstPage}"> onclick="fenye(${videoList.pageNum-1})" </c:if> aria-label="Previous">
                        <span aria-hidden="true"><<</span>
                    </a>
                </li>
                <c:forEach begin="${videoList.navigateFirstPage}" end="${videoList.navigateLastPage}"  var="i">
                    <c:choose>
                        <c:when test="${i == videoList.pageNum}">
                            <li class="active">
                                <a href="javaScript:void (0)"  aria-label="Previous">
                                    <span aria-hidden="true">${i}</span>
                                </a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li>
                                <a href="javaScript:void (0)" onclick="fenye(${i})" aria-label="Previous">
                                    <span aria-hidden="true">${i}</span>
                                </a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <li>
                    <a href="javaScript:void (0)" <c:if test="${!videoList.isLastPage}"> onclick="fenye(${videoList.pageNum+1})" </c:if> aria-label="Previous">
                        <span aria-hidden="true">>></span>
                    </a>
                </li>
            </ul>
        </nav>
    </div>
</form>

```



