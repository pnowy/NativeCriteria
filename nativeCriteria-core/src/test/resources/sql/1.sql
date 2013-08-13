CREATE TABLE ADDRESS (
  ID NUMBER(18) PRIMARY KEY,
  CITY VARCHAR2(64),
  STREET VARCHAR2(64),
  BUILDING_NUMBER VARCHAR2(16),
  ZIP_CODE VARCHAR2(16)
);

create sequence SEQ_ADDRESS;

insert into ADDRESS(id, city, street, building_number, zip_code) values (seq_address.nextval, 'WARSAW','JEROZOLIMSKIE','34','34-567');