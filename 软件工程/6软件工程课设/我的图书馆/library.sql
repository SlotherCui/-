--1.libraryͼ��ݣ��ֹݣ�
--drop table library
create table library(
lib_id varchar(10),
lib_name varchar(30) not null,
lib_add varchar(50) not null,
lib_tel varchar(15),
primary key(lib_id)
)
--alter table library add lib_tel varchar(15)
--insert into library values('001','���԰�ֹ�','�����и��¿�����˴��·�ж�')
--insert into library values('002','����ֹ�','����ɽ����·27��')
--select * from library
--2.librarian ͼ�����Ա
--drop table librarian
create table librarian(
librarian_id varchar(10) primary key,
librarian_name varchar(20) not null,
librarian_sex bit default '0',
librarian_bir date,
librarian_passwd varchar(20) not null,
librarian_email varchar(30) not null,
librarian_tel varchar(15) not null,
librarian_work_place varchar(10) not null,
foreign key(librarian_work_place) references library
)

--alter table librarian add foreign key(librarian_work_place) references library
--insert into librarian values('2009001','wangwang',0,'1990-01-01','362413','990534489@qq.com','115216406000','001')
--insert into librarian values('2009002','wtz',0,'2011-07-20','362413','990534489@qq.com','15216406000','001')
--insert into librarian(librarian_id,librarian_name,librarian_sex,librarian_passwd,librarian_email,librarian_tel,librarian_work_place) values('2009005','wtz',0,'362413','990534489@qq.com','15216406000','001')
--update librarian set librarian_email='994602973@qq.com' where librarian_id='2009001'
--select * from librarian
--delete from librarian where librarian_id='2009003'
--drop table librarian
--3.administratorϵͳ����Ա��
--drop table administrator
create table administrator(
admin_id varchar(10),
admin_name varchar(20) not null,
admin_passwd varchar(20) not null,
primary key(admin_id))

--insert into administrator values('009','wang','362413')
--insert into administrator values('001','wang,cheng','362413')
--delete from administrator where admin_id='001'
--select * from administrator

--4.type ͼ�����
--drop table type
create table type(
type_id varchar(10) primary key,
type_name varchar(30) not null,
type_describtion text)

--insert into type values('1000','��ҵ����','�����Զ���,�����,ʯ��,���Ӽ���,���ߵ�,��еұ���ҵ��')
--insert into type values('1001','��̼���','��̷����ָ����')
--select * from type
--5.purviewȨ��
--drop table purview
create table purview(
purview_id varchar(5) primary key,
purview_name varchar(20) not null,
book_num tinyint not null,--�ܽ�����
book_time int not null,   --�ܽ��ĵ�ʱ�䳤��
order_num tinyint not null) --��ԤԼ��ͼ����

