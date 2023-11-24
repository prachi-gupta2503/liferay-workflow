create index IX_E00A0EBD on POC_Candidate (firstName[$COLUMN_LENGTH:75$]);
create index IX_3FB41542 on POC_Candidate (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_7E650444 on POC_Candidate (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_3C0A62DC on POC_Employee (firstName[$COLUMN_LENGTH:75$]);
create index IX_DAB1B043 on POC_Employee (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_2E5F6B85 on POC_Employee (uuid_[$COLUMN_LENGTH:75$], groupId);