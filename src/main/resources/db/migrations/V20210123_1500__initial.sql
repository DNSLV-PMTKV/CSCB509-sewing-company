create table if not exists companies (
    id BIGSERIAL not null PRIMARY KEY,
    name varchar(256) not null,
    created_ts timestamp with time zone not null default now(),
    updated_ts timestamp with time zone default now()
);
create table if not exists employees (
    id BIGSERIAL not null PRIMARY KEY,
    full_name varchar(256) not null,
    hired_ts timestamp with time zone not null default now(),
    salary DECIMAL(8, 2) not null,
    company_id BIGINT REFERENCES companies(id)
);
create table if not exists stocks (
    id BIGSERIAL not null PRIMARY KEY,
    color varchar(64) not null default 'brown',
    sell_price DECIMAL(8, 2) not null,
    create_price DECIMAL(8, 2) not null
);
create table if not exists employee_stocks(
    id BIGSERIAL not null PRIMARY KEY,
    employee_id BIGINT REFERENCES employees(id),
    stock_id BIGINT REFERENCES stocks(id),
    count INTEGER not null,
    created_ts timestamp with time zone not null default now()
);