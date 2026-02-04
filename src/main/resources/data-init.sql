/*mot de passe = root*/
INSERT INTO app_user (email, password, admin) VALUES
    ('a@a.com', '$2a$10$xzwYLnZ37To2ejK0nmPhF.LYepZBRWYkE.F93MGU6oFKAK9D.bOsy', 1),
    ('b@b.com', '$2a$10$xzwYLnZ37To2ejK0nmPhF.LYepZBRWYkE.F93MGU6oFKAK9D.bOsy', 0),
    ('c@c.com', '$2a$10$xzwYLnZ37To2ejK0nmPhF.LYepZBRWYkE.F93MGU6oFKAK9D.bOsy', 0);

INSERT INTO type_resource(name) VALUES
    ('Video'),
    ('Image');

INSERT INTO resource(name, description, type_id, owner_id) VALUES
    ('Video 1', 'Une super vidéo' , 1 , 1),
    ('Image 1', 'Une super image' , 2 , 2);

INSERT INTO favorite (app_user_id, favorite_id) VALUES
    (1, 2),
    (2 , 1),
    (2 , 2);

INSERT INTO comment (content, author_id, resource_id) VALUES
    ('Un super film', 1, 1),
    ('J''ai aimé',2 , 1),
    ('Une super musique',1 , 2);

