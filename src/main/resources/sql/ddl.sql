CREATE TABLE employees (
                           id SERIAL PRIMARY KEY,
                           name VARCHAR(100) NOT NULL,
                           email VARCHAR(100) NOT NULL,
                           role VARCHAR(50) NOT NULL,
                           uuid VARCHAR(36),
                           created_on TIMESTAMP,
                           start_date TIMESTAMP NOT NULL,
                           start_date TIMESTAMP NOT NULL,
                           phone_number VARCHAR(11)

);

CREATE TABLE email_templates (
                                 id SERIAL PRIMARY KEY,
                                 name VARCHAR(100) NOT NULL,
                                 subject VARCHAR(200) NOT NULL,
                                 template TEXT NOT NULL,
                                 uuid VARCHAR(36),
                                 created_on TIMESTAMP
);

CREATE TABLE scheduled_emails (
                                  id SERIAL PRIMARY KEY,
                                  recipient_email VARCHAR(100) NOT NULL,
                                  send_date TIMESTAMP NOT NULL,
                                  template_id INT NOT NULL,
                                  employee_id INT NOT NULL,
                                  FOREIGN KEY (template_id) REFERENCES email_templates(id),
                                  FOREIGN KEY (employee_id) REFERENCES employees(id),
                                  uuid VARCHAR(36),
                                  created_on TIMESTAMP,
                                  retry_limit INT,
                                  frequency VARCHAR(8)

);
