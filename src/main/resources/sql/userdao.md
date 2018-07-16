select
===
select * from user where 1=1
@if(!isEmpty(name)){
and name = #name#
@}

getCount
===
*查询所有记录个数
select count(1) from user
