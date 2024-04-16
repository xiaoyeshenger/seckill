local cjson = require "cjson"

local M = {}

--1.统一返回函数
function M.say_result(code,msg,data)
  local jsonObject = {
    code = code,
    msg = msg,
    data = data
  }
  local json_data = cjson.encode(jsonObject)
  ngx.say(json_data);
  ngx.exit(ngx.HTTP_OK)
end

--2.获取当前文件的绝对路径
function script_path()
    local str = debug.getinfo(2,"S").source:sub(2)
    return str:match("(.*/)")
end

return M

