
SQL> create table stk( stkID varchar2(10) not null primary key,name varchar2(20));

 


SQL> create table prices(stkID varchar2(10) not null, dat date not null unique, price number(5,2),
					primary key (stkid,dat),
					foreign key ( stkID) references stk(stkID));

 
 
SQL> create table options(optionID varchar2(6) not null, stkID varchar(10) not null,
		 expdate varchar2(10) not null,optiontype varchar2(4) not null, exerciseprice varchar2(10),
		 bid number(5,3) not null, offer number(5,3) not null, dat date not null,
		 primary key (optionID,dat), foreign key (dat) references prices (dat));
		 
		 
