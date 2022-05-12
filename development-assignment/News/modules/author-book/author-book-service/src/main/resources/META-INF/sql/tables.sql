create table News_Author (
	uuid_ VARCHAR(75) null,
	authorId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	authorCode VARCHAR(75) null,
	authorName VARCHAR(75) null,
	authorRegisterDate DATE null
);

create table News_Author_Book (
	companyId LONG not null,
	authorId LONG not null,
	bookId LONG not null,
	primary key (authorId, bookId)
);

create table News_Book (
	uuid_ VARCHAR(75) null,
	bookId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	bookCode VARCHAR(75) null,
	bookName VARCHAR(75) null,
	bookPublishDate DATE null
);