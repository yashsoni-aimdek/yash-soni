create index IX_82C86331 on News_Author (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_E4C201F3 on News_Author (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_60272AAF on News_Author_Book (bookId);
create index IX_ECB70E71 on News_Author_Book (companyId);

create index IX_B07D4135 on News_Book (authorId[$COLUMN_LENGTH:75$], groupId);
create index IX_ED456C8F on News_Book (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_B30DCAD1 on News_Book (uuid_[$COLUMN_LENGTH:75$], groupId);