insert into purview values('01','������',6,30,1)
insert into purview values('02','˶ʿ�о���',10,40,2)
--update purview set book_num=6
--select * from purview
--6.user �û�
drop table user_info
create table user_info(
user_id varchar(20) primary key,
user_name varchar(20) not null,
user_passwd varchar(20) not null,
user_birthday date,
user_sex bit default '0',--0�� ��1Ů
user_question varchar(30),
user_answer varchar(30),
user_purview_id varchar(5) not null,
user_dept varchar(50),
user_email varchar(30),
foreign key(user_purview_id) references purview
)
--alter table user_info add user_email varchar(30)
--alter table user_info add user_dept varchar(50)
--insert into user_info values('200900301','wangmomo','362413','1990-01-01',0,'����˭','wangmomo','01')
--insert into user_info values('200900302','wtz','200900302','1991-02-02',0,'�����ڵĴ�ѧ','ɽ����ѧ','01')
--select * from user_info
--7.book
--drop table book
create table book(
book_id int  identity primary key,
book_isbn varchar(20) not null,
book_name varchar(50) not null,
book_author varchar(60) not null,
book_translator varchar(50),
book_price money not null,
book_type_id varchar(10) not null,
book_press varchar(30) not null,
book_lib_id varchar(10) not null,
book_describtion text,
book_in_time date not null,
book_publish_time date not null,
book_word int,--��λΪǧ��
book_language varchar(20) default '����ͼ��',
book_state varchar(15) default '�ڹ�',
foreign key(book_lib_id) references library,
foreign key(book_type_id) references type on delete cascade,
check (book_language in('����ͼ��','����ͼ��')),
check (book_state in('�ڹ�','��ԤԼ','���','ԤԼ����'))
)
--EXECUTE sp_help book
--alter table book drop constraint CK__book__book_state__4D5F7D71
--alter table book drop column state
--alter table book drop column book_sum
--alter table book drop column book_in_num
--alter table book add foreign key(book_type_id) references type on delete cascade
--alter table book add constraint bookstate check (book_state in('�ڹ�','��ԤԼ','���','ԤԼ����'))
--alter table book add book_word int
--alter table book add book_language varchar(20) default '����ͼ��'
--alter table book add check (book_language in('����ͼ��','����ͼ��'))
--delete from book
/*insert into book values('978-7-111-21382-6','java���˼��','Bruce Eckel','�����','108','1001','��е��ҵ������','001','java�����鼮','2011-01-01','2007-06-01',1000,'����ͼ��','���')
insert into book values('978-7-111-21382-6','java���˼��','Bruce Eckel','�����','108','1001','��е��ҵ������','001','java�����鼮','2011-01-01','2007-06-01',1000,'����ͼ��','���')
insert into book values('978-7-115-14554-3','c++ primer','Stanley B.Lippman,Josee Lajoie,Barbara E.Moo','��ʦ��,������,÷����,����','99','1001','�����ʵ������','001','c++�����鼮','2011-01-01','2007-06-01',1000,'����ͼ��','���')
insert into book values('978-7-115-14554-3','c++ primer','Stanley B.Lippman,Josee Lajoie,Barbara E.Moo','��ʦ��,������,÷����,����','99','1001','�����ʵ������','001','c++�����鼮','2011-01-01','2007-06-01',1000,'����ͼ��','�ڹ�')
insert into book values('978-7-111-17901-3','java���ļ������','Cay S.Horstmann,Gary Cornell','�����,����,Ҧ��ƽ','108','1001','��е��ҵ������','001','java�����鼮','2008-01-01','2007-06-01',955,'����ͼ��','���')
insert into book values('978-7-111-17901-3','java���ļ������','Cay S.Horstmann,Gary Cornell','�����,����,Ҧ��ƽ','108','1001','��е��ҵ������','001','java�����鼮','2008-01-01','2007-06-01',955,'����ͼ��','���')
insert into book values('978-7-111-17901-3','java���ļ������','Cay S.Horstmann,Gary Cornell','�����,����,Ҧ��ƽ','108','1001','��е��ҵ������','001','java�����鼮','2008-01-01','2007-06-01',955,'����ͼ��','���')
insert into book values('978-7-111-25611-3','java���ļ����ڰ˰�','Cay S.Horstmann,Gary Cornell','�����,����,Ҧ��ƽ','118','1001','��е��ҵ������','001','java�����鼮','2010-01-01','2008-12-01',900,'����ͼ��','���')
insert into book values('978-7-111-25611-3','java���ļ����ڰ˰�','Cay S.Horstmann,Gary Cornell','�����,����,Ҧ��ƽ','118','1001','��е��ҵ������','001','java�����鼮','2010-01-01','2008-12-01',900,'����ͼ��','�ڹ�')
insert into book values('978-7-111-17901-3','java���ļ������','Cay S.Horstmann,Gary Cornell','�����,����,Ҧ��ƽ','108','1001','��е��ҵ������','001','java�����鼮','2008-01-01','2007-06-01',955,'����ͼ��','ԤԼ����')
*/
--select * from book
--update book set book_state='�ڹ�'

--8.borrow
--drop table borrow
--delete from borrow
create table borrow(
borrow_id int identity primary key,
borrow_user_id varchar(20) not null,
borrow_book_id int not null,
borrow_is_renew bit not null default 1,--�ܷ����� 0-����,1-��
borrow_time date not null default getdate(),
borrow_restrict_time date not null, --Ӧ�黹ʱ��
borrow_return_time date,
foreign key(borrow_user_id) references user_info on delete cascade,
foreign key(borrow_book_id) references book on delete cascade
)

