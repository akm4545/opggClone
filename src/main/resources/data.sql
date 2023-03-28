insert into AUTHORITY (AUTHORITY_IDX, AUTHORITY_GRANT) VALUES (1, 'A');


insert into "USERS" (USER_IDX, USER_ID, USER_PW, USER_NICKNAME, USER_POLICY_YN, AUTHORITY_IDX) values (1, 'nmAdmin', 'admin@naver.com', '1234', 'a', 1);
insert into "USERS" (USER_IDX, USER_ID, USER_PW, USER_NICKNAME, USER_POLICY_YN, AUTHORITY_IDX) values (2, 'nmUser', 'user@naver.com', '1234', 'b', 1);

-- insert into member_lost_item (category, item_detail_info, item_name, member_Id) values ('WALLET', '성원여객', '지갑', '1');
insert into AD (AD_IDX, AD_TITLE, AD_LINK, USER_IDX) values (1, 'BAG', 'aaaaa', '1');
insert into AD (AD_IDX, AD_TITLE, AD_LINK, USER_IDX) values (2, 'ETC', 'aBbbaa', '1');

