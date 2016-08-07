x= "123"
y = x
puts x.object_id
puts y.object_id

x.freeze
begin
y.gsub! /1/, "5"
rescue => e
puts e
end