@[TOC](%E6%96%87%E7%AB%A0%E7%9B%AE%E5%BD%95)

---

# 前言
韩老师javaweb阶段的家具商城（之后写ssm还是用的这个页面）
`jsp+mvc模式+servlet+druid连接池+ThreadLocal+mysql实现卖家居的网上商城`
开发环境：
服务器：tomcat 9.0
数据库：mysql5.7
开发软件：idea2022.1.1、eclipse2022.9（都可运行）
开发技术：
前端：HTML、CSS、JavaScript、jQuery、jstl等
后端：jsp、servlet等

这 里 我 们 使 用 到 原 生 的 Servlet/ 过 滤 器 ， 后 台 是 经 典 的 分 层 结 构 
WEB-Service-DAO-bean

该系统分为前台和后台，普通用户可实现前台所有功能，后台功能需要管理员登录进入后台页面才可进行。
![image.png](https://cdn.nlark.com/yuque/0/2023/png/35399149/1683709456180-658af085-aef5-4601-b48c-8a420d80105c.png#averageHue=%23f5f3f0&clientId=u6148a799-bf47-4&from=paste&height=684&id=u140067c2&originHeight=855&originWidth=491&originalType=binary&ratio=1.25&rotation=0&showTitle=false&size=67865&status=done&style=none&taskId=u4c847149-3121-4a25-9951-57c679e78a8&title=&width=392.8)
一图胜千言

## 项目结构
![image.png](https://cdn.nlark.com/yuque/0/2023/png/35399149/1683796076149-ed0deb9c-96e5-4445-ae74-8c0386b811a2.png#averageHue=%23f6f6f6&clientId=udcd2a901-b1eb-4&from=paste&height=330&id=u0aab7c98&originHeight=412&originWidth=577&originalType=binary&ratio=1.25&rotation=0&showTitle=false&size=15977&status=done&style=none&taskId=uf5034ffe-b290-4851-8882-93e0285459c&title=&width=461.6)
![image.png](https://cdn.nlark.com/yuque/0/2023/png/35399149/1683794230656-38cc5bf7-de75-417f-b53c-490e2f6d830a.png#averageHue=%23f9f8f8&clientId=udcd2a901-b1eb-4&from=paste&height=773&id=ud734ac7a&originHeight=966&originWidth=493&originalType=binary&ratio=1.25&rotation=0&showTitle=false&size=44568&status=done&style=none&taskId=uba8185cf-9250-4ef7-b539-6fcea15b2ec&title=&width=394.4)

---

# 成果展示
首页
![image.png](https://cdn.nlark.com/yuque/0/2023/png/35399149/1683793850749-3dc17ef1-4736-4422-8a17-92ccf84b852e.png#averageHue=%23a19887&clientId=udcd2a901-b1eb-4&from=paste&height=1524&id=u1fbe60d3&originHeight=1905&originWidth=1918&originalType=binary&ratio=1.25&rotation=0&showTitle=false&size=939831&status=done&style=none&taskId=uf9b9db39-30cf-4074-9e6c-de5054095be&title=&width=1534.4)
用户注册/登录
![image.png](https://cdn.nlark.com/yuque/0/2023/png/35399149/1683793903565-5f22f1c2-a947-4ee5-9ec7-e4c40c53904d.png#averageHue=%23efeeee&clientId=udcd2a901-b1eb-4&from=paste&height=838&id=u0cf44d7a&originHeight=1048&originWidth=1920&originalType=binary&ratio=1.25&rotation=0&showTitle=false&size=81124&status=done&style=none&taskId=u35e07545-fdd8-4fe7-8d06-3198ec3bcab&title=&width=1536)
![image.png](https://cdn.nlark.com/yuque/0/2023/png/35399149/1683793915938-b32f9c5e-2234-4f6e-a178-504d5dd141ef.png#averageHue=%23efefef&clientId=udcd2a901-b1eb-4&from=paste&height=838&id=u42f718b8&originHeight=1048&originWidth=1920&originalType=binary&ratio=1.25&rotation=0&showTitle=false&size=97502&status=done&style=none&taskId=u5c8b1eeb-0d29-4cbd-bb60-3b603a0198a&title=&width=1536)
管理员登录
![image.png](https://cdn.nlark.com/yuque/0/2023/png/35399149/1683793940208-3f109d58-e374-43ac-b8cd-a15024d729e6.png#averageHue=%23efefee&clientId=udcd2a901-b1eb-4&from=paste&height=838&id=u02d31274&originHeight=1048&originWidth=1920&originalType=binary&ratio=1.25&rotation=0&showTitle=false&size=78459&status=done&style=none&taskId=u3a1d8edc-4131-4561-869f-e5d8675119a&title=&width=1536)
信息提示页
![image.png](https://cdn.nlark.com/yuque/0/2023/png/35399149/1683793964141-2a6703ef-9727-46da-94a6-06340f27a9d0.png#averageHue=%23c1af82&clientId=udcd2a901-b1eb-4&from=paste&height=838&id=u27adb779&originHeight=1048&originWidth=1920&originalType=binary&ratio=1.25&rotation=0&showTitle=false&size=90674&status=done&style=none&taskId=uc2c265c6-80c3-4964-be34-1bb3cfb3649&title=&width=1536)
后台页面（目前只有家居管理功能所以没有做菜单）
![image.png](https://cdn.nlark.com/yuque/0/2023/png/35399149/1683794020549-bc120b26-1cd4-466f-a197-f495879e9caf.png#averageHue=%23a8a481&clientId=udcd2a901-b1eb-4&from=paste&height=838&id=uc8a00930&originHeight=1048&originWidth=1920&originalType=binary&ratio=1.25&rotation=0&showTitle=false&size=87122&status=done&style=none&taskId=u65bdf1e9-0fcc-49a8-b5b8-c632d84bc4d&title=&width=1536)
家居管理页面
![image.png](https://cdn.nlark.com/yuque/0/2023/png/35399149/1683794071891-dad933fc-9d08-44a2-8019-c9ea9e4ce239.png#averageHue=%23ececeb&clientId=udcd2a901-b1eb-4&from=paste&height=1729&id=uaa4f9a3d&originHeight=2161&originWidth=1918&originalType=binary&ratio=1.25&rotation=0&showTitle=false&size=315866&status=done&style=none&taskId=ub9267215-7b77-4677-98b7-3cdd4089612&title=&width=1534.4)
修改家居页面
![image.png](https://cdn.nlark.com/yuque/0/2023/png/35399149/1683794123397-9aea5217-95b2-4f33-be96-666ad962a4f7.png#averageHue=%23ececeb&clientId=udcd2a901-b1eb-4&from=paste&height=838&id=uc53aeb10&originHeight=1048&originWidth=1920&originalType=binary&ratio=1.25&rotation=0&showTitle=false&size=100955&status=done&style=none&taskId=u3790f9d0-53d5-4c8f-90cc-86f40ae546c&title=&width=1536)
购物车页面
![image.png](https://cdn.nlark.com/yuque/0/2023/png/35399149/1683796421442-deadcaf3-556a-4fab-944d-31cad33dd5a2.png#averageHue=%23edeceb&clientId=ubaac4d14-d442-4&from=paste&height=838&id=u6cb5a410&originHeight=1048&originWidth=1920&originalType=binary&ratio=1.25&rotation=0&showTitle=false&size=165331&status=done&style=none&taskId=ubed36493-dd53-4a45-84e8-bb303835011&title=&width=1536)
## 完成品项目链接

[github](https://github.com/1072344372/lhjiaju)

# 一、项目部署
## 下载项目
## 运行数据库
## 1、idea导入



## 2、eclipse导入

# 二、

# 三 、心得
个人比较满意的几点
### 1、使用到了设计模式

- 1 工厂模式

使用了DaoFactory对dao进行管理。

- 2 模板模式

    为了解决每个功能都使用一个servlet造成了servlet太多。第一次使用了隐藏域hiddin和if else if来简化，后面使用了模板模式+反射+动态绑定。将对于一个表操作的多个功能合成到了一个servlet中。大大减少了代码量以及提高了相关功能的内聚性。

- 3 单例模式

    为了保持在多个表的操作中使用的是同一个dao对象，使用了单例模式中的懒汉模式+静态内部类。
先把DaoFactory的构造器设置成私有的，在通过内部类来创造这个DaoFactory获取对应的DAO对象。
### 2、事务管理
**Filter + ThreadLocal+druid完成对事务的管理**
tomcat底层使用的是线程池技术，使用druid数据库连接池获取连接并且放到ThreadLocal中，ThreadLocal是单个线程内实现数据共享的技术。首先设置事务的不自动提交，把ThreadLocal中存放的连接取出来进行对多个表的操作时，操作完再把这个连接从ThreadLocal中清除掉（threadloacl 长时间持有该连接，会影响效率）。当遇到异常时进行回滚并关闭连接（在数据库连接池技术中，close 不是真的断掉连接，而是把使用的Connection对象放回连接池）。
### 3、
### 4、


---

# 总结

`提示：这里对文章进行总结：`
