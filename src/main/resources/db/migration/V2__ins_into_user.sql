
	INSERT INTO user_entity (email, first_name, last_name, password, role, user_status) 
	VALUES ('admin@gmail.com', 'adminFirstName', 'adminLastName', '$2y$12$vzVQoemiyvJi00dahjmtXe3KIrY5gh6BxUt6cgKNKH.cmKdSH8LOa', 'ADMIN', 'ACTIVE');

	INSERT INTO user_entity (email, first_name, last_name, password, role, user_status) 
	VALUES ('activeUser@gmail.com', 'userFirstName', 'userLastName', '$2y$12$L1W.3o1U6S0Z7U127QKGCuZg/mXP29Av1zTD0GCHwOctCEyRRjuCu', 'USER', 'ACTIVE');
	
	INSERT INTO user_entity (email, first_name, last_name, password, role, user_status)
	VALUES ('user3@gmail.com', 'userFirstName', 'userLastName', '$2y$12$L1W.3o1U6S0Z7U127QKGCuZg/mXP29Av1zTD0GCHwOctCEyRRjuCu', 'USER', 'ACTIVE');

	INSERT INTO user_entity (email, first_name, last_name, password, role, user_status)
	VALUES ('user4@gmail.com', 'userFirstName', 'userLastName', '$2y$12$L1W.3o1U6S0Z7U127QKGCuZg/mXP29Av1zTD0GCHwOctCEyRRjuCu', 'USER', 'ACTIVE');

	INSERT INTO user_entity (email, first_name, last_name, password, role, user_status)
	VALUES ('bannedUser@gmail.com', 'userFirstName', 'userLastName', '$2y$12$L1W.3o1U6S0Z7U127QKGCuZg/mXP29Av1zTD0GCHwOctCEyRRjuCu', 'USER', 'BANNED');
