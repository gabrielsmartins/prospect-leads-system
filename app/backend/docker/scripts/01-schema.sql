DROP TABLE IF EXISTS tbl_product;
DROP TABLE IF EXISTS tbl_offer;
DROP TABLE IF EXISTS tbl_coverage;
DROP TABLE IF EXISTS tbl_assistance;
DROP TABLE IF EXISTS tbl_offer_coverage;
DROP TABLE IF EXISTS tbl_offer_assistance;

CREATE TABLE tbl_product(product_id SERIAL NOT NULL,
                         product_name VARCHAR(256) NOT NULL,
                         product_active boolean NOT Null,
                         product_category CHAR(1) NOT NULL,
                         created_at TIMESTAMP NOT NULL,
                         updated_at TIMESTAMP,
                         deleted_at TIMESTAMP,
                         CONSTRAINT CHK_category CHECK (product_category IN('L', 'P', 'E', 'T')),
                         CONSTRAINT PK_tbl_product PRIMARY KEY(product_id));

CREATE TABLE tbl_offer(offer_id SERIAL NOT NULL,
                       offer_name VARCHAR(256) NOT NULL,
                       offer_active boolean NOT NULL,
                       product_id INT NOT NULL,
                       created_at TIMESTAMP NOT NULL,
                       updated_at TIMESTAMP,
                       deleted_at TIMESTAMP,
                       CONSTRAINT FK_tbl_offer_tbl_product FOREIGN KEY(product_id) REFERENCES tbl_product(product_id),
                       CONSTRAINT PK_tbl_offer PRIMARY KEY(offer_id));

CREATE TABLE tbl_coverage(coverage_id SERIAL NOT NULL,
                          coverage_name VARCHAR(256) NOT NULL,
                          coverage_active boolean NOT NULL,
                          created_at TIMESTAMP NOT NULL,
                          updated_at TIMESTAMP,
                          deleted_at TIMESTAMP,
                          CONSTRAINT PK_tbl_coverage PRIMARY KEY(coverage_id)); 

CREATE TABLE tbl_assistance(assistance_id SERIAL NOT NULL,
                            assistance_name VARCHAR(256) NOT NULL,
                            assistance_active boolean NOT NULL,
                            created_at TIMESTAMP NOT NULL,
                            updated_at TIMESTAMP,
                            deleted_at TIMESTAMP,
                            CONSTRAINT PK_tbl_assistance PRIMARY KEY(assistance_id));                                                   

CREATE TABLE tbl_offer_coverage(offer_id INT NOT NULL,
                                coverage_id INT NOT NULL,
                                amount NUMERIC(17,2) NOT NULL,
                                created_at TIMESTAMP NOT NULL,
                                updated_at TIMESTAMP,
                                deleted_at TIMESTAMP,
                                CONSTRAINT FK_tbl_offer_coverage_tbl_offer FOREIGN KEY (offer_id) REFERENCES tbl_offer(offer_id),
                                CONSTRAINT FK_tbl_offer_coverage_tbl_coverage FOREIGN KEY (coverage_id) REFERENCES tbl_coverage(coverage_id),
                                CONSTRAINT PK_tbl_offer_coverage PRIMARY KEY(offer_id, coverage_id));                          

CREATE TABLE tbl_offer_assistance(offer_id INT NOT NULL,
                                  assistance_id INT NOT NULL,
                                  number_of_triggers INT NOT NULL,
                                  created_at TIMESTAMP NOT NULL,
                                  updated_at TIMESTAMP,
                                  deleted_at TIMESTAMP,
                                  CONSTRAINT FK_tbl_offer_assistance_tbl_offer FOREIGN KEY (offer_id) REFERENCES tbl_offer(offer_id),
                                  CONSTRAINT FK_tbl_offer_assistance_tbl_assistance FOREIGN KEY (assistance_id) REFERENCES tbl_assistance(assistance_id),
                                  CONSTRAINT PK_tbl_offer_assistance PRIMARY KEY(offer_id, assistance_id));                                      