select * from worksheet_detail where status >= 9;

select * from factory_status where status >= 9 and modification_times >= :From and factory_status.modification_times <= :To ;

select * from factory_status_detail;

select * from factory_status_detail;

select * from factory_status where status >= 9 and modification_times >= :From and factory_status.modification_times >= :To ;

select * from factory_status_detail fsd where factory_status_id = 108 order by fsd.modification_times asc;

select * from factory_status_detail fsd, worksheet_detail wd where fsd.worksheet_detail_id = wd.id and fsd.factory_status_id = 108 order by fsd.modification_times asc;

select wd.employee_id,
(select name from employee where id = wd.employee_id) as employee_name,
(select name from meta_procedure where id = wd.meta_procedure_id) as procedure_name,
wd.end_time,
mc.code_id as commande_id,
mc.external_code
from main_commande mc,slave_commande sc,worksheet ws,worksheet_detail wd
where mc.id=sc.main_commande_id and sc.id = ws.slave_commande_id and ws.id = wd.worksheet_id
and  wd.end_time >= :From and wd.end_time <= :To
and wd.employee_id = '11'
order by commande_id asc;


select * from factory_status_detail fsd where fsd.factory_status_id = 108 order by 3 asc;

select * from employee;

select * from worksheet;

select * from worksheet_detail where employee_id = '4'  and end_time >= :From and end_time >= :To ;

select * from meta_procedure