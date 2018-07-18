create table quote (id int(10) primary key auto_increment ,name varchar(100),code varchar(20),url varchar(200));

	
	
/*create table quote_trans_detail (id int(10) primary key auto_increment,
	code varchar(20),trans_time varchar(30),begin_price DECIMAL(7,2),
	end_price DECIMAL(7,2),min_price DECIMAL(7,2),max_price DECIMAL(7,2),
	trans_count int(10),trans_total int(10));	*/
create table quote_trans_detail (id int(10) primary key auto_increment,
	code varchar(20),trans_time varchar(30),begin_price DECIMAL(7,2),
	end_price DECIMAL(7,2),min_price DECIMAL(7,2),max_price DECIMAL(7,2),
	trans_count int(10),trans_total int(10),change_price DECIMAL(7,2),
	change_percent DECIMAL(7,2),swing DECIMAL(7,2),turnover DECIMAL(7,2),
	weekday int(3));	
create unique index	code_time on quote_trans_detail(code,trans_time);
	
	
	