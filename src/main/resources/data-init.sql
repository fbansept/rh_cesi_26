INSERT INTO app_user (email, password) VALUES
    ('a@a.com', 'root'),
    ('b@b.com', 'root');

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

