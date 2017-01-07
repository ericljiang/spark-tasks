CREATE TABLE Task
(id SERIAL PRIMARY KEY,
 name VARCHAR(256),
 hours DOUBLE PRECISION,
 due_date TIMESTAMP,
 root BOOLEAN
);

CREATE TABLE Subtask
(parent_id INT REFERENCES Task(id),
 child_id INT REFERENCES Task(id),
 PRIMARY KEY(parent_id, child_id)
);

