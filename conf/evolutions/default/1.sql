# --- !Ups

CREATE TABLE document (
    documentId varchar,
    title varchar,
    author varchar,
    content varchar);

CREATE TABLE resourceLease (
    resourceKey varchar,
    owner varchar);

# --- !Downs //#C

DROP TABLE IF EXISTS document;

DROP TABLE IF EXISTS resourceLease;