--alter table borrow add borrow_return_time date
--alter table borrow add unique(borrow_user_id,borrow_book_id)  
--alter table borrow add foreign key(borrow_user_id) references user_info on delete cascade
--alter table borrow add foreign key(borrow_book_id) references book on delete cascade
--delete from borrow where borrow_user_id='200900301' and borrow_book_id in(1,2)
/*insert into borrow values('200900301234',15,1,'2011-05-02','2011-06-01',null)
insert into borrow values('200900301',2,1,'2011-07-13','2011-08-13',null)
insert into borrow values('200900301',6,1,'2011-05-05','2011-06-05','2011-06-01')
insert into borrow values('200900301',8,1,'2011-07-05','2011-08-05',null)
insert into borrow values('200900301233',6,1,'2011-07-01','2011-08-01',null)
*/
--select * from borrow
--update borrow set borrow_return_time=null where borrow_id=4

--9.durty  Υ�¼�¼
--drop table durty
create table durty(
durty_id int identity primary key,
durty_borrow_id int not null,
durty_money money,
durty_describtion varchar(50),
foreign key(durty_borrow_id) references borrow on delete cascade
)
alter table durty add foreign key(durty_borrow_id) references borrow on delete cascade
--alter table durty add foreign key(durty_borrow_id) references borrow on delete set null
--alter table durty drop constraint FK__durty__durty_bor__6DCC4D03
--select * from durty
--delete from durty
--insert into durty values(1,22,'���ڷ���')
--insert into durty values(2,10,'���ڷ���')

--10.order ԤԼ
--drop table order_info
create table order_info(
order_id int identity primary key,
order_book_id int not null,
order_user_id varchar(20) not null,
order_time date not null default getdate(),
order_restrict_time date not null,
order_return_time date,
--order_state varchar(10) not null default '�ȴ�',
--check (order_state in('�ȴ�','����')),
foreign key(order_book_id) references book on delete cascade,
foreign key(order_user_id) references user_info on delete cascade
)
--alter table order_info add order_restrict_time date not null,order_return_time date
--alter table order_info add foreign key(order_user_id) references user_info on delete cascade
--alter table order_info add foreign key(order_book_id) references book on delete cascade
--insert into order_info(order_book_id,order_user_id) values(3,'200900301236')
--delete from order_info where order_id=6
--select * from order_info
--11 history������ʷ
/**drop table history
create table history(
history_id int identity primary key,
history_user_id varchar(20) not null,
history_book_id int not null,
history_borrow_time date not null,
history_return_time date not null,
foreign key(history_user_id) references user_info,
foreign key(history_book_id) references book
)
insert into history values('200900301',2,'2011-03-05','2011-04-04')
select * from history
**/

--����������
--1.���������borrowʱ����book��״̬,���г�����durty�����Ӧ��Ϣ(�����ã�����Ҳ�����borrow)
/**create trigger borrow_update on borrow
after update
as
begin
 declare @betweentime int
 declare @returntime date
 declare @borrowid int 
  if (select book_state from book,inserted where book.book_id=inserted.borrow_book_id)='���'
	  begin
		update book set book_state='�ڹ�' where book_id=(select borrow_book_id from inserted)
	  end
  else
      begin
        update book set book_state='ԤԼ����' where book_id=(select borrow_book_id from inserted)
      end
  select @returntime=borrow_restrict_time from inserted
  set @betweentime=datediff(day,@returntime,getdate()) 
  if @betweentime>0
      begin
        select @borrowid=borrow_id from inserted  
        insert into durty values(@borrowid,@betweentime,'���ڷ���')
      end
end
**/
--drop trigger borrow_update
--������ 2.����ʱ��borrow��д�뽫��Ӧ��book���е���״̬��Ϊ���
create trigger borrow_insert on borrow
after insert
as
begin
  update book set book_state='���' where book_id=(select borrow_book_id from inserted)
