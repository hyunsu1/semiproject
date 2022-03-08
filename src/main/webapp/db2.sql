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

select * from FREEMOIM;
delete from FREEMOIM where f_name = 'Nice Trip';
delete from FREEMOIM where f_name = 'Games';
------------------------------------------------------------------------------------------------------

create table paidmoim (
	p_name varchar2(50 char) primary key,
	p_interest varchar2(10 char) not null,
	p_region varchar2(30 char) not null,
	p_maxMembers number(4) not null,
	p_introduce varchar2(200 char),
	p_image varchar2(30 char),
	p_currentMembers number(4) default 1,
	p_createDate date default sysdate,
	p_fee varchar2(10 char) not null
);


select * from paidmoim

delete paidmoim where p_name='123456'
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

select * from M_MEMBER;
------------------------------------------------------------------------------------------------------

create table user_moim (
	u_moimName varchar2(50 char),
	u_userId varchar2(20 char),
	u_position varchar2(10 char) not null,
	primary key(u_moimName, u_userId),
	constraint user_moi_fk foreign key(u_userId) references m_member(m_id)
);

select * from user_moim
delete user_moim where u_moimname = '유료모임입니다';
------------------------------------------------------------------------------------------------------

create sequence mail_seq
increment by 1;
alter sequence mail_seq nocache;

create table mail(
	m_num number(5) primary key,
	m_sendId varchar2(20 char),
	m_receiveId varchar2(20 char) not null,
	m_title varchar2(100 char) not null,
	m_content varchar2(300 char) not null,
	constraint mail_fk foreign key(m_receiveId) references m_member(m_id)
);


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

------------------------------------------------------------------------------------------------------

create table payment (
	p_sendId varchar2(20 char) not null,
	p_receiveId varchar2(30 char) not null,
	p_fee varchar2(10 char) not null,
	p_payDate date default sysdate
);

------------------------------------------------------------------------------------------------------

CREATE SEQUENCE board_seq
        START WITH 1
        INCREMENT BY 1;
create table board(
	b_no     number(4) primary key,
	b_title  varchar2(100) not null,
	b_text   varchar2(500) not null,
	b_date   date not null,
	b_cnt 	 number(4) not null,
	b_moim   varchar(50) not null
);

select * from board
delete from board where b_moim = '주식코인존버';
------------------------------------------------------------------------------------------------------

CREATE SEQUENCE photoboard_seq
        START WITH 1
        INCREMENT BY 1;

create table photoboard(
	p_no number(4) primary key,
	p_title varchar2(100) not null,
	p_text varchar2(1500) not null,
	p_thumb varchar2(300) not null,
	p_file varchar2(1500) not null,
	p_date date not null,
	p_moim varchar2(50) not null
);

select * from photoboard
------------------------------------------------------------------------------------------------------

SELECT * FROM USER_OBJECTS WHERE OBJECT_TYPE='TABLE';