local cjson= require("cjson")
local ngx_cache= ngx.shared.dis_cache

--1.常用函数
--(1).统一返回函数
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

--(2).请求体参数获取函数
function get_post_body_data()
  ngx.req.read_body()
  local data = ngx.req.get_body_data()
  if data then
    local params = cjson.decode(data)
    return params
  else
    return nil
  end
end

--(3).json判断函数
local function isJsonEmpty(json)
    if json == nil then
        return true
    elseif type(json) == "table" then
        for k, v in pairs(json) do
            return false
        end
        return true 
    else
        return false
    end
end

--2.解析并校验请求参数
local badyParams = get_post_body_data()
local json_data = cjson.encode(badyParams)
ngx.log(ngx.ERR, "step1--> json_data ",json_data)
if isJsonEmpty(json_data) then
   say_result(400,"请求参数不能为空",ngx.null)
end

--(1).疫苗活动id
local vaccineReleaseId = badyParams.vaccineReleaseId
if vaccineReleaseId == nil then
   say_result(400,"vaccineReleaseId不能为空",ngx.null)
end

--(2).数量
local openId = badyParams.openId
if openId == nil then
   say_result(400,"openId不能为空",ngx.null)
end

--(3).记录类型不能为空
local recordType = badyParams.recordType
if recordType == nil then
   say_result(400,"recordType不能为空",ngx.null)
end