end
--������ 3.ԤԼͼ��ʱ����order_info��д���Ӧ��ԤԼ��Ϣʱ��book���е�״̬��Ϊ��ԤԼ
create trigger order_insert on order_info
after insert
as 
begin
 update book set book_state='��ԤԼ' where book_id=(select order_book_id from inserted)
end

--����ʱ ���Ҷ��ߵĽ�����Ϣ
select borrow_book_id,book_isbn,book_name,borrow_time,borrow_restrict_time,lib_name 
from borrow,book,library where borrow_user_id='200900301' and borrow.borrow_book_id=book.book_id 
and book.book_lib_id=library.lib_id and borrow_return_time is null
--�黹ͼ��
update borrow set borrow_return_time=GETDATE() where borrow_id=2
update book set book_state='�ڹ�' where book_id=8 --������


--����ʱ �û�����,�ܽ������,���
select book_num,book_time,user_name from purview,user_info 
where user_id='200900301' and user_purview_id=purview_id
--����ʱ �鿴���߽����������
select count(*) from borrow,order_info where borrow_user_id='200900301236' and borrow_return_time is null 
and order_user_id='200900301236' 
select count(*) from borrow where borrow_user_id='200900301' and borrow_return_time is null
select count(*)+(select count(*) from order_info where order_user_id='200900301236') from borrow 
where borrow_user_id='200900301236' and borrow_return_time is null
--����ʱ �鿴�����Ƿ��г��ڵ���
select * from borrow where borrow_user_id='200900301' and borrow_return_time is null and datediff(day,borrow_restrict_time,getdate())>0
--����ʱ ���������,isbn,�Ƿ�ԤԼ
select book_name,book_state from book where book_id=2
--����                                       
insert into borrow(borrow_user_id,borrow_book_id,borrow_restrict_time) values('200900302',7,dateadd(day,30,getdate()))

--ɾ���û�
--�޸��û�������,����,�������ڣ��Ա�,����,��,Ȩ��,Ժϵ,email��
update user_info set user_name='wangtanfei',user_passwd='362413',user_birthday='1990-05-01',user_sex=1,
user_question='what is my name',user_answer='wangtanfei',user_purview_id='01',user_dept='�������ѧ�뼼��ѧԺ',
user_email='990534489@qq.com' where user_id='200900303'
delete from user_info where user_id='200900301'
--��ȡ����Ȩ��id,����
select purview_id,purview_name from purview
--����û�
insert into user_info values('200900303','tanfei','362400','1990-05-10','0','����˭','tanfei','01','���ѧԺ')
select * from user_info

--ɾ��ͼ��
delete from book where book_id='001'
--�޸�ͼ��
update book set book_isbn='9787111213826',book_name='java���˼��',book_author='Bruce Eckel',book_translator='�����',book_price='108.00',book_type_id='1001',
book_press='��е��ҵ������',book_lib_id='001',book_describtion='java�����鼮',book_in_time='2010-10-01',book_publish_time='2007-06-01',book_word='1000',
book_language='����ͼ��',book_state='�ڹ�' where book_id=2
--���ͼ��

