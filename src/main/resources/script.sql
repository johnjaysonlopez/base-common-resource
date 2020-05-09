INSERT INTO public."client"
(id, access_token_validity, additional_information, authorities, authorized_grant_types, autoapprove, client_id, client_secret, refresh_token_validity, web_server_redirect_uri, resource_ids, "scope")
VALUES(1, 10800, 'A', 'A', 'password,refresh_token', 'A', 'project-web-application', '$2a$08$IU3hTHrU1iz85aanrTlQi.3BnrA3surzEUAQ77kPUJBAvIQmeBE2a', 2592000, 'A', 'user-resource', 'ALL_FUNCTIONS');

INSERT INTO public."permission"
(id, action_name, can_maker_checker, code, entity_name, "grouping")
VALUES(1, NULL, true, 'ALL_FUNCTIONS', NULL, 'SPECIAL');

INSERT INTO public."role"
(id, description, is_disabled, "name")
VALUES(1, 'THIS ROLE PROVIDES ALL APPLICATION PERMISSIONS.', false, 'SUPER USER');

INSERT INTO public."role_permission"
(role_id, permission_id)
VALUES(1, 1);

INSERT INTO public."user"
(id, nonexpired, nonlocked, nonexpired_credentials, is_deleted, email, enabled, firstname, last_time_password_updated, lastname, "password", password_never_expires, username)
VALUES(1, false, false, false, false, 'johnjaysonlopez28@gmail.com', true, 'John Jayson', '2019-05-14', 'Lopez', '$2a$08$1qi/gomFpE.37.IhaEUbCOEZFRDLf1ssMpoiaKPHTjzU5Lt0a3XCi', true, 'johnjaysonlpz');

INSERT INTO public."user_role"
(user_id, role_id)
VALUES(1, 1);

