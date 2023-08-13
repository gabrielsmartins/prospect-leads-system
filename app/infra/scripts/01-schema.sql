DROP TABLE IF EXISTS tbl_coverage;
DROP TABLE IF EXISTS tbl_assistance;
DROP TABLE IF EXISTS tbl_product;

CREATE TABLE tbl_product(product_id SERIAL NOT NULL,
                         product_name VARCHAR(256) NOT NULL,
                         product_active boolean NOT Null,
                         product_category CHAR(1) NOT NULL,
                         max_total_monthly_premium_amount DECIMAL(17,2) NOT NULL,
                         min_total_monthly_premium_amount DECIMAL(17,2) NOT NULL,
                         suggested_total_monthly_premium_amount DECIMAL(17,2) NOT NULL,
                         total_coverage_amount DECIMAL(17,2) NOT NULL,
                         created_at TIMESTAMP NOT NULL,
                         updated_at TIMESTAMP,
                         deleted_at TIMESTAMP,
                         CONSTRAINT CHK_category CHECK (product_category IN('L', 'P', 'E', 'T')),
                         CONSTRAINT PK_tbl_product PRIMARY KEY(product_id));

CREATE TABLE tbl_coverage(product_id INT NOT NULL, 
                          coverage_name VARCHAR(256) NOT NULL,
                          coverage_amount DECIMAL(17,2) NOT NULL,
                          CONSTRAINT PK_tbl_coverage PRIMARY KEY(product_id, coverage_name),
                          CONSTRAINT FK_tbl_product_tbl_coverage FOREIGN KEY(product_id) REFERENCES tbl_product(product_id)); 

CREATE TABLE tbl_assistance(product_id INT NOT NULL,
                            assistances VARCHAR(1024) NOT NULL,
                            CONSTRAINT PK_tbl_assistance PRIMARY KEY(product_id, assistances),
                            CONSTRAINT FK_tbl_product_tbl_assistance FOREIGN KEY(product_id) REFERENCES tbl_product(product_id));                                                   
