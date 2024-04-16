

# 验证码服务

````
本项目基于 https://github.com/plpcm/lua-resty-checkcode 进行修改
````



## 获取uuid(hashkey)

​	

接口地址

```
http://xxx/luaapi/checkcode?act=getuuid
```

请求方式

```
GET
```

请求参数

```
act=getuuid
```

返回参数说明(data 下面接口使用的hashkey)

```
{
errno: 0,
errmsg: "success",
data: "f640cecaaf2e0a85f9e4e84f5aa3a90f"
}
```

## 根据uuid 获取图片信息

接口地址

```
http://xxx/luaapi/checkcode?act=getcodeimg&hashkey=1e9d14b366e388b026c3be51889f8c81&MARK_TYPE=EXPRESSION
```

请求方式

```
GET
```

请求参数

```
act=getcodeimg 
hashkey  获取uuid 接口的返回值
MARK_TYPE 验证码类型
	EXPRESSION 表达式类别,验证结果为图片计算的结果
	TEXT  普通文本类型,验证结果为文本内容
	
```

返回参数说明

```
直接返回验证码图片
```



##   验证验证码

接口地址

```
http://xxx/luaapi/checkcode
```

请求方式

```
post
```

请求参数

```
act:check    方法
code:Ok7k   EXPRESSION时为图片计算结果，TEXT为普通文本类型		
hashkey:b072e1b01bc86ec8adac0e44cac81b9b 获取uuid 接口的返回值
```

返回结果

```
{"errno": 0,"errmsg": "success" }
当errorno为 0 时验证成功
```





