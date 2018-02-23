INSERT INTO USERS (ID, VERSION, ACTIVE, PASSWORD, USERNAME) VALUES (USERS_SEQ.nextval, 1, 'T', '111111', 'admin');
INSERT INTO ROLES (ID, VERSION, DESCRIPTION, ROLE_NAME) VALUES (ROLES_SEQ.nextval, 1, 'Dashboard Index Page', 'dashboard');
INSERT INTO ROLES (ID, VERSION, DESCRIPTION, ROLE_NAME) VALUES (ROLES_SEQ.nextval, 1, 'System Settings', 'settings');
INSERT INTO ROLES (ID, VERSION, DESCRIPTION, ROLE_NAME) VALUES (ROLES_SEQ.nextval, 1, 'User Manager', 'userManager');
INSERT INTO USERS_ROLES (ID, version, role_id, username_id) VALUES (USERS_ROLES_SEQ.nextval, 1, 1, 1);
INSERT INTO USERS_ROLES (ID, version, role_id, username_id) VALUES (USERS_ROLES_SEQ.nextval, 1, 2, 1);
INSERT INTO USERS_ROLES (ID, version, role_id, username_id) VALUES (USERS_ROLES_SEQ.nextval, 1, 3, 1);

/* Create Realm View */
CREATE OR REPLACE VIEW REALM AS SELECT username,role_name FROM USERS, ROLES, USERS_ROLES WHERE USERS.id = USERS_ROLES.username_id AND ROLES.id = USERS_ROLES.role_id;

