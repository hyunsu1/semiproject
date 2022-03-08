create sequence freemoim_seq
increment by 1;
alter sequence freemoim_seq nocache;

create table freemoim (
	f_name varchar2(50 char) primary key,
	f_interest varchar2(10 char) not null,
	f_region varchar2(30 char) not null,
	f_maxMembers number(4) not null,
	f_introduce varchar2(200 char),
	f_image varchar2(30 char),
	f_currentMembers number(4) default 1,
	f_createDate date default sysdate,
	f_fee varchar(10 char) default 0
);

delete from FREEMOIM where f_name = 'hs21';

alter table freemoim add(f_fee varchar(10 char) default 0);
alter table freemoim drop column fee;
select * from freemoim;
drop table freemoim;
select * from freemoim order by f_createDate desc;
update freemoim
set f_currentMembers = (select count(*) from USER_MOIM where u_moimname = '모임3')
where f_name = '모임3';
delete from FREEMOIM;
select count(*) from freemoim;

select rownum, freemoim.*
	from (select * from freemoim order by f_createDate desc) freemoim

select * from (
	select rownum as num, freemoim.*
	from (select * from freemoim order by f_createDate desc) freemoim
) where num between 100 and 351;

insert into freemoim(f_name, f_interest, f_region, f_maxMembers, f_introduce, f_image) values ('hs21', '여행', '서울 강남구 신사동', 10, 'hs', '다운로드.jpg');

select count(*) from freemoim where f_region like '%%' and f_name like '%4%' and f_interest='';
select count(*) from freemoim where f_region like '%%' and f_name like '%4%' and f_interest like '%%';

drop table freemoim;
drop sequence freemoim_seq;
delete from freemoim;
select * from freemoim;

select f_name
from freemoim, user_moim, m_member
where f_name = u_moimName and
u_userId = m_id and u_position = 'admin' and m_id = 'hjj';

------------------------------------------------------------------------------------------------------

create table m_member(
	m_id varchar2(20) primary key,
	m_pw varchar2(30) not null,
	m_name varchar2(30) not null,
	m_gender varchar2(10) not null,
	m_birth varchar2(20) not null,
	m_email varchar2(50) not null,
	m_phone varchar2(30) not null,
	m_area varchar2(100) not null,
	m_qa1 varchar2(100) not null,
	m_qa2 varchar2(100) not null
);


insert into m_member values ('hs', 'hs', '현수', '남', '0116', 'hs@hs.hs', '010-0000-0000', '서울 종로구', 'hs', 'hs');
insert into m_member values ('hs2', 'hs2', '현수2', '남', '0117', 'hs2@hs.hs', '010-0000-0001', '서울 종로구2', 'hs2', 'hs2');
insert into m_member values ('hs3', 'hs3', '현수3', '남', '0118', 'hs3@hs.hs', '010-0000-0003', '서울 종로구3', 'hs3', 'hs3');
insert into m_member values ('hs4', 'hs4', '현수4', '남', '0114', 'hs4@hs.hs', '010-0000-0004', '서울 종로구4', 'hs4', 'hs4');
select * from m_member;
drop table m_member;
delete from m_member;

delete m_member where m_id = 'hs2';

------------------------------------------------------------------------------------------------------

create table user_moim (
	u_moimName varchar2(50 char),
	u_userId varchar2(20 char),
	u_position varchar2(10 char) not null,
	primary key(u_moimName, u_userId),
	constraint user_moim_fk foreign key(u_userId) references m_member(m_id) on delete cascade
	constraint user_moim_fk2 foreign key(u_moimName) references freemoim(f_name) on delete cascade
);

alter table user_moim drop constraint user_moi_fk; 
alter table user_moim add constraint user_moim_fk foreign key(u_userId) references m_member(m_id) on delete cascade;
alter table user_moim add constraint user_moim_fk2 foreign key(u_moimName) references freemoim(f_name) on delete cascade;
select * from user_moim;
select * from user_moim where u_moimName='모임3' and u_userId='hs';
delete from user_moim where u_moimName='163';
insert into user_moim values('151', 'hs2', 'member');
drop table user_moim;
select * from user_moim where u_moimName='모임3' and u_position='admin';
delete from user_moim where u_moimName='주식코인존버';
select * from user_moim where u_moimName='test1';
select count(*) from user_moim where u_userId='hs' and u_position='admin'


select * from PAIDMOIM
------------------------------------------------------------------------------------------------------

create sequence mail_seq
increment by 1;
alter sequence mail_seq nocache;

create table mail(
	m_num number(5) primary key,
	m_sendId varchar2(20 char),
	m_receiveId varchar2(20 char) not null,
	m_title varchar2(100 char) not null,
	m_content varchar2(300 char) not null
);

drop table mail;
insert into mail values('hs', 'hs2', '가입신청', '이미지1');
select * from mail order by m_num desc;
insert into mail values (mail_seq.nextval, ?, ?, '[모임322]' 모임 가입이 승인되었습니다', '[모임322]' 모임 가입이 승인되었습니다', NULL)
------------------------------------------------------------------------------------------------------

create sequence application_seq
increment by 1;
alter sequence application_seq nocache;

create table application (
	a_num number(5) primary key,
	a_moimName varchar2(50 char) not null,
	a_sendId varchar2(20 char) not null,
	a_receiveId varchar2(20 char) not null,
	a_introduce varchar2(300 char)
);

select * from application order by a_num desc;
delete from application;

------------------------------------------------------------------------------------------------------

create table payment (
	p_sendId varchar2(20 char) not null,
	p_receiveId varchar2(30 char) not null,
	p_fee varchar2(10 char) not null,
	p_payDate date default sysdate
);

select * from payment;
delete from payment;
drop table payment;
insert into payment values ('1','2','3', sysdate);