--����ͼ�����Ա��Ϣ
update librarian set librarian_name='jacky',librarian_sex=1,librarian_bir='1990-01-01',librarian_passwd='362413',
librarian_email='jacky@sdu.edu.cn',librarian_tel='12345678901',librarian_work_place='002' where librarian_id='2009005'
--ɾ��ͼ�����Ա��Ϣ
delete from librarian where librarian_id='200904'
--����ͼ�����
update type set type_name='��ҳ����',type_describtion='����jsp,javascript,asp,php���鼮' where type_id='1000'
--ɾ��ͼ�����
delete from type where type_id='2001'
--��ѯͼ�� ��ҳ ҳ��
select * from book where (book_author like '%Josee Lajoie%' or book_translator like '%����%') and book_language='����ͼ��'
--��ѯ ��nҳҪ��ʾ��ͼ����Ϣ
select top 2 * from book where book_name like '%java%' and book_id not in(select top 4 book_id from book where book_name like '%java%' order by book_publish_time asc) order by book_publish_time asc
select top 2 * from book where book_name like '%java%' and book_language='����ͼ��' and book_lib_id='001' and book_id not in(select top 0 book_id from book where book_name like '%java%' and book_language='����ͼ��' and book_lib_id='001' order by book_publish_time asc) order by book_publish_time asc
select top 2 * from book where book_press='��е��ҵ������' and book_language='����ͼ��' and book_lib_id='001' and book_id not in(select top 2 book_id from book where book_press='��е��ҵ������' and book_language='����ͼ��' and book_lib_id='001' order by book_publish_time desc) order by book_publish_time desc
select top 3 * from book where book_name like '%���ļ���%' and book_language='����ͼ��' and book_lib_id='001' and book_id not in(select top 3 book_id from book where book_name like '%���ļ���%' and book_language='����ͼ��' and book_lib_id='001' order by book_isbn asc) order by book_isbn asc
--�һض�ʧ������
select user_passwd from user_info where user_id='200900301232' and user_question='hahah' and user_answer='hahahha'
--��ȡ�û���Ϣ
select user_id,user_name,user_passwd,user_birthday, 
					user_sex,user_question,user_answer,purview_name,user_dept,user_email from
					 user_info,purview where user_id='200900301236' and user_purview_id=purview_id
--��ȡδ�黹����
select book_id,book_isbn,book_name,book_author,book_translator,lib_name,borrow_time,borrow_restrict_time
 from borrow,book,library where borrow_book_id=book_id and borrow_user_id='200900301236' and 
 borrow_return_time is null and book_lib_id=lib_id
 --��ȡָ���û��ܽ���
 select book_time from purview,user_info where user_id='200900301236' and user_purview_id=purview_id
--����(����û��ԤԼ����û���ڵ���,��û�����)
delete from order_info where order_id=1
insert into order_info values(6,'200900301234','2011-08-13')
update borrow set borrow_restrict_time=dateadd(day,30,borrow_restrict_time),borrow_is_renew=0 where 
borrow_return_time is null and borrow_book_id=5
--��ȡ�û���Υ�¼�¼

select book_id,book_name,borrow_time,borrow_return_time,durty_money,durty_describtion from durty,book,borrow
 where borrow_user_id='200900301236' and durty_borrow_id=borrow_id and borrow_book_id=book_id
--��ȡԤԼ��Ϣ
select book_id,book_name,lib_name,book_state,order_restrict_time,order_return_time from 
order_info,book,library where order_user_id='200900301236' and order_book_id=book_id and book_lib_id=lib_id
 --update borrow set borrow_return_time=null where borrow_id=39
--ִ��ԤԼ
insert into order_info(order_book_id,order_user_id,order_restrict_time) 
values(5,'200900301234',(select borrow_restrict_time from borrow where borrow_book_id=5 and borrow_return_time is null))
--��ȡΥ����Ϣ
select book_id,book_name,borrow_time,borrow_return_time,durty_money,durty_describtion from book,durty,borrow
 where borrow_user_id='200900301234' and borrow_id=durty_borrow_id and book_id=borrow_book_id  
--��ȡ������ʷ
select book_id,book_name,book_author,book_translator,lib_name,borrow_time,borrow_return_time from 
borrow,book,library where borrow_user_id='200900301234' and borrow_return_time is not null 
and borrow_book_id=book_id and book_lib_id=lib_id
select * from borrow
select * from order_info
select * from book
select * from librarian
select * from type
select * from library
select * from user_info
select * from durty
select * from purview
select book_num,order_num from purview,user_info where user_id='200900301236' and user_purview_id=purview_id
select * from book where book_id=13 and book_state='���'
select * from order_info where order_book_id=3 and order_user_id=200900301234
insert into durty values(21,72,'���ڷ���')


delete from borrow where borrow_id=43

select * from book where book_name='java���ļ������'
select * from book where book_isbn='9787111179013'

exec sp_help durty
------��������------
create index IX_BOOK_NAME ON book(book_name)
create index IX_BOOK_ISBN ON book(book_isbn)
create index IX_BOOK_AUTHOR ON book(book_author)
create index IX_BOOK_PRESS ON book(book_press)
create index IX_BORROW_USER_ID ON borrow(borrow_user_id)