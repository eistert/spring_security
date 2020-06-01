## spring_security

    spring-security-core:核心业务逻辑
    spring-security-browser:浏览器安全特定代码
    spring-security-app:app相关特定代码
    spring-security-demo:样例程序
    
## RESTful API
    查询  /user?name =tom     GET
    详情  /user/1             GET
    创建  /user               POST
    修改  /user/1             PUT
    删除  /user/1             DELETE
    
     1.用URL描述资源；
     2.使用HTTP方法描述行为，使用HTTP状态码来表示不同的结果；
     3.使用json交互数据；
     5.RESTful只是一种风格，并不是强制的标准；
    
## JsonPath gitHub 网址
    
    
    
    
    
    
## filter interceptor aspect
    filter:性能监控
    interceptor:获取方法所属的的类名，方法名
    aspect:
        性能监控，获取原始的http请求和响应信息，拿不到真正处理方法的信息；
        获取方法所属的类名、方法名，获取原始的http请求和响应信息及真正处理方法的信息，拿不到调用方法
        参数的值；
        获取不到原始的http请求和响应信息，能获取真正处理方法的信息及处理方法的参数的值
        
    三者之间的顺序：
       filter->interceptor->controllerAdvice->aspect->controller