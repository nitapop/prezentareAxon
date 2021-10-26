SET DATABASE SQL SYNTAX ORA TRUE;

  create table STUDENT (
    ID              number(30,0) ,
    NAME            varchar2(30 byte),
    BIRTH_DATE      timestamp(0),
    MATH_STATE      varchar2(30 byte)
  );

  alter table STUDENT add constraint STUDENT_PK primary key ( ID );

  alter table STUDENT
    add check ( MATH_STATE in (
        'ACCEPTED',
        'REJECTED'
    ) );


