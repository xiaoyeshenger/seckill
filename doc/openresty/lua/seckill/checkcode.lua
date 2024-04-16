
local checkcode = require("cmcheckcode")
local method = ngx.req.get_method()


local cache_code = ngx.shared.checkcode

local args = nil

if "GET" == method then
    args = ngx.req.get_uri_args()
elseif "POST" == method then
    ngx.req.read_body()
    args = ngx.req.get_post_args()
end

local act = args["act"] or ''
local hashkey = args["hashkey"] or ''


function get_from_cache(key)
	local value = cache_code:get(key)
	return value
end

function set_to_cache(key, value, exptime)
	if not exptime then
		exptime = 0 
	end 

	local succ, err, forcible = cache_code:set(key, value, exptime)

	return succ
end

if act == 'getuuid' then

	local hash = checkcode.getmd5key()
	set_to_cache(hash, '', 300) -- remain 5min
	ngx.say('{"errno":0,"errmsg": "success","data":"' .. hash .. '"}')
	ngx.exit(ngx.HTTP_OK)
	
elseif (act == 'getcodeimg') then

	local value =get_from_cache(hashkey)
	if value then
		local doItOpt = {}
		doItOpt["MARK_TYPE"] = args["MARK_TYPE"] or ''
		local code = checkcode:doIt(doItOpt)
		if (doItOpt["MARK_TYPE"] == "EXPRESSION") then 
			local script = "return "..code
			res = loadstring(script)()
			set_to_cache(hashkey, res, 300)
		else
			
			set_to_cache(hashkey, code, 300)
		end

		local res = ngx.location.capture('/checkcodeimg/' .. code)
		ngx.header.content_type = "image/png";
		ngx.say(res.body)
		ngx.exit(ngx.HTTP_OK)
	end
	ngx.say('{"errno": -2,"errmsg": "验证码hash已经失效","data": ""}')
	ngx.exit(ngx.HTTP_OK)
elseif (act == 'check') then



	if hashkey and args['code'] then
		local value =get_from_cache(hashkey)
		set_to_cache(hashkey, nil, 1) -- remain 1s

		if value and string.lower(value) == string.lower(args['code']) then
			ngx.say('{"errno": 0,"errmsg": "success" }')
			ngx.exit(ngx.HTTP_OK)
		end
		ngx.say('{"errno": -3,"errmsg": "fail" }')
		ngx.exit(ngx.HTTP_OK)
	end
	ngx.say('{"errno": -1,"errmsg": "Method Not Allowed","data": ""}')
	ngx.exit(ngx.HTTP_OK)


else	
	ngx.say('{"errno": -4,"errmsg":"act Not Allowed","data": ""}')
	ngx.exit(ngx.HTTP_OK)
end

