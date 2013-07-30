CREATE TABLE TEST_TABLE (
  ID NUMBER(15) PRIMARY KEY,
  APP_NAME VARCHAR2(32),
  APP_COL VARCHAR2(32),
  STATUS VARCHAR2(20),
  EMAIL VARCHAR2(64),
  VENDOR VARCHAR2 (32)
);

create sequence SEQ_TEST_TABLE;

insert into test_table(id, app_name, app_col, status, email, vendor) values (seq_test_table.nextval, 'test app name0','app col','test status','pn@pn.wp', null);
insert into test_table(id, app_name, app_col, status, email, vendor) values (seq_test_table.nextval, 'test app name1','app col','test status','pn@pn.wp', null);
insert into test_table(id, app_name, app_col, status, email, vendor) values (seq_test_table.nextval, 'test app name2','app col','test status','pn@pn.wp', null);
insert into test_table(id, app_name, app_col, status, email, vendor) values (seq_test_table.nextval, 'test app name3','app col','test status','pn@pn.wp', null);
insert into test_table(id, app_name, app_col, status, email, vendor) values (seq_test_table.nextval, 'test app name4','app col','test status','pn@pn.wp', null);