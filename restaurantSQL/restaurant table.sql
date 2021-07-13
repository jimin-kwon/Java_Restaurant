select * from Menu;
select * from Category;
desc Menu;
-- drop table Menu;
-- drop table Category;

/* 메뉴 */
CREATE TABLE Menu (
	menu_id int(10) NOT NULL, /* 메뉴번호 */
	mname VARCHAR(20) NOT NULL, /* 메뉴명 */
	description VARCHAR(100), /* 메뉴소개 */
	price int(10), /* 가격 */
	category_id int(10) /* 카테고리번호 */
);

ALTER TABLE Menu
	ADD
		CONSTRAINT PK_Menu
		PRIMARY KEY (
			menu_id
		);

/* 카테고리 */
CREATE TABLE Category (
	category_id int(10) NOT NULL, /* 카테고리번호 */
	cname  VARCHAR(20) NOT NULL /* 카테고리 */
);

ALTER TABLE Category
	ADD
		CONSTRAINT PK_Category
		PRIMARY KEY (
			category_id
		);

ALTER TABLE Menu
	ADD
		CONSTRAINT FK_Category_TO_Menu
		FOREIGN KEY (
			category_id
		)
		REFERENCES Category (
			category_id
		);
     
     
        
 -- 메뉴 insert
insert into Menu (menu_id, mname, description, price, category_id) 
values (101, "오마카세", "스시롤", 18000, "1");
insert into Menu (menu_id, mname, description, price, category_id) 
values (102, "시금치파스타", "스금치베이스", 20000, "1");
insert into Menu (menu_id, mname, description, price, category_id) 
values (201, "애플파이", "애플시럽", 1500, "2");
insert into Menu (menu_id, mname, description, price, category_id) 
values (202, "버터갈릭감자튀김", "달달짭쪼롬", 5500, "2");
insert into Menu (menu_id, mname, description, price, category_id) 
values (301, "녹차젤라또", "진한녹차", 3800, "3");
insert into Menu (menu_id, mname, description, price, category_id) 
values (401, "레몬쉐이크", "상큼한 레몬", 2500, "4");
insert into Menu (menu_id, mname, description, price, category_id) 
values (501, "흑맥주", "시나몬향", 2200, "5");



-- 카탈로그 insert
insert into Category (category_id, cname) 
values (1,"maindish");
insert into Category (category_id, cname) 
values (2,"sidedish");
insert into Category (category_id, cname) 
values (3,"dessert");
insert into Category (category_id, cname) 
values (4,"beverage");
insert into Category (category_id, cname) 
values (5,"alcoholc");
        
        
-- 조회
select * from Menu;
select * from Category;
select * from Menu,Category;
