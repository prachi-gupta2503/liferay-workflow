create index IX_A9FBF4DD on PM_Product (groupId, status);
create index IX_30A5D292 on PM_Product (name[$COLUMN_LENGTH:75$]);
create index IX_33516047 on PM_Product (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_BC734C89 on PM_Product (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_FEC9E273 on PM_Project (groupId, status);
create index IX_108486BC on PM_Project (name[$COLUMN_LENGTH:75$]);
create index IX_78412571 on PM_Project (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_1A065433 on PM_Project (uuid_[$COLUMN_LENGTH:75$], groupId);