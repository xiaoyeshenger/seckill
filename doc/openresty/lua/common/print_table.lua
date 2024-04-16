function printTable(table, level)
    level = level or 1
    local indent = ""
    for i = 1, level do
        indent = indent.."  "
    end

    if level > 1 then
        print(indent.."{")
    end

    for k, v in pairs(table) do
        if type(v) == "table" then
            print(indent..k.." = {")
            printTable(v, level + 1)
            print(indent.."},")
        else
            local content = string.format("%s%s = %s,", indent, tostring(k), tostring(v))
            print(content)
        end
    end

    if level > 1 then
        print(indent.."}")
    end
end
