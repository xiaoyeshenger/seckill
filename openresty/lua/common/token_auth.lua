local jwt = require "resty.jwt"
local cjson = require "cjson"

--统一返回结构
function say_result(code,msg,data)
  local jsonObject = {
    code = code,
    msg = msg,
    data = data
  }
  local json_data = cjson.encode(jsonObject)
  ngx.say(json_data);
  ngx.exit(ngx.HTTP_OK)
end


--1.判断token是否存在
local token = ngx.req.get_headers()["Authorization"]
--ngx.log(ngx.ERR, "token = ",token)
if token == nil then
  say_result(400,"token不能为空",ngx.null)
end

--2.校验token
--注意:后端项目比如springboot中jwt使用的是io.jsonwebtoken.jjwt-api包的话.springboot中配置的secret为base64加密后的字符串,而此处需要decode变回原文字符串
local secret = "VappYmNjNzZjODhmMzk2ZDk0NTFiNzA5MmYxNzhhZGI0ZGM2NDdmNzBlMzE3Nzg2NzE1NWE3MGQ5ZWFmMzQ4ODk1MDcyNzA3ZTkyM2VlOTdhZGJkNDYxM2QwYzU3NjnVapp"
local jwt_obj = jwt:verify(secret, token)
if jwt_obj.verified == false then
  say_result(400,"token校验失败",ngx.null)
end

--3.token校验没问题,不需要做任何操作，直接放行请求后端接口
--local jwt_payload = jwt_obj.payload
--say_result(200,"token校验通过",jwt_obj)
